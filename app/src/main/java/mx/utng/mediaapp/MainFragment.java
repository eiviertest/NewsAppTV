package mx.utng.mediaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.leanback.app.BrowseFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BrowseFragment implements OnItemViewClickedListener {
    private List<Noticia> mNoticias = new ArrayList<>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        loadRows();
        setOnItemViewClickedListener( this );
        setTitle("TV NEWS");
        setHeadersState( HEADERS_ENABLED );
        setHeadersTransitionOnBackEnabled( true );
    }

    private void loadData() {
        String json = Utils.loadJSONFromResource( getActivity(), R.raw.noticias);
        Type collection = new TypeToken<ArrayList<Noticia>>(){}.getType();
        Gson gson = new Gson();
        mNoticias = gson.fromJson( json, collection );
    }

    private void loadRows() {
        ArrayObjectAdapter adapter = new ArrayObjectAdapter( new ListRowPresenter() );
        CardPresenter presenter = new CardPresenter();

        List<String> categories = getCategories();

        if( categories == null || categories.isEmpty() )
            return;

        for( String category : categories ) {
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter( presenter );
            for( Noticia movie : mNoticias ) {
                if( category.equalsIgnoreCase( movie.getCategory() ) )
                    listRowAdapter.add( movie );
            }
            if( listRowAdapter.size() > 0 ) {
                HeaderItem header = new HeaderItem( adapter.size() - 1, category );
                adapter.add( new ListRow( header, listRowAdapter ) );
            }
        }
        setAdapter(adapter);
    }

    private List<String> getCategories() {
        if( mNoticias == null )
            return null;

        List<String> categories = new ArrayList<String>();
        for( Noticia noticia : mNoticias ) {
            if( !categories.contains( noticia.getCategory() ) ) {
                categories.add( noticia.getCategory() );
            }
        }

        return categories;
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof Noticia) {
            Noticia noticia = (Noticia) item;
            Intent intent = new Intent(getActivity(), NoticiaDetailsActivity.class);
            intent.putExtra(NoticiaDetailsFragment.EXTRA_NOTICIA, noticia);
            startActivity(intent);
        }
    }
}

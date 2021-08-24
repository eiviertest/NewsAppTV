package mx.utng.mediaapp;

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;

public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    @Override
    protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object item) {
        Noticia noticia = (Noticia) item;

        if (noticia != null) {
            viewHolder.getTitle().setText(noticia.getTitle());
            viewHolder.getSubtitle().setText(noticia.getCategory());
            viewHolder.getBody().setText(noticia.getDescription());
        }
    }
}

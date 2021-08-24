package mx.utng.mediaapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayerActivity extends Activity implements PlayerControlsFragment.PlayerControlsListener {
    private VideoView mNoticiaView;
    private Noticia mNoticia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mNoticiaView = (VideoView) findViewById( R.id.video_view );
        mNoticia = (Noticia) getIntent().getSerializableExtra( NoticiaDetailsFragment.
                EXTRA_NOTICIA );
        mNoticiaView.setVideoPath( mNoticia.getVideoUrl() );

        initViews();
        initVideoPlayer();
    }

    private void initViews() {
        mNoticiaView = (VideoView) findViewById( R.id.video_view );
    }

    private void initVideoPlayer() {
        mNoticia = (Noticia) getIntent().getSerializableExtra( NoticiaDetailsFragment.EXTRA_NOTICIA );
        mNoticiaView.setVideoPath( mNoticia.getVideoUrl() );
    }

    @Override
    public void play() {
        mNoticiaView.start();
    }

    @Override
    public void pause() {
        mNoticiaView.pause();
    }
}

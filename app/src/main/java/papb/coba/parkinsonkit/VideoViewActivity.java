package papb.coba.parkinsonkit;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.MediaController;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by NADYA ARIZKA on 08/12/2015.
 */
public class VideoViewActivity extends AppCompatActivity {

    String TAG = "papb.coba.parkinsonkit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        final VideoView videoView = (VideoView) findViewById(R.id.videoView1);

        videoView.setVideoPath("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");

        //
        //https://www.dropbox.com/s/fd5rwsilvk44ec4/Exercises%20for%20Parkinson%27s-%20Flexibility%20Exercises.3gp?dl=0

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new
                                                MediaPlayer.OnPreparedListener() {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mp) {
                                                        Log.i(TAG, "Duration = " +
                                                                videoView.getDuration());
                                                    }
                                                });


        videoView.start();
    }

    public void onResume(){
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }
}

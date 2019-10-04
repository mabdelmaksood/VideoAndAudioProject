package abdelmaksood.mohamed.videoandaudioproject;

import androidx.appcompat.app.AppCompatActivity;

//import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    VideoView video;
    Button btnVideo;
    MediaController myMediaCtrl;
    static boolean is_start=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video=findViewById(R.id.Video);
        btnVideo=findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(MainActivity.this);
        myMediaCtrl=new MediaController(MainActivity.this);
        String videoPath="android.resource://"+getPackageName()+"/"+R.raw.horses;
        video.setVideoPath(videoPath);
        video.setMediaController(myMediaCtrl);
        myMediaCtrl.setAnchorView(video);

        /*
        Uri videoUri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.horses);
        video.setVideoURI(videoUri);
        */


    }

    @Override
    public void onClick(View v) {
        String videoPath="android.resource://"+getPackageName()+"/"+R.raw.horses;
        video.setVideoPath(videoPath);
        video.setMediaController(myMediaCtrl);
        myMediaCtrl.setAnchorView(video);
        if(video.isPlaying()) video.pause();
        else if (is_start) {

            video.resume();
        }
        else{
            video.start();
            is_start=true;
        }
    }
}

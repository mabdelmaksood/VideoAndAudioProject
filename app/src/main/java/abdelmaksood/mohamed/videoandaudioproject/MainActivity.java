package abdelmaksood.mohamed.videoandaudioproject;

import androidx.appcompat.app.AppCompatActivity;

//import android.net.Uri;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {
    VideoView video;
    Button btnVideo;
    Button btnAudio;
    Button btnSilent;
    MediaPlayer musicPlayer;
    SeekBar musicBar;
    AudioManager audioService;
    boolean videoInit=false;



    MediaController myMediaCtrl;
    static boolean is_start=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialise view items
        video=findViewById(R.id.Video);
        btnVideo=findViewById(R.id.btnVideo);
        btnAudio=findViewById(R.id.audiobtn);
        btnSilent=findViewById(R.id.audiobtn2);
        musicBar=findViewById(R.id.audioSeeker);
        audioService= (AudioManager)  getSystemService(AUDIO_SERVICE);
        int maxVolume=audioService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume=audioService.getStreamVolume(AudioManager.STREAM_MUSIC);

        //set on click listener
        btnSilent.setOnClickListener(MainActivity.this);
        btnAudio.setOnClickListener(MainActivity.this);
        btnVideo.setOnClickListener(MainActivity.this);


        myMediaCtrl=new MediaController(MainActivity.this);
        video.setOnCompletionListener(MainActivity.this);
        Uri SoundUri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.eagle);
        musicPlayer= MediaPlayer.create(this,SoundUri);
        musicPlayer.setOnCompletionListener(MainActivity.this);


        /*
        Uri videoUri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.horses);
        video.setVideoURI(videoUri);
        */

        musicBar.setMax(maxVolume);
        musicBar.setProgress(currVolume);
        musicBar.setOnSeekBarChangeListener(MainActivity.this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnVideo:
                if(!videoInit){
                    String videoPath="android.resource://"+getPackageName()+"/"+R.raw.lighthouse;
                    video.setVideoPath(videoPath);
                    video.setMediaController(myMediaCtrl);
                    myMediaCtrl.setAnchorView(video);
                }
                if(video.isPlaying()){
                    video.pause();
                    musicPlayer.pause();
                }
                else if (is_start) {

                    video.resume();
                    musicPlayer.start();
                }
                else{
                    video.start();
                    is_start=true;
                    musicPlayer.start();
                }
                break;
            case R.id.audiobtn:
                btnAudio.setClickable(false);
                btnSilent.setClickable(true);
                btnAudio.animate().alpha(0);
                btnSilent.animate().alpha(1);
                musicPlayer.pause();
                break;
            case R.id.audiobtn2:
                btnAudio.setClickable(true);
                btnSilent.setClickable(false);
                btnAudio.animate().alpha(1);
                btnSilent.animate().alpha(0);
                musicPlayer.start();

        }

    }
    @Override
    public void onCompletion(MediaPlayer mp) {
                mp.start();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            //Toast.makeText(getApplicationContext(),progress+"",Toast.LENGTH_SHORT).show();
            audioService.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

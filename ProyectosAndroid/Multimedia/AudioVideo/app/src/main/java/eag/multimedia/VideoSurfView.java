package eag.multimedia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class VideoSurfView extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener{

    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_surf_view);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);

        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(this);


    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        try {
            mediaPlayer.setDisplay(surfaceHolder);

            mediaPlayer.setDataSource("https://r3---sn-h5nhv8pa-h5qe.googlevideo.com/videoplayback?signature=7FCE1CE35C7131180E87D54E974E86341086410E.3290DF7171CBA5A800253991D8DD5B3498CAB45B&mime=video/mp4&ratebypass=yes&sparams=clen,dur,ei,expire,gir,id,initcwndbps,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&ipbits=0&requiressl=yes&id=o-AHqv93-YXoRu6GVLEXl74--TgCw3K66yaA9_oNUH0bhH&dur=5.526&ip=209.205.200.210&key=cms1&lmt=1473143215480635&source=youtube&pl=17&ei=vLFsWpfDMq6f8gSh_r-wAw&clen=185745&expire=1517094428&gir=yes&itag=18&quality=small&type=video%2Fmp4%3B+codecs%3D%22avc1.42001E%2C+mp4a.40.2%22&title=(Hdvidz.in)_Dramatic-Chipmunk&redirect_counter=1&rm=sn-ab5ees7s&req_id=b87041ae8560a3ee&cms_redirect=yes&ipbypass=yes&mip=139.47.19.113&mm=31&mn=sn-h5nhv8pa-h5qe&ms=au&mt=1517072729&mv=m");
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mediaPlayer.release();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}

package eag.multimediacamara;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class GrabActivity extends AppCompatActivity implements SurfaceHolder.Callback{


    Button btnPlay;
    Button btnStop;

    SurfaceView surfaceView;
    MediaRecorder mediaRecorder;

    public static final int CODE_SAVE_VIDEO = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        btnPlay     = (Button)      findViewById(R.id.btnPlay);
        btnStop     = (Button)      findViewById(R.id.btnStop);


         SurfaceHolder surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(this);



        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, CODE_SAVE_VIDEO);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaRecorder.start();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                mediaRecorder.reset();
            }
        });

    }

    /// Gestiona la superficie ///
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        //Configuramos el Media Recorder
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory() + "/eag.mp4");

        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.getMessage();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mediaRecorder.release();
    }

    /// Gestion de permisos ///
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Aqui debemos comprobar todos los permisos
        if(requestCode == CODE_SAVE_VIDEO && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            btnPlay.setEnabled(true);
            btnStop.setEnabled(true);
        }
    }


}

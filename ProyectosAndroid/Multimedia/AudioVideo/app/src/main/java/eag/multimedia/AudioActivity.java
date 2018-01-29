package eag.multimedia;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class AudioActivity extends AppCompatActivity
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener{

    private MediaPlayer mediaPlayer;
    private Button btnPlay;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);


        //Todo 1. Reproduccion desde archivo de la aplicación

        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mj);
        //mediaPlayer.start();

        //Todo 2. Reproduccion desde URL

        AudioAttributes attributes = new AudioAttributes.Builder().
                setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        //String url = "http://www.noiseaddicts.com/samples_1w72b820/4190.mp3"; // your URL here
        String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);

        mediaPlayer.setAudioAttributes(attributes);


        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            //mediaPlyaer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();



        // Todo 3. Algunos metodos de la clase MediaPlayer

        //Duración del audio
        //mediaPlayer.getDuration();

        //Posicion actual
        //mediaPlayer.getCurrentPosition();

        //Adelantarse a la posición.
        //mediaPlayer.seekTo(100000);


    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Toast.makeText(this, "Cancion completada!!", Toast.LENGTH_SHORT).show();
    }

    //Todo 4. Metodo que se llama cuando se provoca un error en tareas asincronas.
    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();

        //Todo 5.Debemos resetear para no dejar colgado los recursos y volver a estado
        this.mediaPlayer.reset();

        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        //Todo 6.Si ha sido invocado el método prepareAsync() se invocaria este método


        Toast.makeText(this, "Cancion preparada!! Comenzando cancion...", Toast.LENGTH_SHORT).show();

        //mediaPlayer.start();
    }


}

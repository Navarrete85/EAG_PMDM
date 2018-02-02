package eag.multimediacamara;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Text;

import java.util.Locale;

public class SpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        textToSpeech = new TextToSpeech(this,this);


    }

    @Override
    public void onInit(int status) {
        Locale localEs = new Locale("es", "ES");
        textToSpeech.setLanguage(localEs);
        textToSpeech.speak("", TextToSpeech.QUEUE_FLUSH, null,null);
    }
}

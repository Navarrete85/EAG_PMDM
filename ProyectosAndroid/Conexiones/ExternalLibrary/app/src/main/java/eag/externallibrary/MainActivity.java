package eag.externallibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class MainActivity extends AppCompatActivity {


    //Todo 7. Declaramos el botón
    private TwitterLoginButton loginButton;

    public static String USERNAME_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Todo 6. Añadimos el botón de login en el layout
        //Todo 8. Conectamos el botón con su referencia y creamos un listener con una clase anonima
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(MainActivity.this, "Logeado con exito", Toast.LENGTH_SHORT).show();

                //Todo 9. Recogemos la sesión de twitter donde podemos acceder a información relevante
                TwitterSession twitterSession = result.data;


                //Todo - 9.1 - En el momento que se quiera hacer uso de la API REST y será necesario
                //Todo -> la identificacion del usuario. La forma segura que pide Twitter son los tokens.
                TwitterAuthToken authToken = twitterSession.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;


                //Todo 10. Lanzamos la nueva actividad para mostrar la lista de tweets -> Sigue en la nueva actividad
                Intent i = new Intent(MainActivity.this, RecyclerTweetsActivity.class);
                i.putExtra(USERNAME_KEY, twitterSession.getUserName());
                startActivity(i);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this, "Fallo en inicio de sesion", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode,resultCode,data);
    }
}

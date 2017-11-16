package eag.externallibrary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import static eag.externallibrary.MainActivity.USERNAME_KEY;

public class RecyclerTweetsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_tweets);

        //Todo 11. Recogemos el usuario pasado en el intent
        String userName = getIntent().getStringExtra(USERNAME_KEY);

        //Todo 12. Instanciamos y conectamos el RecyclerView asi como asignamos el modo de visualización
        //todo  -> de la lista
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Todo 13. Creamos el tipo de TimeLine que queremos cargar
        UserTimeline userTimeline = new UserTimeline.Builder().screenName(userName).build();

        //Todo 14. Creamos el adaptador asignando el tipo de timeLines y configurando el estilo
        final TweetTimelineRecyclerViewAdapter adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(userTimeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();

        //Todo 15. Asignamos el adaptador al recycler.
        recyclerView.setAdapter(adapter);

        //Todo 16 - Ejercicio Librería Toast
        StyleableToast st = new StyleableToast
                            .Builder(this)
                            .text("Actividad cargada!!")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .duration(Toast.LENGTH_SHORT)
                            .build();

        st.show();

    }
}

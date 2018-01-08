package eag.externallibrary;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by JotaC on 12/11/17.
 */

public class CustomApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Todo 1. Registrarnos en twitter
        //Todo 2. Añadir biblioteca a gradle en app.gradle
        //Todo 3. Añadir al archivo strings.xml la ID y la PRIVATEKEY de la cuenta
        //Todo 4. Configuramos Twitter Kit
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.CONSUMER_KEY),getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build();

        //Todo 5. Inicializamos Twitter Kit
        Twitter.initialize(config);

    }
}

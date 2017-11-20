package es.eag.pmdm.parsexml;

import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import es.ua.jtech.daa.parsexml.R;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	TextView textView, textViewTitulo;
	EditText editText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textView	   = (TextView)findViewById(R.id.TextView01);
        textViewTitulo = (TextView)findViewById(R.id.TextViewTitulo);
        editText 	   = (EditText)findViewById(R.id.EditText01);

		// Este listener asociado, se llama cada vez que el usuario introduce una letra.
		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				//Esta es una forma de saber que se ha presionado la tecla enter.
		        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
		            // Perform action on key press
		        	accionDescargar();
		        	return true;
		          }
		          return false;
			}
		});
        ((Button)findViewById(R.id.Button01)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				accionDescargar();
			}
		});

		ConnectivityManager cm = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean hayWifi = wifi.isAvailable();
		boolean hayMobile = mobile.isAvailable();
		boolean noHay = (!hayWifi && !hayMobile);
		cm.setRadio(NetworkType.MOBILE,false);
		cm.setRadio(NetworkType.WIFI,true);
		
   }
   void accionDescargar(){
		TareaDescarga tarea = new TareaDescarga();
		try {
			tarea.execute(new URL(editText.getText().toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
   }
   
   private class TareaDescarga extends AsyncTask<URL, String, String>{
	String resultado;
	String direccion;
	@Override
	protected String doInBackground(URL... params) {
		try {
			URL url = params[0];

			resultado = "";
			direccion = url.getAuthority();
			
			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(url.openStream(), null);
			int parserEvent = parser.getEventType();
			int nItems = 0;

			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					String tag = parser.getName();
					if (tag.equalsIgnoreCase("item")) {
						publishProgress("Cargado item "+(++nItems));
						parserEvent = parser.next();

						boolean itemClosed = false;
						while (parserEvent != XmlPullParser.END_DOCUMENT && !itemClosed) {
							switch (parserEvent) {
							case XmlPullParser.START_TAG:
								tag = parser.getName();
								if (tag.equalsIgnoreCase("title")) {
									resultado += ("Title: " + parser.nextText()+"\n");
								}
								if (tag.equalsIgnoreCase("link")) {
									resultado += ("Link: "+parser.nextText()+"\n");
								}
								break;
							case XmlPullParser.END_TAG:
								tag = parser.getName();
								if(tag.equalsIgnoreCase("item")){
									itemClosed = true;
									resultado += ("-\n");
								}
								break;
							}
							parserEvent = parser.next();
						}
					}
					break;
				}
				parserEvent = parser.next();

			}
		} catch (Exception e) {
			Log.e("Net", "Error in network call", e);
		}

		resultado += ("--\n");
		
		return "Carga finalizada";
	}
	
	

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}



	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		textViewTitulo.setText(values[0]);
	}



	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		textViewTitulo.setText(direccion);
		textView.setText(resultado);
		Linkify.addLinks(textView, Linkify.WEB_URLS);

	}

	private class AsyncExample extends AsyncTask<Integer, Float, String> {
		@Override
		protected String doInBackground(Integer... integers) {
			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Float... values) {
			super.onProgressUpdate(values);
		}
	}


	   
   }
}
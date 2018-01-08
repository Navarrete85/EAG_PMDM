package es.ua.jtech.daa.lectorrss;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Main extends Activity {
    /** Called when the activity is first created. */
	TextView textViewTitulo;
	EditText editText;
	ArrayList<Noticia> noticias;
	NoticiasAdapter noticiasAdapter;
	ListView listView;
	ProgressDialog progressDialog;
	TareaDescarga tarea;
	Context context;
	Main main;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getApplicationContext();
        main = this;
        
        noticias = new ArrayList<Noticia>();
        noticiasAdapter = new NoticiasAdapter(context, R.layout.fila, noticias);
        listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(noticiasAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Noticia n = noticias.get((int)id);
				Toast.makeText(context, n.getDescripcion(), Toast.LENGTH_LONG).show();
			}
		});
        
        textViewTitulo = (TextView)findViewById(R.id.TextViewTitulo);
        editText = (EditText)findViewById(R.id.EditText01);
        editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
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

        
		
   }
   void accionDescargar(){
		try {
			tarea = new TareaDescarga();
			tarea.execute(new URL(editText.getText().toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
   }
   
   private class TareaDescarga extends AsyncTask<URL, String, String>{
	String direccion;
	boolean error=false;
	@Override
	protected String doInBackground(URL... params) {
		try {
			URL url = params[0];

			noticias = new ArrayList<Noticia>();
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
						publishProgress("Cargando item "+(++nItems));
						Noticia noticia = new Noticia();
						parserEvent = parser.next();
						boolean itemClosed = false;

						while (parserEvent != XmlPullParser.END_DOCUMENT && !itemClosed) {
							switch (parserEvent) {
							case XmlPullParser.START_TAG:
								tag = parser.getName();
								if (tag.equalsIgnoreCase("title")) {
									noticia.setTitulo(parser.nextText());
								}
								if (tag.toLowerCase().contains("date")) {
									noticia.setFecha(parser.nextText());
								}
								if (tag.equalsIgnoreCase("link")) {
									noticia.setLink(parser.nextText());
								}
								if (tag.equalsIgnoreCase("description")) {
									noticia.setDescripcion(parser.nextText());
								}
								if (tag.toLowerCase().contains("thumbnail")) {
									String linkImagen = parser.getAttributeValue(null, "url");
									noticia.setLinkImagen(linkImagen);
									try{
										noticia.loadImagen(linkImagen);
									}catch(Exception e){
										Log.w("NET","La imagen no se ha cargado bien desde la URL "+linkImagen,e);
									}
								}
								break;

							case XmlPullParser.END_TAG:
								tag = parser.getName();
								if(tag.equalsIgnoreCase("item")){
									itemClosed = true;
									noticias.add(noticia);
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
			error = true;
		}
		
		
		return "Carga finalizada";
	}
	
	

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		noticiasAdapter.clear();
		progressDialog = ProgressDialog.show(main, "Espere...", "Descargando noticias",true,true);
		progressDialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				tarea.cancel(true);
			}
		});
	}



	@Override
	protected void onCancelled() {
		super.onCancelled();
		textViewTitulo.setText("Descarga cancelada");
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
		for(Noticia n:noticias){
			noticiasAdapter.add(n);
		}
		noticiasAdapter.notifyDataSetChanged();
		progressDialog.dismiss();
		if(error){
			Toast.makeText(context, R.string.errordered, Toast.LENGTH_LONG).show();
			textViewTitulo.setText(R.string.errordered);
		}

	}
	   
   }
   
}
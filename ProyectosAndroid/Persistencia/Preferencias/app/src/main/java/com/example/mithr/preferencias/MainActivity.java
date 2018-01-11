package com.example.mithr.preferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnLdAct;
    private Button btnLdFragm;
    private Button btnOpt;
    private Button btnSvAct;
    private TextView texto;

    //TODO 1. Crear objeto SharePreference
    SharedPreferences sp;

    //TODO 2. Las claves y nombres las creo de forma estática
    public static final String NAME_PREFS = "DAMPREF";
    public static final String KEY_TEST = "clave";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 3. Crea o Abre(Si ya existe) el archivo de preferencias de la aplicacion de forma privada
        sp = getSharedPreferences(NAME_PREFS, MODE_PRIVATE);

        texto = (TextView) findViewById(R.id.textView);

        btnSvAct = (Button) findViewById(R.id.btnSv1);
        btnSvAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 4. Creo un objeto de la clase Editor para poder editar las preferencias propias del archivo de preferencias que estamos accediendo
                SharedPreferences.Editor editor = sp.edit();

                //TODO 5. A través de métodos put([ipo]
                editor.putString(KEY_TEST, "Mensaje cargado de prefs 1");

                //TODO 6. Confirmamos con commit los datos almacenados
                editor.commit();
            }
        });

        btnLdAct = (Button) findViewById(R.id.button);
        btnLdAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 7. Accedo a las preferencias a través de la clave, si no existe dicha clave, aparecerá el valor por defecto definido en el 2º parametro
                String msg = sp.getString(KEY_TEST, "Valor por defecto");
                texto.setText(msg);
            }
        });

        btnLdFragm = (Button) findViewById(R.id.button2);
        btnLdFragm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spFrag = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String msg = spFrag.getString("KEY", "Valor por defecto desde Fragment");
                texto.setText(msg);
            }
        });

        btnOpt = (Button) findViewById(R.id.btnOpc);
        btnOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }
}

package eag.serviciospermisos;

import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eag.serviciospermisos.Services.MyService;

public class MainActivity extends AppCompatActivity {


    private Button mBtnStart;
    private Button mBtnStop;
    Intent i;
    private boolean serviceRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo 1. Crear servicio en manifest

        mBtnStart = (Button) findViewById(R.id.button);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Todo 2. Llamar a un servicio sin vincular con startService() pasandole el intent como si una actividad se tratara
                i = new Intent(MainActivity.this, MyService.class);

                // Todo 3. Si lo necesitamos tambien podemos enviar información con put[tipo]Extra().
                i.putExtra("DATA", "Mensaje para el servicio");

                //Todo 4. Llamamos al servicio. --> sigue en clase MyService
                startService(i);


            }
        });


        mBtnStop = (Button) findViewById(R.id.button2);
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo - 7.2 Si queremos parar un servicio desde fuera de la clase..
                stopService(i);
            }
        });
    }
}

package eag.dialogsthreads;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //  1. Creo elementos de la vista y el objeto dialog para mostrar
    private Button mBtnDialog;
    private Button mBtnThread;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///********* PARTE 1 DIALOGS **********///


        /// Todo Crear textos en string.xml

        // 2. Creo el constructor de dialogos e inicializo variables
        AlertDialog.Builder builder   = new AlertDialog.Builder(this);
        mBtnDialog =   (Button) findViewById(R.id.button2);


        // 3. Inserto titulo y mensaje.
        builder.setTitle(R.string.titleDialog);
        builder.setMessage(R.string.msgDialog);

        ///Todo 7. Si quiero personalizarlo

        /*LayoutInflater      inflater  = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null)); */

        //Todo 8. Agregar una lista
        // Si quieres mostrar una lista no puedes tener un mensaje (quitarlo del paso 3)
        /*builder.setItems(R.array.toppings, new DialogInterface.OnClickListener() {
            @Override


            public void onClick(DialogInterface dialog, int which) {
                //Which contiene el indice del elemento pulsado
                Toast.makeText(MainActivity.this, "Elemento pulsado: "+ which, Toast.LENGTH_SHORT).show();
            }
        });*/

        //Todo 9. Si quieres que la lista sea de selección multiple

        //Usamos un array como ejemplo para controlar los elementos de las listas
         /*final ArrayList mItemSelected = new ArrayList();

        builder.setMultiChoiceItems(R.array.toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    mItemSelected.add(which);
                }else if(mItemSelected.contains(which)){

                    // Usamos Integer para que haga referencia al objeto a borrar no a su posicion
                    mItemSelected.remove(Integer.valueOf(which));
                }
            }
        }); */

        // 4. inserto botones a traves de la clase DialogInterface
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Bien Hecho!!", Toast.LENGTH_SHORT).show();
            }
        });


        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Maaaaaal", Toast.LENGTH_SHORT).show();
            }
        });


        // 5. Creo el objeto de tipo AlertDialog que ha ido construyendo nuestro builder
        dialog = builder.create();

        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6. Mostar el dialog cuando se quiera a través de la función show.
                dialog.show();
            }
        });

        //*********** PARTE 2: THREADS *********** //

         mBtnThread     =   (Button) findViewById(R.id.button);

         mBtnThread.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                  Thread t = new Thread(new Runnable() {

                     int progress = 0;

                     @Override
                     public void run() {
                         for (int i = 1; i<=10; i++){
                             tareaLarga();
                         }

                         //Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                     }
                 });

                 //t.start();
                 for (int i = 1; i<=10; i++){
                     tareaLarga();
                 }


             }
         });

    }


    public void tareaLarga(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

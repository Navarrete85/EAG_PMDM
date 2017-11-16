package eag.menuapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mEdit;
    private Button mButton;

    private ActionMode actionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recojo el action bar que trae la app por defecto
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setIcon(R.mipmap.ic_launcher);

        mTextView = (TextView) findViewById(R.id.TextView);
        mEdit     = (EditText) findViewById(R.id.editText);
        mButton   = (Button)   findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });




        // Registramos la vista que va a sacar un
        // menu contextual
        registerForContextMenu(mEdit);


        /*
            Para mostrar la barra deberemos iniciar el action mode desde algún evento.
            Por ejemplo, podemos iniciar el action mode desde el evento de pulsación de larga duración
            sobre un elemento de la interfaz. Al lanzar este modo obtendremos
            un objeto de tipo ActionMode:
        */

        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(actionMode != null) {
                    return false;
                }else{
                    actionMode = startActionMode(mActionModeCallback);
                }
                v.setSelected(true);
                return true;
            }
        });

    }

    //******************************************//
    //************ MENU DE OPCIONES ************//
    //******************************************//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.menuGrItem1:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                break;
            case R.id.menuGrItem2:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                break;
            case R.id.itemMenu1:
                toast("Pulsada opcion 1");
                break;
            case R.id.itemMenu2:
                toast("Pulsada opcion 2");
                break;
            case R.id.itemMenu3:
                toast("Pulsada opcion 3");
                break;
        }

        return true;
    }

    //Cambiar el menu en tiempo de ejecución
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        //Cambiamos
        MenuItem menuItem = menu.findItem(R.id.menuGrItem1);
        if(menuItem.isChecked())
            menu.findItem(R.id.itemMenu3).setVisible(true);
        else
            menu.findItem(R.id.itemMenu3).setVisible(false);

        return true;
    }

    //*****************************************//
    //************ MENU CONTEXTUAL ************//
    //*****************************************//


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Ponemos titulo al menu contextual
        menu.setHeaderTitle("Menu contextual");
        getMenuInflater().inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        switch (item.getItemId()){
            case R.id.itemMenu1:
                toast("Menu Contextual: opcion 1");
                break;
            case R.id.itemMenu2:
                toast("Menu Contextual: Pulsada opcion 2");
                break;
            case R.id.itemMenu3:
                toast("Menu Contextual: Pulsada opcion 3");
                break;
        }

        return true;
    }

    //***********************************************//
    //************ MENU BARRA CONTEXTUAL ************//
    //***********************************************//

    /*  Para gestionar el action mode deberemos
        definir un objeto de tipo ActionMode.Callback,
        con el que definiremos el comportamiento de este modo:
    */

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.itemMenu1:
                toast("AQUI IRIA UNA ACCION A REALIZAR");
                mode.finish(); //Al realizar la acción cierra el actionmode return true;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //Ponemos a null
            actionMode = null;
        }
    };


    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

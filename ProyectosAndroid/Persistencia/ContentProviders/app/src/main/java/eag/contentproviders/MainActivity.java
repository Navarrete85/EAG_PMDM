package eag.contentproviders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    //Código propio de peticion de permisos
    public static final int CODE_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pedimos permisos
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},CODE_READ_CONTACTS);


    }


    //Si el sistema nos da permiso se llama a este método
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CODE_READ_CONTACTS:

                if(grantResults[0] == PERMISSION_GRANTED) {

                    readContacts();
                }
                break;
        }

    }


    public void readContacts(){

        // Nombre de las tablas que queramos
        String[] projection =
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME
                };

        //Llamamos a query pasandole la uri y las tablas que necesitamos
        Cursor contactsCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                projection,null,null,null);

        //Iteramos sobre el cursor.
        if(contactsCursor.moveToFirst()){
            do {
                Log.i("TAG", contactsCursor.getString(0));
                Log.i("TAG", contactsCursor.getString(1));
                Log.i("TAG", " -------");
            }while(contactsCursor.moveToNext());
        }else{
            Log.i("TAG", "No hay filas!");
        }

    }
}

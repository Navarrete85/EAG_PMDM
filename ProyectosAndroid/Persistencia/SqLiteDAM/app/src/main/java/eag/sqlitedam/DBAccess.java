package eag.sqlitedam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by JotaC on 27/12/17.
 */

public class DBAccess extends SQLiteOpenHelper {

    //Todo 1. Extendemos la clase con SQLiteOpenHelper para tener acceso a los métodos que gestiona
    //todo-> la base de datos.

    //Database name
    private static final String DB_NAME = "db_dam";

    //Table name
    private static final String DB_TABLE_NAME = "db_ciudades";

    //Database version
    private static final int DB_VERSION = 1;

    //Columns
    private static final String CITY_COLUMN = "cityName";

    private static final String POBLATION_COLUMN = "poblation";

    private static final String SURFACE_COLUMN = "surface";

    //Application Context
    private Context mContext;


    public DBAccess(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mContext = context;

    }

    //Todo 2. Sobrecargamos onCreate, encargado de crear la base de datos y las tablas asociadas.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_USER_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "("
                + CITY_COLUMN + " TEXT)";


        //Todo 3. Lanzamos la consulta con execSQL
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        toast("Base de datos creada");

    }

    // Todo 4. Sobrecargamos onUpgrade, encargado de actualizar la base de datos y las tablas asociadas.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


        //Todo 5. En el caso de que hagamos algun cambio en la base de datos es necesario controlar
        //todo -> la versión donde se encuentra e ir modificando la base de datos desde la última
        //todo -> que tenga de la BD en adelante.
        switch(oldVersion){
            case 1:
                sqLiteDatabase.execSQL("ALTER TABLE ciudades ADD COLUMN poblacion INTEGER");
            case 2:
                sqLiteDatabase.execSQL("ALTER TABLE ciudades ADD COLUMN superficie REAL");
            case 3:
                sqLiteDatabase.execSQL("ALTER TABLE ciudades ADD COLUMN gentilicio TEXT");
                break;
        }

        toast("Base de datos actualizada");

    }

    //Todo 6. Creamos un método para insertar un dato en la BD.
    public void insert(String city, int poblation, float superficie){

        //Todo 7. Pedimos acceso de escritura en la base de datos.
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CITY_COLUMN, city);
        values.put(POBLATION_COLUMN, poblation);
        values.put(SURFACE_COLUMN, superficie);

        //Todo 8. Insertamos a través del método insert, cuyos parametro son:
        //todo -> nombre de la tabla
        //todo -> nullColumnHack permite indicar si hay una columna cuyo valor pueda ser nulo.
        //todo -> valores asociados a la inserción.
        if(db.insert(DB_TABLE_NAME,null,values) != -1){
            toast("Usuario insertado");
        }else{
            toast("Error: Usuario no insertado!!!");
        }

        db.close();

    }

    //Todo 9. Creamos un método para recuperar datos en la BD.
    public String getCity(){

        String result = "Error!!";

        //Todo 10. Pedimos acceso de lectura de la BD.
        SQLiteDatabase db = this.getReadableDatabase();

        //Todo 11. Realizamos la consulta a través del método 'query', cuyo significado de los
        // todo-> parámetros tenemos en los apuntes. Este método devuelve un cursor que nos
        // todo-> permite recorrer las tuplas del resultado.

        //String cols = new String[]{ "poblation" };
        //String selection = "city=?"
        //String args = new String[]{"jerez"}
        //Cursor c = db.query(DB_TABLE_NAME,cols,selection,args,null,null,null);
        Cursor c = db.query(DB_TABLE_NAME,null,null,null,null,null,null);

        //Todo 12. Movemos el iterador al primer elemento (si existe devuelve true sino false)
        if(c.moveToFirst()){

            do{
                //Todo 13. Cogemos el valor referente a la posicion de la columna
                String city = c.getString(0);

                result = city;
            }while(c.moveToNext()); //Todo 14. Mientras exista siguiente vamos moviendonos entre tuplas
        }

        //Todo 15. Cerramos el cursor
        if(c != null) {
            c.close();
        }

        //Todo 16. Cerramos la base de datos.
        db.close();

        return result;

    }

    //TODO ---- PARTE 2 ----- //

    public void toast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}

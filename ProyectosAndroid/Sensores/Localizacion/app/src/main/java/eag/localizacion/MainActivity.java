package eag.localizacion;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener{

    public static final int CODE_PERMISSION_GPS = 0;
    public static final int CODE_LOC_ON = 1;

    private TextView mTxtLong;
    private TextView mTxtLat;
    private TextView mTxtAlt;
    private TextView mTxtAdress;

    private Button mBtnStart;
    private Button mBtnStop;
    private Button mBtnLast;

    private LocationManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTxtLong = (TextView) findViewById(R.id.tvLong);
        mTxtLat = (TextView) findViewById(R.id.txLat);
        mTxtAlt = (TextView) findViewById(R.id.txAlt);
        mTxtAdress = (TextView) findViewById(R.id.tvAddress);

        mBtnLast = (Button) findViewById(R.id.btn_last);
        mBtnStart = (Button) findViewById(R.id.btn_Start);
        mBtnStop = (Button) findViewById(R.id.btn_Stop);

        // Todo 1. Debemos pedir los permisos ACCESS_FINE_LOCATION para localización precisa
        // todo -> y ACCESS_COARSE_LOCATION para localización aproximada.
        // todo -> Recuerda incluirlo en el manifest!!


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, CODE_PERMISSION_GPS);
        }


        //Todo 2. Creamos un objeto LocationManager para acceder a través del sistema al servicio
        // todo -> de localizacion.
        mManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Todo 3. Como se pueden obtener la localizacion desde dos proveedores (GPS o WIFI)
        // todo -> indicamos a través de cual queremos obtener la información.
        LocationProvider locProvider = mManager.getProvider(LocationManager.GPS_PROVIDER); // WIFI_PROVIDER

        //Todo 3.1. Puede darse el caso de que no estén disponibles los proveedores, podemos usar
        // todo -> lo siguiente:
        //List<String> listProvider = mManager.getAllProviders();

        //Todo 4. Comprobamos que la localizacion del dispositivo está activado
        if (!mManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(i, CODE_LOC_ON);
        }

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mManager.removeUpdates(MainActivity.this);
                mTxtAlt.setText("-");
                mTxtLat.setText("-");
                mTxtLong.setText("-");
                mTxtAdress.setText("-");
            }
        });

        mBtnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 5. Podemos acceder a la ultima posicion registrada por un proveedor.

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Location lastLoc = mManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (lastLoc != null){
                        mTxtAlt.setText(Double.toString(lastLoc.getAltitude()));
                        mTxtLat.setText(Double.toString(lastLoc.getLatitude()));
                        mTxtLong.setText(Double.toString(lastLoc.getLongitude()));
                    }
                }
            }
        });

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 7. Pedimos actualizaciones del GPS cada (tiempo) y en el caso de que supere
                // todo -> cierta (distancia)
                int time = 5000;
                float distance = 10;
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, time, distance, MainActivity.this);
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == CODE_PERMISSION_GPS && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE_LOC_ON && resultCode == RESULT_OK){

        }
    }


    //Todo 6. Implementamos el listener LocationListener para registrar cambios
    @Override
    public void onLocationChanged(Location location) {
        mTxtAlt.setText(Double.toString(location.getAltitude()));
        mTxtLat.setText(Double.toString(location.getLatitude()));
        mTxtLong.setText(Double.toString(location.getLongitude()));

        //Todo 8. Es posible transformar longitud y latitud en una dirección y viceversa
        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

        try {
            List<Address> list = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 3);
            String address = list.get(0).toString();
            mTxtAdress.setText(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

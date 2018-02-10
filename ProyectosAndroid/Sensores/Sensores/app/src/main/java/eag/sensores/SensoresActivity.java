package eag.sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SensoresActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager mSensorManager;
    List<Sensor> mSensorList;
    TextView mTvLight;
    TextView mTvAcel;
    TextView mTvGiro;
    TextView mTvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        mTvLight = (TextView) findViewById(R.id.tvLight);
        mTvAcel  = (TextView) findViewById(R.id.tvAcel);
        mTvGiro  = (TextView) findViewById(R.id.tvGiro);
        mTvTemp = (TextView) findViewById(R.id.tvTempe);

        //Todo . Accedemos al control de sensores pidiendo acceso al sistema a través de SensorManager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorList = new ArrayList<>();

        //Todo. Con TYPE_ALL nos devuelve todos los sensores.
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //Todo. Listar todos los sensores
        for(Sensor sensor : deviceSensors){
            Log.i("TAG", sensor.getName());
        }

    }

    @Override
     public void onSensorChanged(SensorEvent sensorEvent) {

        String values = "";
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                mTvLight.setText("Light: " + sensorEvent.values[0]);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:

                mTvTemp.setText("Temperatura: " + sensorEvent.values[0]);
                break;
            case Sensor.TYPE_GYROSCOPE:
                values = "x: " + sensorEvent.values[0] + "\n y: "+ sensorEvent.values[1]
                        + "\n z: "+sensorEvent.values[2];
                mTvGiro.setText("Giroscopio: " + values);
                break;
            case Sensor.TYPE_ACCELEROMETER:
                values = "x: " + sensorEvent.values[0] + "\n y: "+ sensorEvent.values[1]
                        + "\n z: "+sensorEvent.values[2];
                mTvAcel.setText("Acelerometro: " + values);
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //Todo. Es necesario registrar la escucha a los datos provistos por los sensores
    // todo -> teniendo en cuenta que este proceso consume una gran cantidad de recursos
    // todo -> por ello vamos a ir registrando la escucha y cancelandolas cuando nos haga falta.
    @Override
    protected void onResume() {
        super.onResume();

        //Todo. Ejemplo de acceso al sensor de luz
        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if( mSensor != null){
            mSensorList.add(mSensor);
        }else{
            Toast("No hay acceso al sensor de Luz!");
        }

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(mSensor != null){
            mSensorList.add(mSensor);
        }else{
            Toast("No hay acceso al sensor de acelerometro!");
        }

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mSensor != null){
            mSensorList.add(mSensor);
        }else{
            Toast("No hay acceso al sensor giroscopio!");
        }

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mSensor != null){
            mSensorList.add(mSensor);
        }else{
            Toast("No hay acceso al sensor temperatura!");
        }

        for(Sensor sensor : mSensorList){
            //Todo. Registramos el listener, el sensor y el tiempo de actualización (SENSOR_DELAY_UI)
            mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
        }


    }

    //Todo. Como hemos dicho es necesario parar el acceso a los sensores cuando no nos haga falta
    //todo -> por ejemplo, cuando la actividad no este en primer plano.
    @Override
    protected void onPause() {
        super.onPause();

        //Todo. Paro de escuchar todos los sensores.
        mSensorManager.unregisterListener(this);

        //Todo. ó para el sensor indicado
        //mSensorManager.unregisterListener(this, mSensorList.get(0));
    }




    public void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

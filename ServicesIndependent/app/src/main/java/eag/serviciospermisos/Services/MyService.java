package eag.serviciospermisos.Services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.widget.Toast;

public class MyService extends Service {


    private Thread t;

    @Override
    public void onCreate() {
        super.onCreate();

        //Todo 5. Se llama al metodo para donde podemos inicializar los objetos que nos haga falta,
        //Todo -> Si el servicio ya estuviera creado NO pasaría por aquí otra vez.
        //Todo -> Dependiendo de si el servicio está vinculado o pasa a tomar un camino u otro

        //Todo 7.1. Si queremos parar un servicio desde dentro del mismo
        //stopSelf();
        t = new Thread();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //TODO 10. Antes de eliminar el servicio, hay que parar el hilo
        if(t.isAlive()) {
            t.interrupt();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO - Método vinculado con bindService() y ligará el servicio al componente que realice la llamada y le permitirá interactuar con el

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {

        //TODO - Los componentes deben desligarse del servicio
        return super.onUnbind(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //TODO - Método vinculado con startService() y que llamará al servicio y dejará que se ejecute de forma separada

        //Todo 6. Despues se llama a este metodo
        Toast.makeText(this, "onStartCommand de MyService ejecutado!!", Toast.LENGTH_SHORT).show();




        //TODO 9: Recordemos que los servicios se siguen ejecutando en el hilo principal por lo que
        // Todo -> si queremos hacer tareas costosas necesitamos un hilo secundario.


            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    /// Ejecutamos la tarea costosa...
                    tareaLarga();

                    //Otra forma equivalente a runOnUiThread()...
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MyService.this, "Tarea larga finalizada!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            t.start();



        // Todo 8. En caso de que el sistema se esté quedando sin memoria, el valor de retorno
        // Todo -> Indica que queremos que suceda cuando el sistema vuelva a tener memoria disponible:
        //Todo -> Service.START_STICKY: El servicio se reiniciará pero el intent que recibe será null
        //Todo -> Service.START_NOT_STICKY: El servicio no se reiniciará aunque tenga memoria.
        //Todo -> Service.START_REDELIVER_INTENT: Lo mismo de START_STICKY pero con el intent original.

        return Service.START_STICKY;
    }


    private void tareaLarga(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

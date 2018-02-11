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

public class MainActivity extends AppCompatActivity {

    public static final int CODE_PERMISSION_GPS = 0;
    public static final int CODE_LOC_ON = 1;

    private TextView mTxtLong;
    private TextView mTxtLat;
    private TextView mTxtAlt;
    private TextView mTxtAdress;

    private Button mBtnStart;
    private Button mBtnStop;
    private Button mBtnLast;


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

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTxtAlt.setText("-");
                mTxtLat.setText("-");
                mTxtLong.setText("-");
                mTxtAdress.setText("-");
            }
        });

        mBtnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }



}

package eag.sqlitedam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mEtPobl;
    EditText mEtCity;
    EditText mEtSurf;
    TextView mTvView;
    Button mButton;
    Button mBtnMostrar;
    DBAccess mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton         = (Button)   findViewById(R.id.btnInsert);
        mBtnMostrar     = (Button)   findViewById(R.id.btnShow);
        mEtPobl = (EditText) findViewById(R.id.etPoblation);
        mEtSurf = (EditText) findViewById(R.id.etSurface);
        mEtCity = (EditText) findViewById(R.id.idCity);

        mTvView         = (TextView) findViewById(R.id.txtVw);

        mDB = new DBAccess(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = mEtCity.getText().toString();
                int pobl    = Integer.parseInt(mEtPobl.getText().toString());
                float surf   = Float.valueOf(mEtSurf.getText().toString());

                mDB.insert(city, pobl, surf);
            }
        });

        mBtnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvView.setText(mDB.getCity());
            }
        });



    }
}

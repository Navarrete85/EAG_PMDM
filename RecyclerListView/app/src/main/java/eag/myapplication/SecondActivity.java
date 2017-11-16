package eag.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    private RecyclerView                mRecyclerView;
    private RecHeroesAdapter            mAdapter;
    private RecyclerView.LayoutManager  mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mRecyclerView   =   (RecyclerView)  findViewById(R.id.recycler);
        mLayoutManager  =   new LinearLayoutManager(this);

        ArrayList<Heroes> heroesList = new ArrayList<>();

        heroesList.add(new Heroes("Lobezno", R.drawable.wolvie));
        heroesList.add(new Heroes("Spiderman", R.drawable.spiderman));
        heroesList.add(new Heroes("Hulk", R.drawable.hulk));

        mAdapter = new RecHeroesAdapter(heroesList);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "Elemento pulsado: "+ mRecyclerView.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }
}

package eag.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (ListView) findViewById(R.id.listVw);

        ArrayList<Heroes> heroesList = new ArrayList<>();

        heroesList.add(new Heroes("Lobezno", R.drawable.wolvie));
        heroesList.add(new Heroes("Spiderman", R.drawable.spiderman));
        heroesList.add(new Heroes("Hulk", R.drawable.hulk));

        HeroesAdapter mAdapter = new HeroesAdapter(this, R.layout.item_list, heroesList);
        mList.setAdapter(mAdapter);


    }

}

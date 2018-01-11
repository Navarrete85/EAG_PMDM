package eag.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JotaC on 15/10/17.
 */

public class HeroesAdapter extends ArrayAdapter{

    private ArrayList<Heroes> itemList;
    private Context mContext;


    public HeroesAdapter(Context context, int resourceId, ArrayList<Heroes> list){
        super(context, resourceId,list);

        this.mContext = context;
        this.itemList = list;
    }



    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return itemList.size();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);

        }


        TextView mTextView = (TextView) convertView.findViewById(R.id.tvName);
        ImageView mIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
        Heroes mHeroes = itemList.get(position);

        mIcon.setImageResource(mHeroes.getIdIcon());
        mTextView.setText(mHeroes.getName());


        return convertView;
    }
}

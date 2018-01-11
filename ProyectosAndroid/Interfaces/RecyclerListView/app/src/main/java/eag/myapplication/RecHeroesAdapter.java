package eag.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JotaC on 15/10/17.
 */

public class RecHeroesAdapter
        extends RecyclerView.Adapter<RecHeroesAdapter.HeroesViewHolder>
        implements View.OnClickListener{

    private ArrayList<Heroes> listHeroes;
    private View.OnClickListener mListener;

    public RecHeroesAdapter(ArrayList<Heroes> listHeroes) {
        this.listHeroes = listHeroes;
    }

    @Override
    public HeroesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);


        view.setOnClickListener(mListener);

        HeroesViewHolder viewHolder = new HeroesViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeroesViewHolder holder, int position) {
        holder.bindHeroesItem(listHeroes.get(position));
    }

    @Override
    public int getItemCount() {
        return listHeroes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onClick(v);
        }

    }

    public class HeroesViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public HeroesViewHolder(View itemView) {
            super(itemView);

            textView    = (TextView)    itemView.findViewById(R.id.tvName);
            imageView   = (ImageView)   itemView.findViewById(R.id.ivIcon);
        }

        public void bindHeroesItem(Heroes item){
            textView.setText(item.getName());
            imageView.setImageResource(item.getIdIcon());
        }

    }


}

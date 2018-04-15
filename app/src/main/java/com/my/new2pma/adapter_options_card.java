/**
 * Created by ashwini on 1/30/2018.
 */
package com.my.new2pma;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public  class adapter_options_card extends RecyclerView.Adapter<adapter_options_card.MyViewHolder>{


    private List<Integer> categoriesList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       public ImageView categoryimage;


        public MyViewHolder(View view) {
            super(view);
            this.categoryimage=(ImageView) view.findViewById(R.id.imageButton);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext,"you opted for options",Toast.LENGTH_SHORT).show();
        }
    }
    public adapter_options_card(List<Integer> categories, Context context) {
        this.mContext = context;

        this.categoriesList = categories;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_for_option, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int  tempobj=categoriesList.get(position);
        holder.categoryimage.setImageResource(tempobj);



    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}

package com.my.new2pma;

/**
 * Created by ashwini on 1/9/2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public  class adapterlanderlist extends RecyclerView.Adapter<adapterlanderlist.MyViewHolder> {


    private List<Lenders> categoriesList;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Amount,Description;
        public ImageView categoryimage;


        public MyViewHolder(View view) {
            super(view);
            this.Amount = (TextView) view.findViewById(R.id.amountview);
            this.Description=(TextView) view.findViewById(R.id.description);
            this.categoryimage=(ImageView) view.findViewById(R.id.categoryImage);
        }
    }
    public adapterlanderlist(List<Lenders> categories, Context context) {
        this.mContext = context;

        this.categoriesList = categories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_for_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Lenders tempobj=categoriesList.get(position);
        holder.Description.setText(tempobj.getName());
        String amount=Integer.toString(tempobj.getAmount());
        holder.Amount.setText(amount);
        holder.categoryimage.setImageResource(tempobj.getImageresourceId());

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}

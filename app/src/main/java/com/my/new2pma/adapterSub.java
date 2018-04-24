package com.my.new2pma;

/**
 * Created by ashwini on 1/9/2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.my.new2pma.R.id.toolbar;

public  class adapterSub extends RecyclerView.Adapter<adapterSub.MyViewHolder> {


    private List<ExpenseCategory> categoriesList;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Amount,Description;
        public ImageView categoryimage;
       // public Toolbar toolbar;


        public MyViewHolder(View view) {
            super(view);
            //this.toolbar=(Toolbar) view.findViewById(R.id.card_toolbar);
            this.Amount = (TextView) view.findViewById(R.id.amountview);
            this.Description=(TextView) view.findViewById(R.id.description);
            this.categoryimage=(ImageView) view.findViewById(R.id.categoryImage);
        }
    }
    public adapterSub(List<ExpenseCategory> categories, Context context) {
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
        ExpenseCategory tempobj=categoriesList.get(position);
        holder.Description.setText(tempobj.getName());
        String amount=Integer.toString(tempobj.getAmount());
        holder.Amount.setText(amount);
        holder.categoryimage.setImageResource(tempobj.getImageSourceId());
       // holder.toolbar.inflateMenu(R.menu.card_toolbar);
        /*holder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(, "menu item click listner is working",Toast.LENGTH_SHORT).show();
                Log.w("CardMenu:","My onClick listener is working properly");

                return false;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}

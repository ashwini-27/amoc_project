package com.my.new2pma;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ajay on 1/28/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] imageresource={R.drawable.logo,R.drawable.background,R.drawable.nit_surat};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return imageresource.length;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=(View) object;
        ViewPager vp=(ViewPager)container;
        vp.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView slide=(ImageView) view.findViewById(R.id.slider);
        slide.setImageResource(imageresource[position]);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"imge"+position+"clicked",Toast.LENGTH_SHORT).show();

            }


        });
        ViewPager vp=(ViewPager) container;
        vp.addView(view);
        return view;
    }
}

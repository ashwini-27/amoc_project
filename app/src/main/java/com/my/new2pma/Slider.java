package com.my.new2pma;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Slider extends Fragment {

ViewPager viewPager;
    public Slider() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // we will get notification in this view pager from the server
        View view=inflater.inflate(R.layout.fragment_slider, container, false);

        viewPager=(ViewPager) view.findViewById(R.id.pagerslider);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
       Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MytimerTask(),2000,10000);
        return view;
    }
    public  class MytimerTask extends TimerTask{
    @Override
        public void run() {
        getActivity().runOnUiThread(new Runnable(){

            @Override
            public void run() {
                int currentitem=viewPager.getCurrentItem();
                viewPager.setCurrentItem((currentitem+1)%3);

            }
        });

        }
    }

}

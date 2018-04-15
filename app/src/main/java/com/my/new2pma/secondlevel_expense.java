/*package com.my.new2pma;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.

public class secondlevel_expense extends Fragment {


    public secondlevel_expense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_secondlevel_expense, container, false);;
        try {

            ViewPager viewPager = (ViewPager) view.findViewById(R.id.expensegraph);
            RecyclerView options;
            options = (RecyclerView) view.findViewById(R.id.expenseoption);
            ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getContext());
            viewPager.setAdapter(pagerAdapter);
            List<Integer> imageresources = new ArrayList<Integer>();
            imageresources.add(R.drawable.attendance);//get category total

            imageresources.add(R.drawable.expense_icon_final);// get person wise total
            imageresources.add(R.drawable.todo_final);// ADD NEW EXPENSE
            FloatingActionButton addexpense = (FloatingActionButton) view.findViewById(R.id.addexpense);
            addexpense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getChildFragmentManager();
                    fm.beginTransaction().replace(R.id.totallister, new expense_add()).addToBackStack(null).commit();
                }

            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            options.setLayoutManager(mLayoutManager);
            options.setItemAnimator(new DefaultItemAnimator());
            adapter_options_card Adapter_options_card = new adapter_options_card(imageresources, getContext());
            options.setAdapter(Adapter_options_card);
            FragmentManager totallisterManager = getChildFragmentManager();
            totallisterManager.beginTransaction().add(R.id.totallister, new TotalListerFragment()).addToBackStack(null).commit();



            options.addOnItemTouchListener(new  RecyclerItemClickListener(getActivity(),options, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {


                    switch (position) {
                        case 1:
                            Toast.makeText(getContext(),"you clicked the option no 1",Toast.LENGTH_LONG).show();
                            FragmentManager ft = getFragmentManager();
                            ft.beginTransaction().replace(R.id.contentdefault, (Fragment) new TotalListerFragment()).addToBackStack(null).commit();break;

                        case 2:
                            Bundle data= new Bundle();
                            data.putInt("position",position);
                            Fragment fragment=new landerlist();
                            fragment.setArguments(data);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();

                        case 3:

                            //getActivity().getActionBar().setTitle("TOdoactivity");
                            break;
                    }

                }


                @Override
                public void onLongItemClick(View view, int position) {

                }

            }));
        }
            catch (Exception e) {
            Toast.makeText(getContext(), "issue "+e, Toast.LENGTH_SHORT).show();

        }


        return view;
    }
}*/


package com.my.new2pma;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class secondlevel_expense extends Fragment {


    public secondlevel_expense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_secondlevel_expense, container, false);;
        try {

            ViewPager viewPager = (ViewPager) view.findViewById(R.id.expensegraph);
            RecyclerView options;
            options = (RecyclerView) view.findViewById(R.id.expenseoption);
            ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getContext());
            viewPager.setAdapter(pagerAdapter);
            List<Integer> imageresources = new ArrayList<Integer>();
            imageresources.add(R.drawable.expense_icon_final);
            imageresources.add(R.drawable.todo_final);
            FloatingActionButton addexpense = (FloatingActionButton) view.findViewById(R.id.addexpense);
            addexpense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getChildFragmentManager();
                    fm.beginTransaction().replace(R.id.totallister, new expense_add()).addToBackStack(null).commit();
                }

            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            options.setLayoutManager(mLayoutManager);
            options.setItemAnimator(new DefaultItemAnimator());
            adapter_options_card Adapter_options_card = new adapter_options_card(imageresources, getContext());
            options.setAdapter(Adapter_options_card);
            FragmentManager totallisterManager = getChildFragmentManager();
            totallisterManager.beginTransaction().add(R.id.totallister, new TotalListerFragment()).addToBackStack(null).commit();

            options.addOnItemTouchListener(new  RecyclerItemClickListener(getActivity(),options, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {


                    switch (position) {
/*                        case 1:
                           // Toast.makeText(getContext(),"you clicked the option no 1",Toast.LENGTH_LONG).show();
                            FragmentManager ft = getFragmentManager();
                            ft.beginTransaction().replace(R.id.contentdefault, (Fragment) new TotalListerFragment()).addToBackStack(null).commit();break;
*/
                        case 1:
                            Bundle data= new Bundle();
                            data.putInt("position",position);
                            Fragment fragment=new landerlist();
                            fragment.setArguments(data);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();

                        case 2:

                            //getActivity().getActionBar().setTitle("TOdoactivity");
                            break;

                    }

                }

                @Override
                public void onLongItemClick(View view, int position) {

                }


            })); }
            catch (Exception e) {
            Toast.makeText(getContext(), "issue "+e, Toast.LENGTH_SHORT).show();

        }
        return view;
    }
}



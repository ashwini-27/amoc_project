package com.my.new2pma;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class secondlevel_todo extends Fragment {


    public secondlevel_todo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_secondlevel_todo, container, false);
        RecyclerView options=(RecyclerView)view.findViewById(R.id.todooptions);

        List<Integer> imageresources=new ArrayList<Integer>();
        imageresources.add(R.drawable.attendance);
        imageresources.add(R.drawable.expense_icon_final);

        imageresources.add(R.drawable.todo_final);

        imageresources.add(R.drawable.attendance);
        imageresources.add(R.drawable.expense_icon_final);

        try {


            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            options.setLayoutManager(mLayoutManager);
            options.setItemAnimator(new DefaultItemAnimator());

            adapter_options_card adapter_options_card = new adapter_options_card(imageresources, getContext());
            options.setAdapter(adapter_options_card);
            Fragment graphfrag = new graph();
            getChildFragmentManager().beginTransaction().replace(R.id.graphfrag, graphfrag).addToBackStack(null).commit();
            Bundle data=new Bundle();
            data.putInt("position",0);
            Fragment fragment= (Fragment) new thirdleveltodo_options();
            fragment.setArguments(data);
            getChildFragmentManager().beginTransaction().replace(R.id.totallister, fragment ).addToBackStack(null).commit();




        }
        catch(Exception e)
        {
            Toast.makeText(getContext(),"there is still an error with the graph"+e,Toast.LENGTH_LONG).show();
        }


        options.addOnItemTouchListener(new  RecyclerItemClickListener(getActivity(),options, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                switch (position) {
                    case 4:
                        //Toast.makeText(getContext(),"you clicked the option no 5",Toast.LENGTH_LONG).show();
                        FragmentManager ft = getFragmentManager();
                        ft.beginTransaction().replace(R.id.contentdefault, (Fragment) new thirdlevel_todoadd()).addToBackStack(null).commit();break;
                    case 1:
                    case 2:
                    case 3:
                    case 0:
                    case 5:

                        //Toast.makeText(getContext(),"you wish to use todo options",Toast.LENGTH_LONG).show();
                        FragmentManager fw = getFragmentManager();
                        Bundle data=new Bundle();
                        data.putInt("position",position);
                        Fragment fragment= (Fragment) new thirdleveltodo_options();
                        fragment.setArguments(data);
                        fw.beginTransaction().replace(R.id.contentdefault, fragment ).addToBackStack(null).commit();
                        //getActivity().getActionBar().setTitle("TOdoactivity");
                        break;
                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        /* put all this in recycler click listener
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {


            }

        };
        lister.setOnItemClickListener(listener);*/

return  view;
    }
}

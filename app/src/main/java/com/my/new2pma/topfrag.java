package com.my.new2pma;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import static com.my.new2pma.R.string.app_name;


/**
 * A simple {@link Fragment} subclass.
 */
public class topfrag extends Fragment {


    public topfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view= inflater.inflate(R.layout.fragment_topfrag, container, false);








        ViewPager viewPager=(ViewPager) view.findViewById(R.id.notification_front);
        RecyclerView options;/*subjectList*/
        options=(RecyclerView) view.findViewById(R.id.recycler_view_for_option_card);
        //subjectList=(RecyclerView) view.findViewById(R.id.first_Level_AttendenceView);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getContext());
        viewPager.setAdapter(pagerAdapter);
       /* List<Subjects> subjects=new ArrayList<Subjects>();
        subjects.add(new Subjects("dbms",2,3,R.drawable.logo));
        subjects.add(new Subjects("dsa",4,5,R.drawable.nit_surat));
        subjects.add(new Subjects("dms",4,5,R.drawable.expense_icon_final));*/
        List<Integer> imageresources=new ArrayList<Integer>();
        imageresources.add(R.drawable.attendance);
        imageresources.add(R.drawable.expense_icon_final);

        imageresources.add(R.drawable.todo_final);


        try {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            options.setLayoutManager(mLayoutManager);
            options.setItemAnimator(new DefaultItemAnimator());

            adapter_options_card adapter_options_card = new adapter_options_card(imageresources, getContext());
            options.setAdapter(adapter_options_card);

            options.addOnItemTouchListener(
                    new RecyclerItemClickListener(getActivity(),options, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (position) {
                                case 0:
                                    /*FragmentManager fm = getFragmentManager();
                                    fm.beginTransaction().replace(R.id.contentdefault, (Fragment) new topfrag()).addToBackStack(null).commit();
                                    */
                                    int f = Integer.valueOf(android.os.Build.VERSION.SDK);
                                    if (f >= 24) {
                                        Intent newact1 = new Intent(getContext(), Main5Activity.class);
                                        startActivity(newact1);
                                    }
                                    else
                                    {
                                        Intent newact1 = new Intent(getContext(), Main8Activity.class);
                                        startActivity(newact1);
                                    }
                                    break;
                                case 1:
                                        FragmentManager ft = getFragmentManager();
                                        ft.beginTransaction().replace(R.id.contentdefault, (Fragment) new secondlevel_expense()).addToBackStack(null).commit();


                                    break;
                                case 2:
                                    FragmentManager fp = getFragmentManager();
                                    fp.beginTransaction().replace(R.id.contentdefault, (Fragment) new secondlevel_todo()).addToBackStack(null).commit();



                            }
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    }
                    ));


           /* RecyclerView.LayoutManager m1LayoutManager = new LinearLayoutManager(getContext());
            subjectList.setLayoutManager(m1LayoutManager);
            subjectList.setItemAnimator(new DefaultItemAnimator());
            Subject_List_Adapter subject_list_adapter = new Subject_List_Adapter(subjects, getContext());
            subjectList.setAdapter(subject_list_adapter);*/
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"there is an error"+e,Toast.LENGTH_SHORT).show();
        }
      //  ImageButton update=(ImageButton) options.findViewById(R.id.imageButton);
        /*View.OnClickListener updater=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense();
            }
        };*/
       // update.setOnClickListener(updater);









       /* we will add this to recycler vie onitem click
        AdapterView.OnItemClickListener listListener =new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                Intent intent;
                switch(position){
                    case 0:
                       // intent=new Intent(First.this,SecondLevel_Attendence.class);
                       // startActivity(intent);
                        FragmentManager fm=getFragmentManager();
                        fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new topfrag()).addToBackStack(null).commit();
                      //  getActivity().getActionBar().setTitle("Attendence will come here");
                        break;
                    case 1:
                        //intent=new Intent(First.this,second_level_expenses.class);
                        //startActivity(intent);

                        FragmentManager ft=getFragmentManager();
                        ft.beginTransaction().replace(R.id.contentdefault, (Fragment)new secondlevel_expense()).addToBackStack(null).commit();

                        ((first) getActivity()).getSupportActionBar().setTitle("EXPENSE");
                        break;
                    case 2:
                       // intent=new Intent(First.this,second_level_todos.class);
                       // startActivity(intent);
                        FragmentManager fp=getFragmentManager();
                        fp.beginTransaction().replace(R.id.contentdefault, (Fragment)new secondlevel_todo()).addToBackStack(null).commit();

                       /* mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
                        setSupportActionBar(mActionBarToolbar);*/
                     //   ((first) getActivity()).getSupportActionBar().setTitle("TODO");
                       // getActivity().getActionBar().setTitle("EXPENSEactivity");

                       // break;
                //}
               /* TextView text=(TextView) view.findViewById(R.id.textfirst);
                text.setText("you pressed the item no"+Integer.toString(position+1));*/
/*
            }
        };
        ListView listView=(ListView) view.findViewById(R.id.listfirst);
        listView.setOnItemClickListener(listListener);*/

        return view;

    }
/*
    public  void expense()
    {
        FragmentManager fm=getFragmentManager();
        fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new topfrag()).addToBackStack(null).commit();
    }*/
    @Override
    public  void onResume() {
        try {
            ((first) getActivity()).getSupportActionBar().setTitle(app_name);
        } catch (Exception e) {
            Log.e("action bar error::", String.valueOf(e));
        }
        super.onResume();
    }

}

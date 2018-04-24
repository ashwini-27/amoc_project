package com.my.new2pma;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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
public class landerlist extends Fragment {


    private RecyclerView recyclerView;
    private adapterlanderlist mAdapter;
    private List<ExpenseCategory> categoryList= new ArrayList<ExpenseCategory>();
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public landerlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle data=this.getArguments();
        int position= data.getInt("position",0);
        View view= inflater.inflate(R.layout.lenderlist, container, false);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        try {
             mAdapter = new adapterlanderlist(getlenders(position), getContext());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
        }
        catch (Exception e) {
            Toast.makeText(getContext(), "there is still issue with the adapter" + e, Toast.LENGTH_LONG).show();
        }
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    //    Toast.makeText(getContext(),"onclickListener is working\n now you can add your function into it",Toast.LENGTH_SHORT).show();

                        //Fragment fragment=new TotalListerFragment();
                        //fragment.setArguments(data);
                        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                     //   Toast.makeText(getContext(),"onclickListener is working\n now you can add youtr function into it",Toast.LENGTH_SHORT).show();

                    }
                })
        );
        return view;

    }




    private List<Lenders> getlenders(int position) {

        SQLiteOpenHelper exp1 = new db(getContext());
        SQLiteDatabase db = exp1.getReadableDatabase();

        List<Lenders> temp=new ArrayList<Lenders>();
        String table = "EXPENSES";
        String selection="FOOD=?";
        String[] columns = new String[] { "AMOUNT","DESCRIPTION" };
        switch(position) {

            case 0:
                selection = "FOOD=?";

                break;
            case 1:
                selection = "NAME=? And LEND=?";
                break;
            case 2:
                selection = "STUDYMATERIAL=?";
                break;
            case 3:
                selection = "RECREATIONAL=?";
                break;
            case 4:
                selection = "NAME=? And BORROW=?";
                break;

        }
        String arguments ;
        String groupBy =null;
        String having = null;
        String orderBy =null;
        int tem=0;
        Cursor cursor;
        if(position==1||position==4) {
            Cursor name = db.query("EXPENSES", new String[]{"DISTINCT Name"}, null, null, null, null, null);

            if (name.moveToFirst()) {

                arguments=name.getString(0);

                cursor=db.query(table, columns, selection, new String[]{arguments,"1"}, groupBy, having, orderBy);
                cursor.moveToFirst();
                if (cursor.moveToFirst()) {
                    tem+=cursor.getInt(0);
                    while (cursor.moveToNext()) {
                        tem+=cursor.getInt(0);
                    }

                }
                temp.add(new Lenders(arguments,tem));
                cursor.close();
                tem=0;
                while (name.moveToNext()) {
                    arguments=name.getString(0);

                    cursor=db.query(table, columns, selection, new String[]{arguments,"1"}, groupBy, having, orderBy);
                    cursor.moveToFirst();
                    if (cursor.moveToFirst()) {
                        tem+=cursor.getInt(0);
                        while (cursor.moveToNext()) {
                            tem+=cursor.getInt(0);
                        }

                    }
                    temp.add(new Lenders(arguments,tem));
                    cursor.close();
                    tem=0;}

                name.close();

            }
        }
        else {

            cursor=db.query(table, columns, selection, new String[]{"1"}, groupBy, having, orderBy);
            if (cursor.moveToFirst()) {
                temp.add(new Lenders(cursor.getString(1),cursor.getInt(0)));
                while (cursor.moveToNext()) {
                    temp.add(new Lenders(cursor.getString(1),cursor.getInt(0)));
                }

            }

        }

        return temp;

    }
    }




    /*private List<Lenders> getborrowers()
    {
        SQLiteOpenHelper exp1 = new db(getContext());
        SQLiteDatabase db = exp1.getReadableDatabase();

        Cursor name=db.query("EXPENSES",new String[] {"DISTINCT Name"},null,null,null,null,null);
        List<Lenders> temp=new ArrayList<Lenders>();
        String table = "EXPENSES";
        String[] columns = new String[] { "AMOUNT" };
        String selection = "NAME=? And BORROW=?";
        String arguments = null;
        String groupBy =null;
        String having = null;
        String orderBy =null;
        int tem=0;
        Cursor cursor;
        if (name.moveToFirst()) {

            arguments=name.getString(0);

            cursor=db.query(table, columns, selection, new String[]{arguments,"1"}, groupBy, having, orderBy);
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                tem+=cursor.getInt(0);
                while (cursor.moveToNext()) {
                    tem+=cursor.getInt(0);
                }

            }
            temp.add(new Lenders(arguments,tem));
            cursor.close();
            tem=0;
            while (name.moveToNext()) {
                arguments=name.getString(0);

                cursor=db.query(table, columns, selection, new String[]{arguments,"1"}, groupBy, having, orderBy);
                cursor.moveToFirst();
                if (cursor.moveToFirst()) {
                    tem+=cursor.getInt(0);
                    while (cursor.moveToNext()) {
                        tem+=cursor.getInt(0);
                    }

                }
                temp.add(new Lenders(arguments,tem));
                cursor.close();
                tem=0;}

        }
        name.close();
        return temp;
    }*/










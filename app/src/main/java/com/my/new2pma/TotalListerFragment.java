package com.my.new2pma;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalListerFragment extends Fragment {


    private RecyclerView recyclerView;
    private adapterSub mAdapter;
    int foodTotal=0,MoviesTotal=0,lendTotal=0,borrowTotal=0,studyTotal=0;
    private List<ExpenseCategory> categoryList= new ArrayList<ExpenseCategory>();
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public TotalListerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        categoryList.clear();
       View view= inflater.inflate(R.layout.fragment_total_lister, container, false);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        try {
            foodTotal=0;MoviesTotal=0;lendTotal=0;borrowTotal=0;studyTotal=0;
            preparedata();
            mAdapter = new adapterSub(categoryList, getContext());
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
                     //   Toast.makeText(getContext(),"onclickListener is working\n now you can add youtr function into it",Toast.LENGTH_SHORT).show();
                        Bundle data= new Bundle();
                        data.putInt("position",position);
                        Fragment fragment=new landerlist();
                        fragment.setArguments(data);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }


    protected  void preparedata() {

//Toast.makeText(getContext(),"prepare data fuction is caleed",Toast.LENGTH_SHORT).show();
        try {
            SQLiteOpenHelper exp0 = new db(getContext());
            SQLiteDatabase db1 = exp0.getReadableDatabase();
            Cursor cursor = db1.query("EXPENSES",
                    new String[]{"AMOUNT", "FOOD", "LEND", "STUDYMATERIAL", "RECREATIONAL", "BORROW"},
                    null,
                    null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                counter(cursor);
                while (cursor.moveToNext()) {
                    counter(cursor);
                }

            }
            cursor.close();
            db1.close();
            categoryList.add(new ExpenseCategory("FOOD",foodTotal,R.drawable.ic_menu_gallery));
            categoryList.add(new ExpenseCategory("Lend",lendTotal,R.drawable.ic_menu_camera));
            categoryList.add(new ExpenseCategory("Study",studyTotal,R.drawable.ic_menu_send));
            categoryList.add(new ExpenseCategory("Movies",MoviesTotal,R.drawable.ic_menu_share));
            categoryList.add(new ExpenseCategory("BORROW",borrowTotal,R.drawable.ic_menu_manage));


        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "there is an issue within the toatal function"+e, Toast.LENGTH_SHORT).show();
        }
    }
    private void counter( Cursor cursor) {
        if (cursor.getInt(1) == 1) {
            foodTotal += cursor.getInt(0);
        }
        if (cursor.getInt(2) == 1) {
            lendTotal += cursor.getInt(0);
        }
        if (cursor.getInt(3) == 1) {
            studyTotal += cursor.getInt(0);
        }
        if (cursor.getInt(4) == 1) {
            MoviesTotal += cursor.getInt(0);
        }
        if (cursor.getInt(5) == 1) {
            borrowTotal += cursor.getInt(0);
        }
    }




private void getlenders()
{   SQLiteOpenHelper exp1 = new db(getContext());
    SQLiteDatabase db = exp1.getReadableDatabase();

    Cursor name=db.query("Lenders",new String[] {"Name"},null,null,null,null,null);
    List<Lenders> temp=new ArrayList<Lenders>();
    String table = "Expenses";
    String[] columns = new String[] { "AMOUNT" };
    String selection = "NAME=? And LEND=?";
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
}


}


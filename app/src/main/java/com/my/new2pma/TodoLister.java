package com.my.new2pma;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by ajay on 2/3/2018.
 */

public class TodoLister extends RecyclerView.Adapter<TodoLister.MyViewHolder> {


    private List<todo_shower> dataList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView deadline,descri;
        Toolbar toolbar;
        CardView todocard;
            ImageButton pop;

        public MyViewHolder(View view) {
            super(view);
            this.deadline= (TextView) view.findViewById(R.id.deadlineDate);
            this.descri=(TextView) view.findViewById(R.id.todoshow);
            this.toolbar=(Toolbar) view.findViewById(R.id.menu_for_todo);
            this.todocard=(CardView) view.findViewById(R.id.todocard);

        }

        @Override
        public void onClick(View v) {
          //  Toast.makeText(mContext,"you opted for options",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public TodoLister.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_lister, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final todo_shower tempobj=dataList.get(position);
        holder.descri.setText(tempobj.getDescription());
        holder.deadline.setText(tempobj.getDeadline());
        holder.todocard.setTag(tempobj.id);
        if (holder.toolbar != null) {
            // inflate your menu
            holder.toolbar.inflateMenu(R.menu.card_toolbar);
            holder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {


                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {


                    final String olddiscri=holder.descri.getText().toString();

                     final int id= (int) holder.todocard.getTag();
                    switch(menuItem.getItemId())
                    {
                        case R.id.first:
                            final Dialog openDialog = new Dialog(mContext);
                            openDialog.setContentView(R.layout.customdialog_layout);
                            openDialog.setTitle("Custom Dialog Box");
                            final TextView dialogTextContent = (TextView)openDialog.findViewById(R.id.content);
                            dialogTextContent.setText(holder.descri.getText());
                            Button dialogCloseButton = (Button)openDialog.findViewById(R.id.dialog_button);
                            dialogCloseButton.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    holder.descri.setText(dialogTextContent.getText());
                                    openDialog.dismiss();
                                    try {



                                        db dbhelper = new db(mContext);
                                        SQLiteDatabase expdb = dbhelper.getWritableDatabase();

                                        ContentValues updated_todo = new ContentValues();
                                        updated_todo.put("DATE_DEADLINE", holder.deadline.getText().toString());
                                        updated_todo.put("DESCRIPTION", holder.descri.getText().toString());


                                            String s=holder.descri.getText().toString();
                                        expdb.execSQL("UPDATE TODO SET DESCRIPTION='"+s+"' WHERE _id="+id+" ");
                                        expdb.close();



                                    }
                                    catch (Exception e) {
                                        Toast.makeText(mContext,"error in update"+e,Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                            openDialog.show();






                            // edit todo
                            break;
                        case R.id.second:
                            try {



                                final Calendar myCalendar=Calendar.getInstance();
                                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                          int dayOfMonth) {
                                        // TODO Auto-generated method stub
                                        myCalendar.set(Calendar.YEAR, year);
                                        myCalendar.set(Calendar.MONTH, monthOfYear);
                                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                        String d=updateLabel(myCalendar);


                                        holder.deadline.setText(d);

                                    }

                                };
                                new DatePickerDialog(mContext,0, date, myCalendar
                                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();






                                db dbhelper = new db(mContext);
                                SQLiteDatabase expdb = dbhelper.getWritableDatabase();

                                ContentValues updated_todo = new ContentValues();
                                updated_todo.put("DATE_DEADLINE", holder.deadline.getText().toString());
                                updated_todo.put("DESCRIPTION", holder.descri.getText().toString());


                                String s = holder.descri.getText().toString();
                                expdb.execSQL("UPDATE TODO SET DATE_DEADLINE='" + s + "' WHERE _id=" + id + " ");
                                expdb.close();
                            }
                            catch (Exception e)
                            {
                                                Toast.makeText(mContext,"error in deletion"+e,Toast.LENGTH_SHORT).show();
                            }



                            break;
                        case R.id.third:



                            db dbhelper = new db(mContext);
                            SQLiteDatabase expdb = dbhelper.getWritableDatabase();
                            String table = "TODO";
                            String whereClause = "_id=?";
                            String[] whereArgs = new String[] { String.valueOf(id) };
                            expdb.delete(table, whereClause, whereArgs);

                            break;
                    }

                                       return true;
                }
            });
        }
        /*holder.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.pop);

                popupMenu.inflate(R.menu.card_toolbar);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId())
                        {
                            case R.id.first:
                                break;
                            case R.id.second:
                                break;
                            case R.id.third:
                                break;
                        }


                        return false;
                    }
                });
            }
        });*/
    }

    private String updateLabel(Calendar myCalendar) {
        String d="ngf,ng";
        try {
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            d= (sdf.format(myCalendar.getTime()));
        } catch (Exception e) {
            Toast tb = Toast.makeText(mContext, "issue is in update label function and the issue is\n\n" + e, Toast.LENGTH_LONG);
            tb.show();
        }
        return d;
    }


    public TodoLister(List<todo_shower> categories, Context context) {
        this.mContext = context;

        this.dataList = categories;

    }
    @Override
    public int getItemCount() {

        return dataList.size();
    }
}






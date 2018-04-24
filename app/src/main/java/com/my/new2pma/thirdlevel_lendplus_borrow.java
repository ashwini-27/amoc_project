package com.my.new2pma;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;


public class thirdlevel_lendplus_borrow extends Fragment {
    public thirdlevel_lendplus_borrow() {
        // Required empty public constructor
    }
    int position;
    EditText editText;
    Calendar myCalendar=Calendar.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {


            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }
        catch(Exception e){
            Log.d("lendplusborroe","error in oncreate method",e);
        }
    }
    public View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  final View view;
        try{

        if (position == 0)
        {
                    view= inflater.inflate(R.layout.fragment_thirdlevel_lendplus_borrow, container, false);
        }
        else
        {
                    view= inflater.inflate(R.layout.option_plus_borrow, container, false);
        }
        Button updater= (Button)view.findViewById(R.id.updater);

        updater.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           update( view );
                                       }

                                       }
                                  );
        }
        catch (Exception e)
        {
            Log.d("Error","inside oncreate view function",e);
        }


        // myCalendar = Calendar.getInstance();
        try {
            editText = (EditText) view.findViewById(R.id.date);
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();

                }

            };

            editText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(),"error in datepicker dialoge"+e,Toast.LENGTH_SHORT);
            Log.d("error in edittext","there is an error",e);
        }

        return view;
    }



    public void update(View view) {
        try {
          //  Toast tb=Toast.makeText(getContext(),"update function called\n",Toast.LENGTH_SHORT);
           // tb.show();

            db dbhelper = new db(getContext());
            SQLiteDatabase expdb = dbhelper.getWritableDatabase();
            // DatePicker date1 = (DatePicker) findViewById(R.id.datePicker);
            EditText amountview = (EditText) view.findViewById(R.id.amount);
            String a = amountview.getText().toString();
            boolean borrowed = false;
            boolean lend = false;
            String se = "a";
            if (position != 0) {
                RadioButton checkedTextView= (RadioButton) view.findViewById(R.id.checkedTextView2);
                borrowed = checkedTextView.isChecked();
            } else {
                ToggleButton toggle = (ToggleButton) view.findViewById(R.id.toggleButton);
                se = toggle.getText().toString();
                if (se.equals("BORROWED")) {
                    borrowed = true;
                } else if (se.equals("LEND")) {
                    lend = true;
                }
                //i will put some code for len borrow activity
            }

            int amount = Integer.parseInt(a);
            EditText name = (EditText) view.findViewById(R.id.name);
            String c = name.getText().toString();

            EditText des = (EditText) view.findViewById(R.id.description);
            String b = des.getText().toString();
            boolean food = false;
            boolean recreational = false;
            boolean study = false;

            //long das = Long.parseLong(date1.toString());
            switch (position) {
                case 1:
                    food = true;
                    break;
                case 2:
                    recreational = true;
                    break;
                case 3:
                    study = true;
                    break;
            }

            //from here the copied <code></code>
            boolean success;
            // success=dbhelper.insertdb(expdb, amount, b, c,lend, borrowed,food, recreational, study);
            //     expdb.setTransactionSuccessful();
            try {


                c=c.toUpperCase();
                ContentValues lenders=new ContentValues();
                lenders.put("Name",c);
                try
                {

                    expdb.insertOrThrow("EXPENSES", null, lenders);
                }
                catch (Exception e)
                {
                    Log.w("name","name might already exist");
                }
                ContentValues drinkValues = new ContentValues();
                drinkValues.put("AMOUNT", amount);
                drinkValues.put("NAME",c);
                drinkValues.put("DESCRIPTION",b);
                drinkValues.put("LEND", lend);
                drinkValues.put("BORROW", borrowed);
                drinkValues.put("FOOD", food);
                drinkValues.put("STUDYMATERIAL", study);
                //drinkValues.put("E_D",das);
                drinkValues.put("RECREATIONAL", recreational);
                //db.insert("EXPENSES", null, drinkValues);
                //lets see the new changes
                //drinkValues.put("E_D", das);
                drinkValues.put("RECREATIONAL", recreational);
                //expdb.beginTransaction();
                expdb.insert("EXPENSES", null, drinkValues);
                expdb.close();

                success= true;
            }
            catch (Exception e) {
                success= false;
            }

          /*  if(success)
            {
                Toast te=Toast.makeText(getContext(),"Database updated hurrey!!!!!!!!!!\n",Toast.LENGTH_LONG);
                tb.show();
            }*/

        }
        catch (Exception e)
        {
            Toast tb=Toast.makeText(getContext(),"Database issue so cant be updated and the issue id\n"+e,Toast.LENGTH_LONG);
            tb.show();
        }
    }
    private void updateLabel() {
        try {
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            editText.setText(sdf.format(myCalendar.getTime()));
        } catch (Exception e) {
            Toast tb = Toast.makeText(getContext(), "issue is in update label function and the issue is\n\n" + e, Toast.LENGTH_LONG);
            tb.show();
        }
    }
}

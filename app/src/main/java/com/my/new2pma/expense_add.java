package com.my.new2pma;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class expense_add extends Fragment {
    Calendar myCalender=Calendar.getInstance();
    EditText dte,amount,name,description;
    TextView cat;
    Button add;
    Spinner category;
    RadioButton borrow;
    public expense_add() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_expense_add, container, false);

         dte= (EditText) view.findViewById(R.id.date);
         amount=(EditText) view.findViewById(R.id.amount);
         name=(EditText) view.findViewById(R.id.name);
         description=(EditText) view.findViewById(R.id.description);
         add=(Button) view.findViewById(R.id.add);
        borrow=(RadioButton) view.findViewById(R.id.borrowed);
        category=(Spinner) view.findViewById(R.id.spinner);
        cat=(TextView) view.findViewById(R.id.category);
        cat.setText("@string/category");



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, monthOfYear);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date;
                date=dayOfMonth+"/"+monthOfYear+"/"+year;
                dte.setText(date);

            }

        };

        dte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(),0, date, myCalender
                        .get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        return view;
    }

    private void update() {



            try {
                /*Toast tb=Toast.makeText(getContext(),"update function called\n",Toast.LENGTH_SHORT);
                tb.show();*/

                db dbhelper = new db(getContext());
                SQLiteDatabase expdb = dbhelper.getWritableDatabase();
                String a = amount.getText().toString();
                boolean borrowed;
                boolean lend = false;


                int amount = Integer.parseInt(a);
                String c = name.getText().toString();
                String b = description.getText().toString();
                boolean food = false;
                boolean recreational = false;
                boolean study = false;
                String op=category.getSelectedItem().toString().toUpperCase();
                cat.setText(op);

                if(op.equals("LEND"))
                {

                    lend=true;
                }
                else if(op.equals("FOOD"))
                {
                    food=true;
                }
                else if(op.equals("RECREATIONAL"))
                {

                    recreational=true;
                }
                else if(op.equals("STUDYMATERIAL"))
                {
                    study=true;

                }
                borrowed=borrow.isChecked();
                boolean success;
                try {


                    c=c.toUpperCase();
                    ContentValues lenders=new ContentValues();
                    lenders.put("Name",c);
                    if(borrowed) {
                        try {

                            expdb.insertOrThrow("Lenders", null, lenders);
                        } catch (Exception e) {
                            Log.w("name", "name might already exist");
                        }
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
                    Toast.makeText(getContext(),"there is problem in updating the database"+e,Toast.LENGTH_SHORT).show();
                }

               /* if(success)
                {
                    Toast tb=Toast.makeText(getContext(),"Database updated hurrey!!!!!!!!!!\n",Toast.LENGTH_LONG);
                    tb.show();
                }*/

            }
            catch (Exception e)
            {
                Toast tb=Toast.makeText(getContext(),"Database issue so cant be updated and the issue id\n"+e,Toast.LENGTH_LONG);
                tb.show();
            }
        }

    }



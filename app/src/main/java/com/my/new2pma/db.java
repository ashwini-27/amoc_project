package com.my.new2pma;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ashwini on 12/21/2017.
 */

public class db extends SQLiteOpenHelper {
    private static final String DB_NAME = "pmadb"; // the name of our database
    private static final int DB_VERSION = 3; // the version of the database

    db(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
        updateMyDatabase(db, 0, DB_VERSION);
    }catch (Exception e){


        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //updateMyDatabase(db, oldVersion, newVersion);
        //db.execSQL("CREATE TABLE TODO (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "DATE_DEADLINE TEXT, " + "SETALARM NUMERIC, " + "CREATED_ON DATETIME DEFAULT CURRENT_TIMESTAMP, " + "NOTIFICATION NUMERIC, "
          //      + "DESCRIPTION TEXT);");

    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    /*private static void insertLend(SQLiteDatabase db, int amount, String description) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("AMOUNT", amount);
        drinkValues.put("DESCRIPTION", description);
        db.insert("LEND", null, drinkValues);
    }/*/

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("CREATE TABLE EXPENSES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, "
                    + "AMOUNT INTEGER, " + "LEND NUMERIC DEFAULT 0, " + "BORROW NUMERIC DEFAULT 0, " + "FOOD NUMERIC, " + "RECREATIONAL NUMERIC, " + "STUDYMATERIAL NUMERIC, " + "DATE TEXT, "
                    + "DESCRIPTION TEXT);");

            db.execSQL("CREATE TABLE TODO (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "DATE_DEADLINE TEXT, " + "SETALARM NUMERIC, " + "CREATED_ON DATETIME DEFAULT CURRENT_TIMESTAMP, " + "NOTIFICATION NUMERIC, "
                + "DESCRIPTION TEXT);");
            db.execSQL("CREATE TABLE Lenders (Name Text PRIMARY KEY AUTOINCREMENT);");



    }


    public boolean insertdb(SQLiteDatabase db, int amount, String description, String name, boolean lend, boolean borrow, boolean food, boolean recreational, boolean study) {
        try {
            name=name.toUpperCase();
            ContentValues lenders=new ContentValues();
            lenders.put("Name",name);
            try
            {

                db.insertOrThrow("EXPENSES", null, lenders);
            }
            catch (Exception e)
            {
                Log.w("name","name might already exist");
            }
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("AMOUNT", amount);
            drinkValues.put("NAME", name);
            drinkValues.put("DESCRIPTION", description);
            drinkValues.put("LEND", lend);
            drinkValues.put("BORROW", borrow);
            drinkValues.put("FOOD", food);
            drinkValues.put("STUDYMATERIAL", study);

            //drinkValues.put("E_D",das);

            drinkValues.put("RECREATIONAL", recreational);

            //db.insert("EXPENSES", null, drinkValues);

            //lets see the new changes


            //drinkValues.put("E_D", das);

            drinkValues.put("RECREATIONAL", recreational);
            db.beginTransaction();
            db.insertOrThrow("EXPENSES", null, drinkValues);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}




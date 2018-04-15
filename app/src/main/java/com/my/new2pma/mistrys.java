package com.my.new2pma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Deep Mistry on 09-02-2018.
 */

public class mistrys extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "subject1_table";
    public static final String TABLE_NAME2 = "subject2_table";
    public static final String TABLE_NAME1 = "start_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DAY";
    public static final String COL_3 = "SUBJECT";
    public static final String COL1 = "STARTD";
    public static final String COL2 = "ENDD";
    public static final String COL3 = "DAY1";
    public static final String COL4 = "NUMBEROF";
    public static final String TABLE_NAME3 = "student3_table";

    public static final String COL_11 = "ID";
    public static final String COL_12 = "DATE";
    public static final String COL_13 = "SUBJECT";
    public static final String COL_14 = "ATTENDANCE";

    public static final String COL111 = "SUBJECT";
    public static final String COL1111 = "SUBJECT";
    public static final String COL112 = "TOPIC";
    public static final String COL113 = "SUBTOPIC";
    public static final String COL1113 = "SUBTOPIC";
    public static final String TABLE_NAME4 = "TOPIC_INFO";
    public static final String TABLE_NAME5 = "TOPIC_RANDOM";
    public static final  String  TABLE_NAME6="EXAMDATE";

    public static final String TABLE_NAME66 = "SUBJECTS_NAME";




    public mistrys(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID TEXT,DAY TEXT,SUBJECT TEXT)");
        db.execSQL("create table " + TABLE_NAME1 +" (DAY1 TEXT,NUMBEROF TEXT)");
        db.execSQL("create table " + TABLE_NAME2 +" (STARTD TEXT,ENDD TEXT)");
        db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,SUBJECT TEXT,ATTENDANCE TEXT)");
        db.execSQL("create table " + TABLE_NAME4 +" (SUBJECT TEXT,TOPIC TEXT,SUBTOPIC TEXT,ID INTEGER PRIMARY KEY  AUTOINCREMENT,STUDIED INTEGER DEFAULT 0)");
        db.execSQL("create table " + TABLE_NAME5 +" (SUBJECT TEXT,SUBTOPIC TEXT)");
        db.execSQL("create table " + TABLE_NAME6 +" (SUBJECT TEXT,DATE date not null)");
        db.execSQL("create table " + TABLE_NAME66 +" (SUBJECT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME6);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME66);
        onCreate(db);
    }
    public boolean insertData(SQLiteDatabase db,String id,String day,String subject) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,day);
        contentValues.put(COL_3,subject);


        // String Query = "Select * from " + TABLE_NAME + " where " + date + " =? " ;
        //Cursor cursor = db.query(SELECT * FROM TABLE_NAME WHERE DATE == date AND SUBJECT ==subject);

        // Toast.makeText(MainActivity.this,"EXIST",Toast.LENGTH_LONG).show();

        Cursor cursor = db.query(TABLE_NAME,new String[]{"ID","DAY" },"ID=? AND DAY=?",new String[]{id,day},null,null,null  );
        if(cursor.getCount() > 0){
            cursor.close();

            return updateData(db,id,day,subject);
        }
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertsub(SQLiteDatabase db,String day,String numberofsubject) {

        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL1,startd);
        //contentValues.put(COL2,endd);
        contentValues.put(COL3,day);
        contentValues.put(COL4,numberofsubject);
        Cursor cursor = db.query(TABLE_NAME1,new String[]{"DAY1","NUMBEROF"}," DAY1=?",new String[]{day},null,null,null  );
        if(cursor.getCount() > 0){
            cursor.close();

            return updateData1(db,day,numberofsubject);
        }
        long result = db.insert(TABLE_NAME1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public boolean insertrandom(SQLiteDatabase db,String subject,String subtopic) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1111,subject);
        //contentValues.put(COL112,topic);
        contentValues.put(COL1113,subtopic);

        long result = db.insert(TABLE_NAME5,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean inserttopic(SQLiteDatabase db,String subject,String topic,String subtopic) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL111,subject);
        contentValues.put(COL112,topic);
        contentValues.put(COL113,subtopic);
        long result = db.insert(TABLE_NAME4,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public boolean insertstart(SQLiteDatabase db,String startd,String endd) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,startd);
        contentValues.put(COL2,endd);
        //long result=db.update(TABLE_NAME2,contentValues,null,null);

        long result = db.insert(TABLE_NAME2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    private boolean updateData(SQLiteDatabase db,String id, String day, String subject) {


        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,day);
        contentValues.put(COL_3,subject);

        long re=db.update(TABLE_NAME,
                contentValues,
                "DAY" + " = ? AND " + "ID"  + " = ?",
                new String[]{day,id});
        if(re == -1)
            return false;
        else
            return true;
    }
    private boolean updateData1(SQLiteDatabase db,String day, String numberofsubject) {


        ContentValues contentValues = new ContentValues();

        contentValues.put(COL3,day);
        contentValues.put(COL4,numberofsubject);

        long re=db.update(TABLE_NAME1,
                contentValues,
                "DAY1" + " = ?",
                new String[]{day});
        if(re == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(SQLiteDatabase db) {

        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor view(SQLiteDatabase db) {

        Cursor res = db.rawQuery("select * from "+TABLE_NAME4,null);
        return res;
    }
    public Cursor getAllData1(SQLiteDatabase db) {

        Cursor res = db.rawQuery("select * from "+TABLE_NAME2,null);
        return res;
    }
    public Cursor getAllData2(SQLiteDatabase db) {

        Cursor res = db.rawQuery("select * from "+TABLE_NAME1,null);
        return res;
    }
    public boolean insertData11(SQLiteDatabase db,String date,String subject,String att) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12,date);
        contentValues.put(COL_13,subject);
        contentValues.put(COL_14,att);

        // String Query = "Select * from " + TABLE_NAME + " where " + date + " =? " ;
        //Cursor cursor = db.query(SELECT * FROM TABLE_NAME WHERE DATE == date AND SUBJECT ==subject);
        Cursor cursor = db.query(TABLE_NAME3,new String[]{"DATE","SUBJECT" },"DATE=? AND SUBJECT=?",new String[]{date,subject},null,null,null  );
        if(cursor.getCount() > 0){
            cursor.close();

            return false;
        }
        // Toast.makeText(MainActivity.this,"EXIST",Toast.LENGTH_LONG).show();
        cursor.close();


        long result = db.insert(TABLE_NAME3,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData11(SQLiteDatabase db) {

        Cursor res = db.rawQuery("select * from "+TABLE_NAME3,null);
        return res;
    }

    public boolean updateData11(SQLiteDatabase db,String date,String subject,String att) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_12,date);
        contentValues.put(COL_13,subject);
        contentValues.put(COL_14,att);

        db.update(TABLE_NAME3,
                contentValues,
                "DATE" + " = ? AND " + "SUBJECT"  + " = ?",
                new String[]{date,subject});

        return true;

    }

    public Integer deleteData11 (SQLiteDatabase db,String id) {

        return db.delete(TABLE_NAME3, "ID = ?",new String[] {id});
    }
    public String findsubject(SQLiteDatabase db,String id,String day)
    {

        Cursor cursor = db.query(TABLE_NAME,new String[]{"ID","DAY","SUBJECT"}," ID=? AND DAY=? ",new String[]{id,day},null,null,null  );
        // Log.w("day",cursor.getString(2));
        //Toast.makeText(context,cursor.getString(2),Toast.LENGTH_LONG).show();
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return "hello";
        }
        else{
            if(cursor.moveToFirst())
            {
                String value=cursor.getString(cursor.getColumnIndex("SUBJECT"));
                db.close();
                return value;


            }
            else
            {
                return "error1";
            }}
    }

    public int find11(SQLiteDatabase db,String s)
    {
        // SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME1,new String[]{"DAY1","NUMBEROF"}," DAY1=?",new String[]{s},null,null,null  );
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return -1;
        }


        else
        {
            if(cursor.moveToFirst())
            {String value=cursor.getString(cursor.getColumnIndex("NUMBEROF"));
                int finalValue=Integer.parseInt(value);
                db.close();
                return finalValue;
            }
            else
            {
                db.close();

                return -1;
            }



        }
    }
    public boolean  checkdatafilled(SQLiteDatabase db){

        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME2,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return false;}
        else
            return true;
    }
    public boolean checkday(SQLiteDatabase db,String day)
    {
        Cursor cursor = db.query(TABLE_NAME1,new String[]{"DAY1","NUMBEROF"}," DAY1=?",new String[]{day},null,null,null  );
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return false;}
        else
            return true;
    }
    public long check(SQLiteDatabase db)
    {
        Cursor res = db.rawQuery("select DISTINCT DATE from "+TABLE_NAME3,null);
        return res.getCount();

    }
    public long subjecttotal(SQLiteDatabase db,String sub){
        Cursor cursor = db.query(TABLE_NAME3,new String[]{"SUBJECT","ATTENDANCE"}," SUBJECT=?",new String[]{sub},null,null,null  );
        return cursor.getCount();
    }
    public long subjectattended(SQLiteDatabase db,String sub){
        Cursor cursor = db.query(TABLE_NAME3,new String[]{"SUBJECT","ATTENDANCE"}," SUBJECT=? AND ATTENDANCE=?",new String[]{sub,"3"},null,null,null  );
        return cursor.getCount();
    }
    public Cursor findallsubjectsin(SQLiteDatabase db)
    {
        Cursor res = db.rawQuery("select DISTINCT SUBJECT from "+TABLE_NAME66,null);
        return res;
    }
    public Cursor devil1(SQLiteDatabase db) {
        Cursor res = db.rawQuery("select * from "+TABLE_NAME4,null);
        return res;
    }
    public  Cursor devil2(SQLiteDatabase db){
        Cursor c=db.query(true,TABLE_NAME4,new String[]{COL111},"STUDIED = ?",new String[]{"0"},null,null,null,null);
        return c;
    }
    public  Cursor devil3(SQLiteDatabase db,String s){
        Cursor c=db.query(true,TABLE_NAME4,new String[]{COL112},COL111 + "= ? AND STUDIED = ?",new String[]{s,"0"},null,null,null,null);
        return c;
    }
    public  Cursor devil4(SQLiteDatabase  db,String s,String s1){
        Cursor c=db.query(true,TABLE_NAME4,new String[]{COL113,"ID"},COL111 + "= ? AND "+COL112 + "= ? AND STUDIED = ?",new String[]{s,s1,"0"},null,null,null,null);
        return c;
    }
    public  Cursor getId(SQLiteDatabase db){
        Cursor c=db.query(TABLE_NAME4,new String[]{"ID"},"STUDIED = ?",new String[]{"0"},null,null,null);
        return c;
    }
    public void updatechecked(SQLiteDatabase db,int id){
        db.execSQL("UPDATE "+TABLE_NAME4+" SET STUDIED = 1 WHERE ID = "+id);
    }
    public boolean examdate(SQLiteDatabase db,String sub,String date)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("SUBJECT",sub);
        contentValues.put("DATE",date);
        Cursor c=db.query(TABLE_NAME6,new String[]{"SUBJECT"},"SUBJECT = ?",new String[]{sub},null,null,null);
        if(c.getCount()>0)
        {
            c.close();
            return u_examdate(db,sub,date);
        }
        Long A=db.insert(TABLE_NAME6,null,contentValues);
        if(A==-1)
        {
            return false;
        }
        return  true;
    }
    public  Cursor examanddate(SQLiteDatabase db)
    {
        Cursor c=db.query(TABLE_NAME6,new String[]{"SUBJECT","DATE"},null,null,null,null,null);
        return c;
    }
    public boolean u_examdate(SQLiteDatabase db,String sub,String  date)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("SUBJECT",sub);
        contentValues.put("DATE",date);
        long a=db.update(TABLE_NAME6,contentValues,"SUBJECT = ?",new String[]{sub});
        if(a==-1)
        {
            return false;
        }
        return true;
    }
    public boolean insertsubin(SQLiteDatabase db,String subject) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_13,subject);


        long result = db.insert(TABLE_NAME66,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public  Cursor devil22(SQLiteDatabase db){
        Cursor c=db.query(true,TABLE_NAME4,new String[]{COL111},null,null,null,null,null,null);
        return c;
    }
    public Cursor devil33(SQLiteDatabase db,String s)
    {
        Cursor c=db.query(true,TABLE_NAME4,new String[]{COL112},COL111 + "= ?",new String[]{s},null,null,null,null);
        return c;
    }
    public  Cursor devil44(SQLiteDatabase  db,String s,String s1){
        Cursor c=db.query(true,TABLE_NAME4,new String[]{COL113,"ID"},COL111 + "= ? AND "+COL112 + "= ?",new String[]{s,s1},null,null,null,null);
        return c;
    }
}

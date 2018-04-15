package com.my.new2pma;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main12Activity extends AppCompatActivity {

    StarbuzzDatabaseHelper db=new StarbuzzDatabaseHelper(this);
    SQLiteDatabase db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        LinearLayout l1=(LinearLayout)findViewById(R.id.ll123);
        db1=db.getReadableDatabase();
        //Toast.makeText(Main12Activity.this,"balle balle",Toast.LENGTH_LONG).show();
        int i=0,j=255,k=510;
        try{
            Cursor re=db.devil2(db1);
            if(re.getCount()==0)
            {
                Toast.makeText(Main12Activity.this,"NO TOPIC LEFT",Toast.LENGTH_LONG).show();
                Intent i1=new Intent(Main12Activity.this,Main11Activity.class);
                startActivity(i1);
            }
          //  Toast.makeText(Main12Activity.this,re.getCount()+ "", Toast.LENGTH_SHORT).show();
            while (re.moveToNext()) {
                TextView t1=new TextView(Main12Activity.this);
                t1.setId(i);i++;
                t1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                t1.setTextSize(35);
                t1.setText(re.getString(0));
                t1.setTextColor(Color.parseColor("#ffffba"));
                l1.addView(t1);
                Cursor re1=db.devil3(db1,re.getString(0));
               // Toast.makeText(Main12Activity.this,re1.getCount()+ "", Toast.LENGTH_SHORT).show();
                while(re1.moveToNext()){
                    TextView t2=new TextView(Main12Activity.this);
                    t2.setId(j);j++;
                    LinearLayout.LayoutParams x1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    x1.setMargins(200,0,0,0);
                    t2.setLayoutParams(x1);
                    t2.setTextColor(Color.parseColor("#afeeee"));
                    t2.setTextSize(25);
                    t2.setText(re1.getString(0));
                    l1.addView(t2);
                    Cursor re2=db.devil4(db1,re.getString(0),re1.getString(0));
                   // Toast.makeText(Main12Activity.this,re2.getCount()+ "", Toast.LENGTH_SHORT).show();
                    while(re2.moveToNext()){
                        //Toast.makeText(Main12Activity.this, re2.getString(0)+"", Toast.LENGTH_SHORT).show();
                        CheckBox c1=new CheckBox(Main12Activity.this);
                        c1.setId(Integer.valueOf(re2.getString(1)));
                        LinearLayout.LayoutParams x2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
                        x2.setMargins(400,0,0,0);
                        c1.setLayoutParams(x2);
                        c1.setTextColor(Color.parseColor("#7cfc00"));
                        c1.setTextSize(20);
                        c1.setText(re2.getString(0));
                        l1.addView(c1);
                    }

                }

                //buffer.append("what happened :"+ res.getString(3)+"\n");
            }
            if(re.getCount()>0 && re!=null){
                Button submit=new Button(Main12Activity.this);int wer=2000;
                submit.setId(wer);
                LinearLayout.LayoutParams x4=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                x4.setMargins(400,15,0,0);
                submit.setLayoutParams(x4);
                submit.setText("SUBMIT");
                l1.addView(submit);
            }
        }
        catch (Exception e){
            Toast.makeText(Main12Activity.this,e+"",Toast.LENGTH_LONG).show();
        }
        try{
        int wer=2000;
            Button b1=(Button) findViewById(wer);
            //Toast.makeText(Main12Activity.this,"getting at 0",Toast.LENGTH_LONG).show();
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(Main12Activity.this,"getting at 1",Toast.LENGTH_LONG).show();
                    Cursor c=db.getId(db1);
                    while (c.moveToNext()){
                       // Toast.makeText(Main12Activity.this,"getting id"+Integer.valueOf(c.getString(0)),Toast.LENGTH_LONG).show();
                        int id=Integer.valueOf(c.getString(0));
                        try{
                            CheckBox chk=(CheckBox) findViewById(id);
                            if(chk.isChecked())
                            {
                                db.updatechecked(db1,id);

                            }}
                        catch (Exception e)
                        {
                            Toast.makeText(Main12Activity.this,e+"",Toast.LENGTH_LONG).show();
                        }
                    }
                    Intent intent=new Intent(Main12Activity.this,Main11Activity.class);
                    startActivity(intent);

                }
            });}
            catch(Exception e){}




    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

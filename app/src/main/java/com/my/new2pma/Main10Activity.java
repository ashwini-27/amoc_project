package com.my.new2pma;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main10Activity extends AppCompatActivity {
    StarbuzzDatabaseHelper db=new StarbuzzDatabaseHelper(this);
    SQLiteDatabase db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        db1=db.getWritableDatabase();
        int i=0,j=255,k=510;
        LinearLayout l1=(LinearLayout)findViewById(R.id.viewalll1);
        Cursor re=db.devil22(db1);
        if(re.getCount()==0)
        {
            Toast.makeText(Main10Activity.this,"NO TOPICS ADDED",Toast.LENGTH_LONG).show();
            Intent i1=new Intent(Main10Activity.this,Main11Activity.class);
            startActivity(i1);
        }
        //  Toast.makeText(Main12Activity.this,re.getCount()+ "", Toast.LENGTH_SHORT).show();
        while (re.moveToNext()) {
            TextView t1=new TextView(Main10Activity.this);
            t1.setId(i);i++;
            t1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            t1.setTextSize(35);
            t1.setText(re.getString(0));
            t1.setTextColor(Color.parseColor("#ffffba"));
            l1.addView(t1);
            Cursor re1=db.devil33(db1,re.getString(0));
            // Toast.makeText(Main12Activity.this,re1.getCount()+ "", Toast.LENGTH_SHORT).show();
            while(re1.moveToNext()){
                TextView t2=new TextView(Main10Activity.this);
                t2.setId(j);j++;
                LinearLayout.LayoutParams x1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                x1.setMargins(200,0,0,0);
                t2.setLayoutParams(x1);
                t2.setTextColor(Color.parseColor("#afeeee"));
                t2.setTextSize(25);
                t2.setText(re1.getString(0));
                l1.addView(t2);
                Cursor re2=db.devil44(db1,re.getString(0),re1.getString(0));
                // Toast.makeText(Main12Activity.this,re2.getCount()+ "", Toast.LENGTH_SHORT).show();
                while(re2.moveToNext()){
                    //Toast.makeText(Main10Activity.this, re2.getString(0)+"", Toast.LENGTH_SHORT).show();
                    TextView c1=new TextView(Main10Activity.this);
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
    }
}

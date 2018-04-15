package com.my.new2pma;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main9Activity extends AppCompatActivity {

    StarbuzzDatabaseHelper db=new StarbuzzDatabaseHelper(this);
    SQLiteDatabase db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        db1 = db.getWritableDatabase();
        LinearLayout l1=(LinearLayout)findViewById(R.id.ll1);
        LinearLayout l2=(LinearLayout)findViewById(R.id.ll2);
        l1.removeAllViews();
        l2.removeAllViews();
        int i=0;
        Cursor c=db.findallsubjectsin(db1);
        while(c.moveToNext()) {
            TextView t1 = new TextView(Main9Activity.this);
            EditText e1 = new EditText(Main9Activity.this);
            LinearLayout.LayoutParams x1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams x2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            x1.setMargins(50, 27, 0,0 );
            t1.setLayoutParams(x1);
            t1.setTextColor(Color.parseColor("#ffffba"));
            x2.setMargins(0,0,0,0);
            e1.setLayoutParams(x2);
            e1.setHint("dd/mm/yyyy");
            e1.setTextSize(21);
            Toast.makeText(Main9Activity.this,c.getString(0),Toast.LENGTH_SHORT).show();
            t1.setText(c.getString(0));
            t1.setTextSize(27);
            e1.setId(i);
            i++;
            l1.addView(t1);
            l2.addView(e1);
        }
        Button b4=new Button(Main9Activity.this);
        b4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        b4.setText("Submit");
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exams();
            }
        });
        int wer=999;
        b4.setId(wer);
        l2.addView(b4);
    }
    public void exams(){
        int wer=999;
        Button b=(Button)findViewById(wer);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(Main11Activity.this,"enter in onclick",Toast.LENGTH_LONG).show();
                int cnt=0;
                Cursor c=db.findallsubjectsin(db1);
                int i=c.getCount();
                for(int j=0;j<i;j++)/*change 3 to i*/
                {
                    EditText e=(EditText)findViewById(j);
                    int k;
                    k=validate(e.getText().toString());
                    if(k!=1)
                    {
                        e.setError("enter date in valid");
                    }
                    else
                        cnt++;
                }
                // Toast.makeText(MainActivity.this,cnt+"",Toast.LENGTH_LONG).show();
                i=0;
                if(cnt==c.getCount()) {    /*check every sentences here where true something else condn is there*/
                    while (c.moveToNext()) {
                        EditText e=(EditText)findViewById(i);
                        // Toast.makeText(MainActivity.this,e.getText().toString()+"",Toast.LENGTH_LONG).show();
                        i++;
                        boolean check=db.examdate(db1,c.getString(0),e.getText().toString());
                        if(check==false)
                        {
                            Toast.makeText(Main9Activity.this,"Not Inserted",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(Main9Activity.this,"Inserted",Toast.LENGTH_LONG).show();
                            Intent i1=new Intent(Main9Activity.this,Main11Activity.class);
                            startActivity(i1);
                        }
                    }
                }

            }
        });
    }
    private int  validate(String registerdate) {

        String regEx = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$";
        Matcher matcherObj = Pattern.compile(regEx).matcher(registerdate);
        if (matcherObj.matches())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}

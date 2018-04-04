package com.my.new2pma;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;







//HELLOO
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.LENGTH_LONG;

public class Main11Activity extends AppCompatActivity {

    StarbuzzDatabaseHelper db=new StarbuzzDatabaseHelper(this);
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        db1 = db.getWritableDatabase();

        Button b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main11Activity.this,Main12Activity.class);
                startActivity(intent);
            }
        });
        Button b3=(Button)findViewById(R.id.button);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i1=new Intent(Main11Activity.this,Main9Activity.class);
               startActivity(i1);
            }
        });
        Button b4=(Button)findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(Main11Activity.this,Main10Activity.class);
                startActivity(i3);
            }
        });
        Button b5=(Button)findViewById(R.id.button3);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{ Cursor res = db.devil1(db1);
                    if(res.getCount() == 0) {
                        showMessage("Error","Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("sub :"+ res.getString(0)+"\n");
                        buffer.append("topic :"+ res.getString(1)+"\n");
                        buffer.append("subtopic :"+ res.getString(2)+"\n");
                        buffer.append("id :"+res.getString(3)+"\n");
                        buffer.append("boolean :"+res.getString(4)+"\n\n");
                    }
                    showMessage("database info:",buffer.toString());
                    res.close();
                }
                catch(Exception e)
                {
                    Toast.makeText(Main11Activity.this,"error in view"+e,LENGTH_LONG).show();
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

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

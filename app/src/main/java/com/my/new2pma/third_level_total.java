package com.my.new2pma;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class third_level_total extends Fragment {

    ListView listDrinks;
    View view;
    int foodTotal=0,MoviesTotal=0,lendTotal=0,borrowTotal=0,studyTotal=0;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentSQLiteOpenHelper exp0 = new db(this);
try {

    SQLiteOpenHelper exp0 = new db(getContext());
    view = inflater.inflate(R.layout.fragment_third_level_total, container, false);
    try {
        listDrinks=(ListView) view.findViewById(R.id.lister);
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

        //Move to the first record in the Cursor
           /* if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                //Populate the drink name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                //Populate the drink description
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);

                //Populate the drink image
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                //Populate the favorite checkbox
                CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }/*/

        String[] total = new String[5];
        total[0] = "foodTotal\t" + foodTotal;
        total[1] = "LendTotal\t" + lendTotal;
        total[2] = "StudyTotal\t" + studyTotal;
        total[3] = "MoviesTotal\t" + MoviesTotal;
        total[4] = "BorrowTotal\t" + borrowTotal;

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, total);
       // ListView listDrinks = (ListView) view.findViewById(R.id.listfirst);
        listDrinks.setAdapter(listAdapter);
    } catch (SQLiteException e) {
        Toast toast = Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT);
        toast.show();
    }

}
catch (Exception e)
{
    Toast.makeText(getContext(), "there is an issue within the toatal function"+e, Toast.LENGTH_SHORT).show();
}
        return view;
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

}

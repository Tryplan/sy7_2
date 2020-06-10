package com.example.sy7_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dhHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.create_database);
        dhHelper = new MyDatabaseHelper(this, "contacts.db", null, 1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dhHelper.getWritableDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase DB = dhHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "Lim");
                values.put("number", 45);
                values.put("sex", "男");
                DB.insert("contacts", null, values);
                values.clear();
                values.put("name", "Limi");
                values.put("number", 46);
                values.put("sex", "女");
                DB.insert("contacts", null, values);
                values.clear();
                values.put("name", "Limin");
                values.put("number", 44);
                values.put("sex", "女");
                DB.insert("contacts", null, values);
                values.clear();
                values.put("name", "Liming");
                values.put("number", 47);
                values.put("sex", "男");
                DB.insert("contacts", null, values);

            }
        });
    }
}
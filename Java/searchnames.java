package com.example.hp.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class searchnames extends AppCompatActivity {
Button bb,b1;
EditText ee;
ListView list;
DatabaseHandler db;
List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchnames);
        db=new DatabaseHandler(searchnames.this);
        bb=(Button) findViewById(R.id.b3);
        b1=(Button) findViewById(R.id.h1);
        ee=(EditText) findViewById(R.id.e1);
        list=(ListView) findViewById(R.id.list1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(searchnames.this,welcome1.class);
                startActivity(i);
                finish();
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            items=db.searchall(ee.getText().toString());
                ArrayAdapter adapter=new ArrayAdapter(searchnames.this,android.R.layout.simple_list_item_1,items);
                list.setAdapter(adapter);

            }
        });


    }

}

package com.example.hp.babycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adminaddnames extends AppCompatActivity {

    EditText editText;
    DatabaseHandler db;
    Button submit,view,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminaddnames);
        editText= (EditText) findViewById(R.id.name1);
        submit= (Button) findViewById(R.id.submit1);
        view= (Button) findViewById(R.id.view);
        back= (Button) findViewById(R.id.ll);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DatabaseHandler(adminaddnames.this);
                db.insertnamedata(editText.getText().toString());
                editText.setText("");

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminaddnames.this,adminnamelist.class));
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminaddnames.this,main.class));
                finish();
            }
        });
    }
}

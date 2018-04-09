package com.example.hp.babycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class welcome1 extends ListActivity
{


    protected TextView eun;
    protected SQLiteDatabase DB;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected TextView mUname;
    protected TextView mFname;
    protected TextView mLname;
    protected TextView mEmail;
Button b1,b2,b3;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome1);


        DB = (new DBHelper(this)).getWritableDatabase();
        // searchText = (EditText) findViewById (R.id.searchText);
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
        b3=(Button) findViewById(R.id.log);
        eun = (TextView)findViewById(R.id.textV);
        Bundle bundle = getIntent().getExtras();

      //  String UName = bundle.getString("UserName");

      //  eun.setText(UName);

b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(welcome1.this,searchnames.class);
        startActivity(i);
        finish();
    }
});


b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(welcome1.this,babyneeds.class);
        startActivity(i);
        finish();
    }
});
b3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(welcome1.this,main.class);
        startActivity(i);
        finish();
    }
});
    }

    public void search(View view)
    {
        cursor = DB.rawQuery("SELECT _id, firstname, lastname, gender, username, email FROM sri1 WHERE username LIKE ?",
                new String[] {"%" + eun.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_dtl,
                cursor,
                new String[] {"firstname", "lastname", "gender","username", "email"},
                new int[] {R.id.sfname, R.id.slname, R.id.sgender , R.id.suname, R.id.semail});


        setListAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i = new Intent(welcome1.this, main.class);
        startActivity(i);
        finish();
    }
}



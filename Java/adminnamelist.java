package com.example.hp.babycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.List;

public class adminnamelist extends AppCompatActivity {

    ListView list;
    List<String> items;
    DatabaseHandler db;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminnamelist);
        db=new DatabaseHandler(adminnamelist.this);
        items=db.selectall();
        but=(Button) findViewById(R.id.but);
        list= (ListView) findViewById(R.id.list);
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                final String delname=parent.getItemAtPosition(position).toString();

                AlertDialog.Builder builder =new AlertDialog.Builder(adminnamelist.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you want to modify or delete?");
                builder.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(adminnamelist.this);
                        alertDialog.setTitle("Modify");

                        final EditText input = new EditText(adminnamelist.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setLayoutParams(lp);
                        alertDialog.setView(input);
                        //alertDialog.setIcon(R.drawable.key);

                        alertDialog.setPositiveButton("SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        db.updatenamedata(delname,input.getText().toString());
                                        items=db.selectall();
                                        adapter.clear();
                                        adapter.addAll(items);
                                        adapter.notifyDataSetChanged();
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.cancel();
                                    }
                                });
                        // dialog.dismiss();

                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        db.deletenamedata(delname);
                        items=db.selectall();
                        adapter.clear();
                        adapter.addAll(items);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(adminnamelist.this,adminaddnames.class);
                startActivity(i);;
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

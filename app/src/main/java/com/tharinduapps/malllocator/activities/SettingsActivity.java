package com.tharinduapps.malllocator.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.database.DBHelper;
import com.tharinduapps.malllocator.models.Mall;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton closeBtn;
    private RelativeLayout insertBtn;
    private RelativeLayout deleteBtn;

    private DBHelper dbHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = this;
        dbHelper = new DBHelper(context);

        initUI();
    }

    private void initUI(){
        initElements();
        initOnClicks();
    }

    private void initElements(){
        closeBtn = (ImageButton)findViewById(R.id.closeBtn);
        insertBtn = (RelativeLayout)findViewById(R.id.insertBtn);
        deleteBtn = (RelativeLayout)findViewById(R.id.deleteBtn);
    }

    private void initOnClicks(){
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.keep_active, R.anim.slide_to_bottom);
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg ="Data insertion successful.";
                if (dbHelper.getAllMalls().size()>0){
                    msg = "Data has already been inserted.";
                } else{
                    dbHelper.insertMalls(getMallObjects());
                }

                AlertDialog textDialog = new AlertDialog.Builder(context)
                        .setMessage(msg)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        }).setCancelable(true).show();
                textDialog.getButton(textDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setMessage("Are you sure want to delete data?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //close dialog box
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dbHelper.getAllMalls().size()>0){
                                    dbHelper.deleteAll();
                                    Toast.makeText(getApplicationContext(),"Data is deleted.", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(getApplicationContext(),"No data found.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setCancelable(false).show();
                alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
            }
        });
    }

    private ArrayList<Mall> getMallObjects(){
        ArrayList<Mall> malls = new ArrayList<>();

        malls.add(new Mall(1,"Liberty Plaza","Colombo 3","0112 254 811",4.2f,6.9112714,79.7815067));
        malls.add(new Mall(2,"Majestic City","Colombo 4","0112 508 673",4.3f,6.8822381,79.8460813));

        return  malls;
    }
}

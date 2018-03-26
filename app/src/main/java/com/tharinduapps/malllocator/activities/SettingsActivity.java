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
                    MainActivity.didDBChange = true;
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
                                //do nothing
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dbHelper.getAllMalls().size()>0){
                                    dbHelper.deleteAll();
                                    MainActivity.didDBChange = true;
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

        malls.add(new Mall(1,"image1","Liberty Plaza","Colombo 3","0112 254 811","Liberty_Plaza_Cover",4.2f,6.9113598,79.851157));
        malls.add(new Mall(2,"image2","Majestic City","Colombo 4","0112 508 673","Majestic_City_Cover",4.3f,6.894127,79.8546198));
        malls.add(new Mall(3,"image3","Arcade Independence Square","Colombo","071 345 6789","Liberty_Plaza_Cover",4.2f,6.902570,79.868915));
        malls.add(new Mall(4,"image4","Dutch Hospital","Colombo 7","078 555 467","Dutch_Hospital_Cover",4.1f,6.902551,79.868832));
        malls.add(new Mall(5,"image5","Crescat Shopping Mall","Colombo","0112 152 100","Crescat_Shopping_Mall_Cover",3.6f,6.917006,79.848256));
        malls.add(new Mall(6,"image6","Colombo City Centre","Colombo","0112 300 100","Colombo_City Centre_Cover",5.0f,6.917609,79.855325));
        malls.add(new Mall(7,"image7","Odel","Dehiwala-Mount Lavinia","0112 789 700","Odel_Cover",4.6f,6.841328,79.866775));
        malls.add(new Mall(8,"image8","Cotton Collection","Colombo 3","0112 657 890","Cotton_Collection_Cover",3.6f,6.914775,79.859064));
        malls.add(new Mall(9,"image9","Capital Mall","Pitakotte","0112 786 876","Capital_Mall_Cover",4.0f,6.874732,79.934653));
        malls.add(new Mall(10,"image10","The Factory Outlet","Sri Jayawardenepura Kotte","0114 154 700","The_Factory_Outlet_Cover",4.5f,6.889142,79.929297));

        return  malls;
    }
}

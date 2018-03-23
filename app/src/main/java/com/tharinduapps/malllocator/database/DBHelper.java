package com.tharinduapps.malllocator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tharinduapps.malllocator.models.Mall;

import java.util.ArrayList;

/**
 * Created by tharindu on 3/23/18.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "MallLocatorDB.db";
    public static final String MALL_TABLE_NAME = "malls";
    public static final String MALL_COLUMN_ID = "id";
    public static final String MALL_COLUMN_NAME = "name";
    public static final String MALL_COLUMN_CITY = "city";
    public static final String MALL_COLUMN_TEL = "telephone";
    public static final String MALL_COLUMN_RATE = "rating";
    public static final String MALL_COLUMN_LAT = "lat";
    public static final String MALL_COLUMN_LON = "lon";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+MALL_TABLE_NAME+" ("+MALL_COLUMN_ID+" integer primary key,"
                +MALL_COLUMN_NAME+" text,"+MALL_COLUMN_CITY+" text,"+MALL_COLUMN_TEL+" text,"
                +MALL_COLUMN_RATE+" real,"+MALL_COLUMN_LAT+" real,"+MALL_COLUMN_LON+" real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+MALL_TABLE_NAME);
        onCreate(db);
    }

    public void insertMall (String name, String city, String tel, float rate, double lat, double lon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MALL_COLUMN_NAME, name);
        contentValues.put(MALL_COLUMN_CITY, city);
        contentValues.put(MALL_COLUMN_TEL, tel);
        contentValues.put(MALL_COLUMN_RATE, rate);
        contentValues.put(MALL_COLUMN_LAT, lat);
        contentValues.put(MALL_COLUMN_LON, lon);
        db.insert(MALL_TABLE_NAME, null, contentValues);
        db.close();
    }

    public void insertMalls(ArrayList<Mall> malls){
        SQLiteDatabase db = this.getWritableDatabase();

        for (Mall mall : malls){
            ContentValues contentValues = new ContentValues();
            contentValues.put(MALL_COLUMN_NAME, mall.getName());
            contentValues.put(MALL_COLUMN_CITY, mall.getCity());
            contentValues.put(MALL_COLUMN_TEL, mall.getTelephone());
            contentValues.put(MALL_COLUMN_RATE, mall.getRate());
            contentValues.put(MALL_COLUMN_LAT, mall.getLat());
            contentValues.put(MALL_COLUMN_LON, mall.getLon());
            db.insert(MALL_TABLE_NAME, null, contentValues);
        }

        db.close();
    }

    public ArrayList<Mall> getAllMalls(){
        ArrayList<Mall> mallList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from "+MALL_TABLE_NAME, null );
        cursor.moveToFirst();

        int idIndex = cursor.getColumnIndex(MALL_COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(MALL_COLUMN_NAME);
        int cityIndex = cursor.getColumnIndex(MALL_COLUMN_CITY);
        int telIndex = cursor.getColumnIndex(MALL_COLUMN_TEL);
        int rateIndex = cursor.getColumnIndex(MALL_COLUMN_RATE);
        int latIndex = cursor.getColumnIndex(MALL_COLUMN_LAT);
        int lonIndex = cursor.getColumnIndex(MALL_COLUMN_LON);

        while(cursor.isAfterLast() == false){
            Integer id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String city = cursor.getString(cityIndex);
            String tel = cursor.getString(telIndex);
            Float rate = cursor.getFloat(rateIndex);
            Double lat = cursor.getDouble(latIndex);
            Double lon = cursor.getDouble(lonIndex);

            mallList.add(new Mall(id,name,city,tel,rate,lat,lon));
            cursor.moveToNext();
        }
        db.close();
        return mallList;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ MALL_TABLE_NAME);
        db.close();
    }
}

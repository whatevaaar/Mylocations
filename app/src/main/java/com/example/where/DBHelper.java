package com.example.where;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DB";
    private static final String TABLE_NAME= "locations";
    private static final String COL1= "lat";
    private static final String COL2= "lng";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE locations (id INTEGER PRIMARY KEY AUTOINCREMENT, lat DOUBLE, lng DOUBLE)";

        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addData(double lat, double lon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, lat);
        contentValues.put(COL2, lon);
        long result = db.insert(TABLE_NAME, null, contentValues);
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}



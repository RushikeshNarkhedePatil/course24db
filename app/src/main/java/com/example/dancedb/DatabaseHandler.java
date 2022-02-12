package com.example.dancedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Dance";
    private static final String TABLE_NAME = "dance";
    SQLiteDatabase db = null;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DANCE_TABLE = "CREATE TABLE IF NOT EXISTS dance " + "(ID INTEGER PRIMARY KEY,NAME TEXT,MOBNO TEXT,DANCETYPE TEXT)";
        db.execSQL(CREATE_DANCE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS dance");
// Create tables again
        onCreate(db);
    }

    public void addDance(Dance dance) {
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ID", dance.get_id()); 
            values.put("NAME", dance.get_name()); 
            values.put("MOBNO", dance.get_mobno()); 
            values.put("DANCETYPE", dance.get_dancetype()); 
// 
            db.insert("dance", null, values);
// Inserting Contacts
            Log.d("Insert: ", "Record Added ..");
// Closing database connection
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDance(Dance dance) {

        SQLiteDatabase db = this.getWritableDatabase();
        int dance_id = dance.get_id();
        ContentValues cVals = new ContentValues();
        cVals.put("ID", dance.get_id());
        cVals.put("NAME", dance.get_name());
        cVals.put("MOBNO", dance.get_mobno());
        cVals.put("DANCETYPE", dance.get_dancetype());
        int count = db.update(TABLE_NAME,
                cVals,
                "ID = ?",
                new String[]{String.valueOf(dance_id)});
    }

    public void deleteDance(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(userid)});
        db.close();
    }

    // code to get all Dances in a list view
    public ArrayList<Dance> getAllDance() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Dance> DanceList = new ArrayList<Dance>();
// Select All Query
        String selectQuery = "SELECT * FROM dance";
// rawQuery(String sql, String[] selectionArgs)
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
// looping through all rows and adding to list if (cursor.moveToFirst()) {
        do {
            Dance Dance = new Dance();
            Dance.set_id(Integer.parseInt(cursor.getString(0)));
            Dance.set_name(cursor.getString(1));
            Dance.set_mobno(cursor.getString(2));
            Dance.set_dancetype(cursor.getString(3));

// Adding contact to list
            DanceList.add(Dance);
        } while (cursor.moveToNext());
// return contact list
        return DanceList;
    }

    // Get User Details based on userid
    public ArrayList<Dance> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Dance> DanceList = new ArrayList<Dance>();
        Cursor cursor = db.query
                (TABLE_NAME, new String[]{"id", "name", "mobno", "dancetype"},
                        "id = ?", new String[]{String.valueOf(userid)},
                        null, null, null, null);
        if (cursor.moveToNext()) {
            Dance Dance = new Dance();
            Dance.set_id(Integer.parseInt(cursor.getString(0)));
            Dance.set_name(cursor.getString(1));
            Dance.set_mobno(cursor.getString(2));
            Dance.set_dancetype(cursor.getString(3));
            // Adding contact to list
            DanceList.add(Dance);
        }
        while (cursor.moveToNext()) ;
// return contact list
        return DanceList;
    }
}


package com.example.android.yagami;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "crimesdb";
    private static final String TABLE_Users = "crimedetails";
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_LOCATION_TYPE = "location_type";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CONTEXT = "context";
    private static final String KEY_OUTCOME_STATUS = "outcome_status";
    private static final String KEY_PERSISTENT_ID = "persistent_id";
    private static final String KEY_CRIME_ID = "crime_id";
    private static final String KEY_LOCATION_SUBTYPE = "location_subtype";
    private static final String KEY_MONTH = "month";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CATEGORY + " TEXT,"
                + KEY_LOCATION_TYPE + " TEXT," + KEY_LOCATION + " TEXT," + KEY_CONTEXT + " TEXT,"  + KEY_OUTCOME_STATUS + " TEXT," + KEY_PERSISTENT_ID + " TEXT,"
                + KEY_CRIME_ID + " TEXT,"
                + KEY_LOCATION_SUBTYPE + " TEXT,"
                + KEY_MONTH + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
    void insertUserDetails(String category, String location_type, String location, String context, String outcome_status, String persistent_id, String crime_id, String location_subtype, String month){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_CATEGORY, category);
        cValues.put(KEY_LOCATION_TYPE, location_type);
        cValues.put(KEY_LOCATION, location);
        cValues.put(KEY_CONTEXT, context);
        cValues.put(KEY_OUTCOME_STATUS, outcome_status);
        cValues.put(KEY_PERSISTENT_ID, persistent_id);
        cValues.put(KEY_CRIME_ID, crime_id);
        cValues.put(KEY_LOCATION_SUBTYPE, location_subtype);
        cValues.put(KEY_MONTH, month);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT category, location_type, location, context, outcome_status, persistent_id, crime_id, location_subtype, month FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("category",cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
            user.put("location_type",cursor.getString(cursor.getColumnIndex(KEY_LOCATION_TYPE)));
            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
            user.put("context",cursor.getString(cursor.getColumnIndex(KEY_CONTEXT)));
            user.put("outcome_status",cursor.getString(cursor.getColumnIndex(KEY_OUTCOME_STATUS)));
            user.put("persistent_id",cursor.getString(cursor.getColumnIndex(KEY_PERSISTENT_ID)));
            user.put("crime_id",cursor.getString(cursor.getColumnIndex(KEY_CRIME_ID)));
            user.put("location_subtype",cursor.getString(cursor.getColumnIndex(KEY_LOCATION_SUBTYPE)));
            user.put("month",cursor.getString(cursor.getColumnIndex(KEY_MONTH)));
            userList.add(user);
        }
        return  userList;
    }

    public boolean GetUserByCrimeId(String crimeId){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT category, location_type, location, context, outcome_status, persistent_id, location_subtype, month FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_CATEGORY, KEY_LOCATION_TYPE, KEY_LOCATION, KEY_CONTEXT, KEY_OUTCOME_STATUS, KEY_PERSISTENT_ID, KEY_LOCATION_SUBTYPE, KEY_MONTH},  KEY_CRIME_ID+ "=?",new String[]{crimeId},null, null, null, null);
        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }
}

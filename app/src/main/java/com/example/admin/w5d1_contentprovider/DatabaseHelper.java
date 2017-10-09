package com.example.admin.w5d1_contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by Admin on 9/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "MY_DATABASE";
    private static final String TABLE_NAME = "Nba_Players";
    private static final String PLAYER_ID = "player_id";
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_AGE = "player_age";
    private static final String PLAYER_POSITION = "player_position";

    public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                PLAYER_ID + " INTEGER PRIMER KEY AUTOINCREMENT, " +
                PLAYER_NAME + " TEXT, " +
                PLAYER_AGE + " TEXT, " +
                PLAYER_POSITION + " TEXT " +
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long addPlayer(String pName, int pAge, String position){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PLAYER_NAME, pName);
        contentValues.put(PLAYER_AGE, pAge);
        contentValues.put(PLAYER_POSITION, position);

        long add = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return add;
    }

    public Cursor getPlayers(String[] projection, String selection, String[] selectionArgs, String sortOrder){

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TABLE_NAME);

        if(sortOrder==null||sortOrder==""){
            sortOrder = PLAYER_NAME;
        }
        Cursor cursor = sqLiteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        return cursor;
    }

    public int updatePlayer(int id, String pName, String pAge, String position){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PLAYER_NAME, pName);
        contentValues.put(PLAYER_AGE, pAge);
        contentValues.put(PLAYER_POSITION, position);

        String temp = "'";
        temp += (Integer.toString(id)) + "'";

        return sqLiteDatabase.update(TABLE_NAME, contentValues, PLAYER_ID + " = " + temp, null);
    }

    public int deletePlayer(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, PLAYER_ID + " = " +  name, null);
    }
}

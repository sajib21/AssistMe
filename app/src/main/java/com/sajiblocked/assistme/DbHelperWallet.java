package com.sajiblocked.assistme;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;

import java.util.ArrayList;

import static com.sajiblocked.assistme.DbHelper.DB_COLUMN;

/**
 * Created by Sadman Sakib on 10/2/2017.
 */

public class DbHelperWallet extends SQLiteOpenHelper {

    private static String DB_NAME = "WALLETDB";
    private static final int DB_VER = 1;
    public  static  final String DB_TABLE =  "Wallet";
    public static final String  DB_COLUMN_ID = "_id";
    public static final String  DB_COLUMN_TAG = "tag";
    public static final String  DB_COLUMN_TYPE = "Type";
    public static final String  DB_COLUMN_CATEGORY = "Category";
    public static final String  DB_COLUMN_DESCRIPTION = "Description";
    public static final String  DB_COLUMN_AMOUNT = "Ammount";

    private static DbHelperWallet dbHelperWallet = null;

    private DbHelperWallet(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }

    public static DbHelperWallet getInstance(Context context) {
        if(dbHelperWallet == null) dbHelperWallet = new DbHelperWallet(context);
        return dbHelperWallet;
    }
    public static DbHelperWallet getInstance() {
        return dbHelperWallet;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE " + DB_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DB_COLUMN_TAG + " TEXT NOT NULL, " + DB_COLUMN_TYPE + " TEXT NOT NULL, "
        + DB_COLUMN_DESCRIPTION + " TEXT NOT NULL, " + DB_COLUMN_AMOUNT + " INTEGER );";
        db.execSQL(str);

        System.out.println("WALLET DATABASE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE);
        db.execSQL(str);
        onCreate(db);
    }

    public void insertNewEntry(Entry entry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_TAG, entry.tag);
        values.put(DB_COLUMN_TYPE, entry.type);
        values.put(DB_COLUMN_DESCRIPTION, entry.description);
        values.put(DB_COLUMN_AMOUNT, entry.amount);
        db.insert(DB_TABLE,null,values);
        db.close();
    }

    public void deleteEntry(Entry entry){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,"tag = ?", new String[] {entry.getTag()} );
        db.close();
    }

    public Entry getEntry(String entryTag) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_TAG, DB_COLUMN_TYPE,
                DB_COLUMN_DESCRIPTION, DB_COLUMN_AMOUNT},null,null,null,null,null);

        int id_index = cursor.getColumnIndex(DB_COLUMN_ID);
        int tag_index = cursor.getColumnIndex(DB_COLUMN_TAG);
        int type_index = cursor.getColumnIndex(DB_COLUMN_TYPE);
        int description_index = cursor.getColumnIndex(DB_COLUMN_DESCRIPTION);
        int amount_index = cursor.getColumnIndex(DB_COLUMN_AMOUNT);

        Entry found = null;

        while(cursor.moveToNext() && found == null){

            if(cursor.getString(tag_index).equalsIgnoreCase(entryTag)) {
                found = new Entry(cursor.getString(tag_index), cursor.getString(type_index), cursor.getString(description_index)
                    , cursor.getString(amount_index));
            }
        }
        cursor.close();
        db.close();

        return found;
    }

    public ArrayList<Entry> getEntryList(){
        ArrayList<Entry> entryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_TAG, DB_COLUMN_TYPE,
                DB_COLUMN_DESCRIPTION, DB_COLUMN_AMOUNT},null,null,null,null,null);

        int id_index = cursor.getColumnIndex(DB_COLUMN_ID);
        int tag_index = cursor.getColumnIndex(DB_COLUMN_TAG);
        int type_index = cursor.getColumnIndex(DB_COLUMN_TYPE);
        int description_index = cursor.getColumnIndex(DB_COLUMN_DESCRIPTION);
        int amount_index = cursor.getColumnIndex(DB_COLUMN_AMOUNT);

        while(cursor.moveToNext()){
            entryList.add( new Entry( cursor.getString(tag_index), cursor.getString(type_index),
                    cursor.getString(description_index), cursor.getString(amount_index)) );
        }
        cursor.close();
        db.close();
        return entryList;
    }

    public int getBalance() {
        ArrayList<Entry> data = getEntryList();
        int balance = 0;
        for(Entry entry : data) {
            if(entry.getType().equalsIgnoreCase("Expense")) balance -= entry.getAmount();
            else balance += entry.getAmount();
        }
        return  balance;
    }
}
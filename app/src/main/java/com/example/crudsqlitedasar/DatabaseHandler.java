package com.example.crudsqlitedasar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variable
    private static  final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "db_latihan";

    //table name
    private static final String TABLE_USER = "tbl_user";

    //column tables
    private static final  String KEY_ID = "idUser";
    private static final  String KEY_USER = "namaUser";
    private static final  String KEY_EMAIL = "email";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER + " TEXT,"+ KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

    }
    public void addUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getNamaUser());
        values.put(KEY_EMAIL, user.getEmail());

        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getNamaUser());
        values.put(KEY_EMAIL, user.getEmail());

        //updating row
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getIdUser()) });
    }
    public void delete(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getIdUser()) });
        db.close();
    }
    public List<User> getAllRecord() {
        List<User> contactList = new ArrayList<User>();
        //select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User userModels = new User();
                userModels.setIdUser(Integer.parseInt(cursor.getString(0)));
                userModels.setNamaUser(cursor.getString(1));
                userModels.setEmail(cursor.getString(2));

                contactList.add(userModels);
            }while (cursor.moveToNext());
        }

        //return contact list
        return contactList;
    }


}

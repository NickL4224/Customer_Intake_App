package me.nickligocki.customer_sign_up;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;


public class DBHelper extends SQLiteOpenHelper {

    private static final String dbName = "fpos.db";
    private static final String tableName = "customers";


    public DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table "+ tableName +" (id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, email TEXT, phone TEXT, city TEXT, state TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Customers");
        onCreate(db);
    }

    public Boolean insertCustomer(String firstName, String lastName, String email, String phone, String city, String state) {
        int id = IdMaker.makeId();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("city", city);
        contentValues.put("state", state);

        long result = db.insert("Customers", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

   /* public Boolean deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Customers where id = ?", );

        if (cursor.getCount() > 0) {
            long result = db.delete("Customers", "id=?", new String[]{id});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }*/

    public Cursor getAllCustomers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] {"id", "First_name", "last_name", "email", "phone", "city", "state"};

        return db.query("Customers", columns, null, null, null, null, null);
    }



}

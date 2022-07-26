package me.nickligocki.customer_sign_up;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static final String dbName = "fpos.db";
    private static final String tableName = "customers";
    List<Customer> customerList = new ArrayList<>();


    public DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table "+ tableName +" (id INTEGER PRIMARY KEY autoincrement, first_name TEXT, last_name TEXT, email TEXT, phone TEXT, city TEXT, state TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Customers");
        onCreate(db);
    }

    public Boolean insertCustomer(String firstName, String lastName, String email, String phone, String city, String state) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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

        return db.query("customers", columns, null, null, null, null, null);
    }

    public List<Customer> getCustomerList() {
        customerList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] {"id", "First_name", "last_name", "email", "phone", "city", "state"};

        Cursor cursor = db.query("customers", columns, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex("id");
            int rowId = cursor.getInt(indexID);

            int indexFirstN = cursor.getColumnIndex("first_name");
            String fName = cursor.getString(indexFirstN);

            int indexLastN = cursor.getColumnIndex("last_name");
            String lName = cursor.getString(indexLastN);

            int indexEmail = cursor.getColumnIndex("email");
            String email = cursor.getString(indexEmail);

            int indexPhone = cursor.getColumnIndex("phone");
            String phone = cursor.getString(indexPhone);

            int indexCity = cursor.getColumnIndex("city");
            String city = cursor.getString(indexCity);

            int indexState = cursor.getColumnIndex("state");
            String state = cursor.getString(indexState);

            Customer c = new Customer(rowId, fName, lName, email, phone, city, state);
            customerList.add(c);
        }

        return customerList;
    }



}

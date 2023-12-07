package com.mobilecomputing.project4;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "DietRecommendation.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table userdata(username TEXT primary key, password TEXT,Height INTEGER,Weight INTEGER,Diabetes TEXT,VNV TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertuserData(String username, String password,int Height,int Weight,String Diabetes,String VNV){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("Height", Height);
        contentValues.put("Weight", Weight);
        contentValues.put("Diabetes", Diabetes);
        contentValues.put("VNV", VNV);

        long result = MyDB.insert("userdata", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }


    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from userdata where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String getUserHeight(String username) {
        SQLiteDatabase db = this.getWritableDatabase();; // Assuming MyDB is your SQLite database instance
        int height = -1; // Default value if the user is not found or height is not available

        try {
            String query = "SELECT Height FROM userdata WHERE username = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username});

            if (cursor.moveToFirst()) {
                int heightIndex = cursor.getColumnIndex("Height");
                height = cursor.getInt(heightIndex);
            }

            cursor.close();
        } catch (Exception e) {
            // Handle exceptions, e.g., log the error or show an error message
            e.printStackTrace();
        }

        return Integer.toString(height);
    }

    public String getUserWeight(String username) {
        SQLiteDatabase db = this.getWritableDatabase();; // Assuming MyDB is your SQLite database instance
        int weight = -1; // Default value if the user is not found or height is not available

        try {
            String query = "SELECT Weight FROM userdata WHERE username = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username});

            if (cursor.moveToFirst()) {
                int weightIndex = cursor.getColumnIndex("Weight");
                weight = cursor.getInt(weightIndex);
            }

            cursor.close();
        } catch (Exception e) {
            // Handle exceptions, e.g., log the error or show an error message
            e.printStackTrace();
        }

        return Integer.toString(weight);
    }

    public String getUserDiabetes(String username) {
        SQLiteDatabase db = this.getWritableDatabase();; // Assuming MyDB is your SQLite database instance
        String Diabetes = ""; // Default value if the user is not found or height is not available

        try {
            String query = "SELECT Diabetes FROM userdata WHERE username = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username});

            if (cursor.moveToFirst()) {
                int DiabetesIndex = cursor.getColumnIndex("Diabetes");
                Diabetes = cursor.getString(DiabetesIndex);
            }

            cursor.close();
        } catch (Exception e) {
            // Handle exceptions, e.g., log the error or show an error message
            e.printStackTrace();
        }

        return Diabetes;
    }

    public String getUserVNV(String username) {
        SQLiteDatabase db = this.getWritableDatabase();; // Assuming MyDB is your SQLite database instance
        String VNV = ""; // Default value if the user is not found or height is not available

        try {
            String query = "SELECT VNV FROM userdata WHERE username = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username});

            if (cursor.moveToFirst()) {
                int VNVIndex = cursor.getColumnIndex("VNV");
                VNV = cursor.getString(VNVIndex);
            }

            cursor.close();
        } catch (Exception e) {
            // Handle exceptions, e.g., log the error or show an error message
            e.printStackTrace();
        }

        return VNV;
    }

    public String getUserEmail(String username) {
        SQLiteDatabase db = this.getWritableDatabase();; // Assuming MyDB is your SQLite database instance
        String VNV = ""; // Default value if the user is not found or height is not available

        try {
            String query = "SELECT username FROM userdata WHERE username = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username});

            if (cursor.moveToFirst()) {
                int VNVIndex = cursor.getColumnIndex("username");
                VNV = cursor.getString(VNVIndex);
            }

            cursor.close();
        } catch (Exception e) {
            // Handle exceptions, e.g., log the error or show an error message
            e.printStackTrace();
        }

        return VNV;
    }
}



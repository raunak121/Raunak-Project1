package com.example.raunak.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Raunak on 24-01-2018.
 */

public class database extends SQLiteOpenHelper{


   SQLiteDatabase sql_db ;


    public static  String sqldb_query ="";
    public static  String db_name ="test.db";
    public static  int version = 1;

    public database(Context context) {
        super(context, db_name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

           }



    public String InsertData(Context context)
    {
        String errror = "";



        try {

          sql_db = context.openOrCreateDatabase(db_name,0,null);


            sqldb_query = " CREATE TABLE IF NOT EXISTS Contacts ( Name text, PhoneNumber text) ";
            sql_db.execSQL(sqldb_query);

            sqldb_query = " Delete from Contacts  ";
            sql_db.execSQL(sqldb_query);

            for (int i = 0; i < MainActivity.Number.size(); i++) {
                sqldb_query = " Insert into Contacts (Name,PhoneNumber) values  ( " +
                        " '" + MainActivity.Name.get(i) + "','" + MainActivity.Number.get(i) + "' ) ";
                sql_db.execSQL(sqldb_query);
            }
        }
        catch (Exception ex)
        {

            errror =ex.getMessage().toString();
        }
        return errror;
    }

    public Cursor fetchcontacts(Context context)
    {
            Cursor cr = null;
        sql_db = context.openOrCreateDatabase(db_name,0,null);

        sqldb_query = " Select 1 _id, Name, PhoneNumber from Contacts  ";
        cr = sql_db.rawQuery(sqldb_query,null);

        return  cr;
    }
}

package com.example.raunak.myapplication;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactList extends AppCompatActivity {

    ListView listView;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        listView = (ListView) findViewById(R.id.listView);



        GetcursoView();
    }


                public  void  GetcursoView()
    {


        database db = new database(this);

     try {


         Cursor cr = db.fetchcontacts(this);

         if (cr.getCount() > 0) {

             cr.moveToFirst();

             String[] from = {"Name", "PhoneNumber"};
             int[] to = {R.id.contactname, R.id.contactnumber};


             SimpleCursorAdapter sqldb_adapter = new SimpleCursorAdapter(this, R.layout.recordview_contacts, cr, from, to);
             listView.setAdapter(sqldb_adapter);
         }

     }
     catch (Exception ex)
     {

         String excp = ex.getMessage();
     }
    }
}

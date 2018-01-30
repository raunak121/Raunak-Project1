package com.example.raunak.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {




    public  static  String ClickedImage;
    public static ArrayList<String> rank;
    public static ArrayList<String> population;
    public static ArrayList<String> flag;

    public static ArrayList<String> Name;
    public static ArrayList<String> Number;
    AlertDialog.Builder builder;

    RecyclerView recyclerView;

    Button btn;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        btn = (Button) findViewById(R.id.btn);

     btn.setOnClickListener(



             new View.OnClickListener() {


         @Override
         public void onClick(View view) {



             builder = new AlertDialog.Builder(MainActivity.this);
             builder.setMessage("Do you want to access contacts ?");
             builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {


                     getDetails();
                 }
             });

             builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     return;
                 }
             });
            builder.show();

         }
     });


        new GetLoginApi(new ApiCallHandler() {
            @Override
            public void onApiSuccess() {
//dismiss progreess dialog



                CustomAdapter adapter = new CustomAdapter(getApplicationContext(),rank,flag);
                recyclerView.setAdapter(adapter);



            }


            @Override
            public void onApiFailure(Exception c) {

                //dismiss progreess dialog


                //Toast.makeText(getApplicationContext(), "Please check your password", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoConnection() {
                //dismiss progreess dialog


            }
        });

    }

    private void getDetails() {






            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            ContentResolver cr = getContentResolver();
        Cursor cur = null;
             cur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


            cur.moveToFirst();
        Name = new ArrayList<String>(cur.getCount());
        Number = new ArrayList<String>(cur.getCount());
            do {


                String name = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Name.add(name);
                Number.add(phoneNumber);

                // Log.e("Contact list with name & numbers", " "+contacts);
            }
            while (cur.moveToNext());
        cur.close();


        database db = new database(this);
       String result =  db.InsertData(this);

       if(result=="")
       {


           Intent i = new Intent(this,ContactList.class);
           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           this.startActivity(i);
       }

    }


    }

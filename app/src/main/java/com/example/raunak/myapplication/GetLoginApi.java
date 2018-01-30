package com.example.raunak.myapplication;

/**
 * Created by Eiraj on 12/1/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetLoginApi implements Response.ErrorListener, Response.Listener<JSONObject> {

    public int responseCode;
    public String message;
    public String username;
    public String password;
    Context context;



    public static String Area;
    public static String Blocked;
    public static String CreateTransactionId;
    public static String DataAreaID;
    public static String Employeename;
    public static String LoginType;
    public static String MobileNo;
    public static String ModifyTransactionId;
    public static String ProjectType;
    public static String RecId;



    public static String UserName;

    public static String UserPassword;

    public static String UserType;public static String Userstatus;public static String state;public static String userCode;
    public static String userId;
















    SharedPreferences sharedpreferences;

    String url;

    ApiCallHandler apiCallHandler;

    public GetLoginApi(ApiCallHandler apiCallHandler) {

        this.apiCallHandler = apiCallHandler;

        this.username = username;
        this.password = password;

       String suburl="http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

      // String suburl="http://13.71.114.131:2323/Service1.svc/GetCongifInfo/"+username+"/"+password;
        url = suburl;
        Log.d("check", "inside getNewsquery...url is" + url);

        Log.i("Seekdoers", "Calling Url - " + url);

        boolean isInternetConnected = AppController.isNetworkConnected();

        if (isInternetConnected) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(jsonObjectRequest, "");


        } else {

            apiCallHandler.onNoConnection();

        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        apiCallHandler.onNoConnection();

    }

    @Override
    public void onResponse(JSONObject response) {

        try {


            MainActivity.rank = new ArrayList<String>();
            MainActivity.population  = new ArrayList<String>();
            MainActivity.flag  = new ArrayList<String>();


            JSONArray newlaunch = response.getJSONArray("worldpopulation");

            if (newlaunch != null) {



                for (int i = 0; i < newlaunch.length(); i++) {
                    JSONObject rec = newlaunch.getJSONObject(i);
                                   Area=rec.getString("country");
                    Blocked = rec.getString("population");

                    Employeename = rec.getString("flag");

                    MainActivity.rank.add(Area);
                    MainActivity.population.add(Blocked);
                    MainActivity.flag.add(Employeename);



                }


                apiCallHandler.onApiSuccess();


            }

        } catch (JSONException e) {
            e.printStackTrace();
            apiCallHandler.onApiFailure(e);
        }

    }
}
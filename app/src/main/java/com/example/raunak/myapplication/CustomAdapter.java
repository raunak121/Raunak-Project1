package com.example.raunak.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Raunak on 24-01-2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

     ArrayList<String> country;
     ArrayList<String> images;
     Context context;



    public  CustomAdapter(Context context, ArrayList country,ArrayList images )
    {

        this.context = context;
        this.country = country;
        this.images = images;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recordview_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {



        holder.title.setText(MainActivity.rank.get(position));

       String url = (MainActivity.flag.get(position));

        Glide.with(context).load(url)
              .override(100,200)
              .centerCrop()
               .into(holder.imageView);


        final String element = MainActivity.rank.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context,Integer.toString(position),Toast.LENGTH_SHORT).show();
             String itemselected =    MainActivity.flag.get(position);
             MainActivity.ClickedImage = itemselected;
                //Toast.makeText(context,itemselected,Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(context, Enalrged_IMAGE.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return  country.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, genre;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtone);
            imageView = (ImageView) view.findViewById(R.id.img);

        }

    }



}

package com.tharinduapps.malllocator.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.activities.MallDetailsActivity;
import com.tharinduapps.malllocator.database.DBHelper;
import com.tharinduapps.malllocator.models.Mall;

import java.util.ArrayList;

/**
 * Created by User on 25/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ImageViewHolder>{

    private Context context;
    private DBHelper dbHelper;
    private ArrayList<Mall> malls;

    public RecyclerAdapter(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        this.malls = dbHelper.getAllMalls();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
        ImageViewHolder imageViewHolder= new ImageViewHolder(view,context);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        Mall mall ;
        mall = malls.get(position);
        holder.imageView.setImageResource(context.getResources().getIdentifier(mall.getCoverImage() , "drawable", context.getPackageName()));
        holder.name.setText(mall.getName());
        holder.address.setText(mall.getCity());
        holder.ratingBar.setRating(mall.getRate());



    }

    @Override
    public int getItemCount() {
        return malls.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView name;
        TextView address;
        RatingBar ratingBar;
        Context context;


        public ImageViewHolder(View itemView,Context context) {
            super(itemView);
            imageView = itemView.findViewById(R.id.album);
            name = itemView.findViewById(R.id.album_title);
            address = itemView.findViewById(R.id.address);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
            this.context = context;
        }



        @Override
        public void onClick(View v) {

            Intent detailsIntent = new Intent(context, MallDetailsActivity.class);
            detailsIntent.putExtra("mall", malls.get(getAdapterPosition()));
            context.startActivity(detailsIntent);
            ((Activity) context).overridePendingTransition(R.anim.slide_to_right, R.anim.keep_active);

        }
    }
}

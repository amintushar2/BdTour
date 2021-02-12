package com.raslab.bdtour.pojo;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raslab.bdtour.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelImagesAdapter extends RecyclerView.Adapter<HotelImagesAdapter.HotelImageViewHolde> {

    private Context context;
    private List<HotelModel>imageLinkList;

    public HotelImagesAdapter(Context context, List<HotelModel> imageLinks) {
        this.context = context;
        this.imageLinkList = imageLinks;
    }

    @NonNull
    @Override
    public HotelImageViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.image_single_row, parent, false);

        return new HotelImageViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelImageViewHolde holder, int position) {

        Picasso.get().load(imageLinkList.get(position).link).fit().into(holder.hotelImageView);
        holder.hotelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {

        return imageLinkList.size();

    }

    class  HotelImageViewHolde extends RecyclerView.ViewHolder{
        ImageView hotelImageView;
        public HotelImageViewHolde(@NonNull View itemView) {
            super(itemView);
        hotelImageView =itemView.findViewById(R.id.hotelImages);

        }
    }

    public void updateList(List<HotelModel> imageLinkList){
        this.imageLinkList = imageLinkList;
        notifyDataSetChanged();
    }

}

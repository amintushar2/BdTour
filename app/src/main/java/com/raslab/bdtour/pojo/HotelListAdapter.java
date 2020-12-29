package com.raslab.bdtour.pojo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raslab.bdtour.Hotel_Room_rent_Details;
import com.raslab.bdtour.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelListViewHolder> {


    private Context context;
    private  List<HotelModel>hotelModelList;

    public HotelListAdapter(Context context, List<HotelModel> hotelModelList) {
        this.context = context;
        this.hotelModelList = hotelModelList;
    }

    @NonNull
    @Override
    public HotelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotelListViewHolder(LayoutInflater.from(context).inflate(R.layout.hotel_list_single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListViewHolder holder, int position) {
            holder.hotelNameTV.setText(hotelModelList.get(position).hotelNAMES);
            holder.priceTV.setText(hotelModelList.get(position).nonAcSingleRoom+"/per night");

            holder.locationTV.setText(hotelModelList.get(position).hotelAddress+" , "+hotelModelList.get(position).hotelDistricts);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = context.getSharedPreferences("MyFref",0);
                    SharedPreferences.Editor editor =preferences.edit();

                    editor.putString("HotelName",hotelModelList.get(position).hotelNAMES);
                    editor.putString("hotelAddress",hotelModelList.get(position).hotelAddress);
                    editor.putString("hotelDistricts",hotelModelList.get(position).hotelAddress);
                    editor.putString("hotelGmapLoc",hotelModelList.get(position).hotelGmapLoc);
                    editor.putString("hotelEmail",hotelModelList.get(position).hotelEmail);
                    editor.putString("nonAcSingleRoom",hotelModelList.get(position).nonAcSingleRoom);
                    editor.putString("nonAcDoubleRoom",hotelModelList.get(position).nonAcDoubleRoom);
                    editor.putString("nonAcPremiumRoom",hotelModelList.get(position).nonAcPremiumRoom);
                    editor.putString("acSingleRoom",hotelModelList.get(position).acSingleRoom);
                    editor.putString("acDoubleRoom",hotelModelList.get(position).acDoubleRoom);
                    editor.putString("acPremium",hotelModelList.get(position).acPremiumRoom);
                    editor.putString("description",hotelModelList.get(position).description);
                    editor.putString("id",hotelModelList.get(position).id);
                    editor.putString("phoneNumber",hotelModelList.get(position).phoneNumber);
                    editor.putString("coverImageUrl",hotelModelList.get(position).coverImageUrl);
                    editor.commit();
                    Intent intent = new Intent(context,Hotel_Room_rent_Details.class);
                    context.startActivity(intent);
                }
            });

            Picasso.get().load(hotelModelList.get(position).coverImageUrl).fit().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return hotelModelList.size();
    }

    public  class  HotelListViewHolder extends RecyclerView.ViewHolder{


            private TextView hotelNameTV, priceTV,locationTV;
            ImageView imageView;

            public HotelListViewHolder(@NonNull View itemView) {
                super(itemView);
                hotelNameTV=itemView.findViewById(R.id.hotelNameTxtBox);
                priceTV=itemView.findViewById(R.id.priceTV);
                locationTV=itemView.findViewById(R.id.locationTv);
                imageView=itemView.findViewById(R.id.rlistImage);

            }
        }
    public void updateList(List<HotelModel> notelist){
        this.hotelModelList = notelist;
        notifyDataSetChanged();
    }

}

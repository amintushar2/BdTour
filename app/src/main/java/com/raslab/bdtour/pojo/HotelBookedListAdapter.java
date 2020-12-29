package com.raslab.bdtour.pojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raslab.bdtour.R;

import java.util.List;

public class HotelBookedListAdapter extends RecyclerView.Adapter<HotelBookedListAdapter.HotelBookedListViewHolde> {
    private  Context context;
    private List<UserBookedDetailsModel>userBookedDetailsModels;

    public HotelBookedListAdapter(Context context, List<UserBookedDetailsModel> userBookedDetailsModels) {
        this.context = context;
        this.userBookedDetailsModels = userBookedDetailsModels;
    }

    @NonNull
    @Override
    public HotelBookedListViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.booked_hotel_singleraw,parent,false);

        return new HotelBookedListViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelBookedListViewHolde holder, int position) {
holder.bookedHotelName.setText(userBookedDetailsModels.get(position).mobilePhoneEdts);
        Toast.makeText(context, ""+userBookedDetailsModels.get(position).mobilePhoneEdts, Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {

        return userBookedDetailsModels.size();

    }

    class HotelBookedListViewHolde extends RecyclerView.ViewHolder{

        TextView bookedHotelName;
        public HotelBookedListViewHolde(@NonNull View itemView) {

            super(itemView);
            bookedHotelName =itemView.findViewById(R.id.bookefHoteLName);
        }
    }



    public void updateList(List<UserBookedDetailsModel>userBookedDetailsModels){
        this.userBookedDetailsModels = userBookedDetailsModels;
        notifyDataSetChanged();
    }
}

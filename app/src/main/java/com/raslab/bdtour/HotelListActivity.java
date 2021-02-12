package com.raslab.bdtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raslab.bdtour.pojo.HotelListAdapter;
import com.raslab.bdtour.pojo.HotelModel;

import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends AppCompatActivity {
    TextView destinationViews;

    public DatabaseReference rootDatabaseRef;
    public DatabaseReference hotelDatabaseRef;
    public DatabaseReference hotelUserDatabaseRef;

    public FirebaseAuth firebaseAuth ;
    FirebaseDatabase mFirebaseDatabase;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private HotelListAdapter hotelListAdapter;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    String tv;
    final  List<HotelModel>hotelModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        recyclerView=findViewById(R.id.hotelListRecyclerView);
        destinationViews=findViewById(R.id.destinationTV);
        Context context;
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        Intent intent = getIntent();
         tv=  intent.getStringExtra("destinationLOC");
         destinationViews.setText("Your Destination  :  " +tv);
        preferences = getSharedPreferences("MyFref",0);
        editor =preferences.edit();

        editor.putString("DestinationSS", tv);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth= firebaseAuth.getInstance();
        rootDatabaseRef = mFirebaseDatabase.getReference();;
        firebaseAuth=FirebaseAuth.getInstance();
        rootDatabaseRef=FirebaseDatabase.getInstance().getReference();
        hotelDatabaseRef=rootDatabaseRef.child("Hotels");
        hotelDatabaseRef.keepSynced(true);
       // hotelUserDatabaseRef=hotelDatabaseRef.child(firebaseUser.getUid());
        gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        // gridLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        hotelListAdapter = new HotelListAdapter(this,hotelModelList);
        recyclerView.setAdapter(hotelListAdapter);
        adapter();


    }
        public void adapter(){

        hotelDatabaseRef.orderByChild("hotelDistricts").equalTo(tv).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot==null){
                    return;
                }else {
                    hotelModelList.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        HotelModel model= snapshot1.getValue(HotelModel.class);
                        String tv = snapshot1.getKey();

//                        for (DataSnapshot snapshot2: snapshot.child(tv).child("imageUrl").getChildren()) {
//                            String tv2= snapshot2.getKey();
//                            HotelModel model1= snapshot2.getValue(HotelModel.class);
//                            hotelModelList.add(model1);
//                        }

//                        Toast.makeText(HotelListActivity.this, ""+tv, Toast.LENGTH_SHORT).show();

                        hotelModelList.add(model);

                    }
                    hotelListAdapter.updateList(hotelModelList);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }

}
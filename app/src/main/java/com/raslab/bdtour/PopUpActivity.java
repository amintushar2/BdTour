package com.raslab.bdtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raslab.bdtour.pojo.HotelImagesAdapter;
import com.raslab.bdtour.pojo.HotelListAdapter;
import com.raslab.bdtour.pojo.HotelModel;
import com.raslab.bdtour.pojo.ImageLink;

import java.util.ArrayList;
import java.util.List;

public class PopUpActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public DatabaseReference rootDatabaseRef;
    public DatabaseReference hotelDatabaseRef;
    public DatabaseReference hotelUserDatabaseRef;
    public FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth ;

    ProgressDialog progressDialog;

    private HotelImagesAdapter hotelImagesAdapter;
    RecyclerView imageViewRecyclerView;
    String id;
    final  List<HotelModel> imageLinks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        imageViewRecyclerView =findViewById(R.id.imageViewRecyclerView);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        preferences = getSharedPreferences("MyFref", 0);

        firebaseAuth= firebaseAuth.getInstance();
        rootDatabaseRef = FirebaseDatabase.getInstance().getReference();;
        firebaseAuth=FirebaseAuth.getInstance();
        rootDatabaseRef=FirebaseDatabase.getInstance().getReference();
        hotelDatabaseRef=rootDatabaseRef.child("Hotels");
        hotelDatabaseRef.keepSynced(true);

        hotelImagesAdapter = new HotelImagesAdapter(this,imageLinks);

        GridLayoutManager manager = new GridLayoutManager(this, 1,GridLayoutManager.VERTICAL,false);
        imageViewRecyclerView.setLayoutManager(manager);
        imageViewRecyclerView.setAdapter(hotelImagesAdapter);
        id=preferences.getString("id","");

        adapter();

    }

    public void adapter(){

        hotelDatabaseRef.child(id).child("imageUrl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                if (datasnapshot==null){
                    return;
                }else {
                    imageLinks.clear();
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        HotelModel model= snapshot.getValue(HotelModel.class);
                        imageLinks.add(model);
                    }
                    hotelImagesAdapter.updateList(imageLinks);
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}
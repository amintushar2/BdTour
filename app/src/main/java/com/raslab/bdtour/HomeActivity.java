package com.raslab.bdtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raslab.bdtour.pojo.HotelBookedListAdapter;
import com.raslab.bdtour.pojo.UserBookedDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    public FirebaseAuth firebaseAuth ;
    public DatabaseReference rootDatabaseRef;
    public DatabaseReference hotelDatabaseRef;
    public DatabaseReference hotelUserDatabaseRef;
    public DatabaseReference useridDatabaseRef;
    public FirebaseUser firebaseUser;
    FirebaseDatabase mFirebaseDatabase;

    List<UserBookedDetailsModel>userBookedDetailsModels;
    HotelBookedListAdapter hotelBookedListAdapter;
    RecyclerView bookedListRecy;
 
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bookedListRecy =findViewById(R.id.bookedHotelReciclerView);
        userBookedDetailsModels= new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        rootDatabaseRef = mFirebaseDatabase.getReference();;
        firebaseAuth=FirebaseAuth.getInstance();
        rootDatabaseRef=FirebaseDatabase.getInstance().getReference();
        hotelDatabaseRef=rootDatabaseRef.child("Hotels");
        hotelUserDatabaseRef=hotelDatabaseRef.child("User");
        useridDatabaseRef=hotelDatabaseRef.child(firebaseAuth.getCurrentUser().getUid());
        hotelUserDatabaseRef.keepSynced(true);
        Toast.makeText(this, ""+firebaseAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();


       phone = firebaseAuth.getCurrentUser().getPhoneNumber().substring(3);
        Toast.makeText(this, ""+phone, Toast.LENGTH_SHORT).show();

       hotelBookedListAdapter = new HotelBookedListAdapter(this, userBookedDetailsModels);
       GridLayoutManager layoutManager = new GridLayoutManager(this,1 );
       bookedListRecy.setLayoutManager(layoutManager);
       bookedListRecy.setAdapter(hotelBookedListAdapter);

        updateData();



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                Intent startIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(startIntent);
                finish();
                return true;
            case R.id.help:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void updateData() {
        hotelUserDatabaseRef.orderByChild("mobilePhoneEdts").equalTo(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot==null){
                    return;
                }else {
                    userBookedDetailsModels.clear();
                    for (DataSnapshot snapshot2: snapshot.getChildren()) {
                        UserBookedDetailsModel userBookedDetailsModel = snapshot2.getValue(UserBookedDetailsModel.class);
                        userBookedDetailsModels.add(userBookedDetailsModel);
                        String tv = snapshot2.getKey();
                    }

                hotelBookedListAdapter.updateList(userBookedDetailsModels);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
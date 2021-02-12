package com.raslab.bdtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import java.util.Locale;

public class Hotel_Room_rent_Details extends AppCompatActivity implements View.OnClickListener {
    public DatabaseReference rootDatabaseRef;
    public DatabaseReference hotelDatabaseRef;
    public DatabaseReference hotelUserDatabaseRef;
    public FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth ;
    private HotelListAdapter hotelListAdapter;
    HotelModel hotelModel;
    Button  nextButton;

    final List<HotelModel> hotelModelList=new ArrayList<>();
        SharedPreferences  preferences;
        String hotelNAMES;
                TextView hotelName,descripitonTv,rentTvName,totalPRiceTv;
        AutoCompleteTextView roomSlectionSpiner;
        String nonACsinglechk,nonAcDoubleChk,nonACpremiumchk,aCsinglechk,aCDoubleChk,aCpremiumchk,gmapLoc,descriptionSnap;
        String nonACsinglecRB,nonAcDoubleRB,nonACpremiumcRb,aCsingleRB,aCDoubleRb,aCpremiumRb,totalPRiceR;
        SharedPreferences.Editor editor;
        RadioButton nonAc,ac,singlechk,doubleChk,premiumchk;
        String spinnerSlectedItem;
        Button showImageButton, locationBtn;

    int totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__room_rent__details);

        preferences = getSharedPreferences("MyFref",0);
        editor =preferences.edit();
        hotelName=findViewById(R.id.hotelNAmeRent);
        descripitonTv=findViewById(R.id.desCriptionTV);
        rentTvName=findViewById(R.id.rentTVEE);
        totalPRiceTv=findViewById(R.id.totalPriceTv);
        nextButton=findViewById(R.id.nextBtnSubRent);
        roomSlectionSpiner=findViewById(R.id.spinnerRoomSelection);
        totalPRiceR= totalPRiceTv.getText().toString();
        nonAc=findViewById(R.id.noAc);
        nonAc.setOnClickListener(this);
        ac=findViewById(R.id.ac);
        ac.setOnClickListener(this);
        singlechk=findViewById(R.id.singleRoom);
        singlechk.setOnClickListener(this);
        doubleChk=findViewById(R.id.doubleRoom);
        doubleChk.setOnClickListener(this);
        premiumchk=findViewById(R.id.premiumRoom);
        premiumchk.setOnClickListener(this);
        showImageButton=findViewById(R.id.imageButtonPop);
        locationBtn=findViewById(R.id.hotelLocationBtn);

        String[] arraySpinner = new String[] {
               "1", "2", "3", "4", "5", "6", "7","8","9","10"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSlectionSpiner.setAdapter(adapter);


        firebaseAuth= firebaseAuth.getInstance();
        rootDatabaseRef = FirebaseDatabase.getInstance().getReference();;
        firebaseAuth=FirebaseAuth.getInstance();
        rootDatabaseRef=FirebaseDatabase.getInstance().getReference();
        hotelDatabaseRef=rootDatabaseRef.child("Hotels");
        hotelDatabaseRef.keepSynced(true);

        preferences = getSharedPreferences("MyFref", 0);
        hotelNAMES=preferences.getString("HotelName","");
        nonACsinglechk=preferences.getString("nonAcSingleRoom","");
        nonAcDoubleChk=preferences.getString("nonAcDoubleRoom","");
        nonACpremiumchk=preferences.getString("nonAcPremiumRoom","");
        aCDoubleChk=preferences.getString("acDoubleRoom","");
        aCsinglechk=preferences.getString("acSingleRoom","");
        aCpremiumchk=preferences.getString("acPremium","");
        gmapLoc=preferences.getString("hotelGmapLoc","");
        descriptionSnap=preferences.getString("description","");


        roomSlectionSpiner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tv = "Total Price : ";
                spinnerSlectedItem= roomSlectionSpiner.getText().toString();
                totalPrice  = Integer.parseInt(rentTvName.getText().toString())*Integer.parseInt(spinnerSlectedItem);
                totalPRiceTv.setText(tv +" " + String.valueOf(totalPrice));
            }
        });


        hotelName.setText(hotelNAMES);
        descripitonTv.setText(descriptionSnap);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+gmapLoc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        showImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hotel_Room_rent_Details.this,PopUpActivity.class);
                startActivity(intent);
            }
        });
//        roomSlectionSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                String tv = "Total Price : ";
//                spinnerSlectedItem= roomSlectionSpiner.getText().toString();
//                totalPrice  = Integer.parseInt(rentTvName.getText().toString())*Integer.parseInt(spinnerSlectedItem);
//                totalPRiceTv.setText(tv +" " + String.valueOf(totalPrice));
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    nextButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            editor.putString("totalPrice", String.valueOf(totalPrice));
            editor.putString("RoomSelect",spinnerSlectedItem);
            editor.putString("acSingleRb",aCsingleRB);
            editor.putString("acDoubleRB",aCDoubleRb);
            editor.putString("acPremium",aCpremiumRb);
            editor.putString("nonSingle",nonACsinglecRB);
            editor.putString("nonDouble",nonAcDoubleRB);
            editor.putString("nonPremium",nonACpremiumcRb);
            editor.putString("RentPerNight",rentTvName.getText().toString());


            editor.commit();
            Intent intent = new Intent(Hotel_Room_rent_Details.this,UserRegFrom.class);
            startActivity(intent);
        }
    });

    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {

        if (ac.isChecked() && singlechk.isChecked()) {
            rentTvName.setText(aCsinglechk);
            aCsingleRB = ac.getText().toString() + singlechk.getText().toString();


        }else if (ac.isChecked()&&doubleChk.isChecked()){
               rentTvName.setText(aCDoubleChk);
            aCDoubleRb = ac.getText().toString() + doubleChk.getText().toString();

        } else if (ac.isChecked()&&premiumchk.isChecked()) {
            rentTvName.setText(aCpremiumchk);
            aCpremiumRb = ac.getText().toString() + premiumchk.getText().toString();
//
        }
        if (nonAc.isChecked() && singlechk.isChecked()) {
            rentTvName.setText(nonACsinglechk);
            nonACsinglecRB= nonAc.getText().toString() + singlechk.getText().toString();
//
        }else if (nonAc.isChecked()&&doubleChk.isChecked()){
            rentTvName.setText(nonAcDoubleChk);
            nonAcDoubleRB = ac.getText().toString() + doubleChk.getText().toString();
//
        } else if (ac.isChecked()&&premiumchk.isChecked()) {
            rentTvName.setText(nonACpremiumchk);

            nonACpremiumcRb = nonAc.getText().toString() + premiumchk.getText().toString();
//
        }


    }


}
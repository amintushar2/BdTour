package com.raslab.bdtour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BusDetailsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String dest;
        TextView destinationTextView,destinationTextView2,destinationTextView3,destinationTextView4,destinationTextView5,destinationTextView6,destinationTextView7
                ,destinationTextView8,destinationTextView9,destinationTextView10,destinationTextView11,destinationTextView12,destinationTextView13,destinationTextView14,
                destinationTextView15,destinationTextView16,destinationTextView17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        preferences = getSharedPreferences("MyFref", 0);
        dest=preferences.getString("destt","");
        Toast.makeText(this, ""+dest, Toast.LENGTH_SHORT).show();
        destinationTextView2= findViewById(R.id.destination1);
        destinationTextView3= findViewById(R.id.destination2);
        destinationTextView4= findViewById(R.id.destination3);
        destinationTextView5= findViewById(R.id.destination4);
        destinationTextView6= findViewById(R.id.destination5);
        destinationTextView7= findViewById(R.id.destination6);
        destinationTextView8= findViewById(R.id.destination7);
        destinationTextView9= findViewById(R.id.destination8);
        destinationTextView10= findViewById(R.id.destination9);
        destinationTextView11= findViewById(R.id.destination10);
        destinationTextView12= findViewById(R.id.destination11);
        destinationTextView13= findViewById(R.id.destination12);
        destinationTextView14= findViewById(R.id.destination13);
        destinationTextView15= findViewById(R.id.destination14);
        destinationTextView16= findViewById(R.id.destination15);
        destinationTextView17= findViewById(R.id.destination16);
        destinationTextView= findViewById(R.id.destination17);

        destinationTextView.setText(dest);
        destinationTextView2.setText(dest);
        destinationTextView3.setText(dest);
        destinationTextView4.setText(dest);
        destinationTextView5.setText(dest);
        destinationTextView6.setText(dest);
        destinationTextView7.setText(dest);
        destinationTextView8.setText(dest);
        destinationTextView9.setText(dest);
        destinationTextView10.setText(dest);
        destinationTextView11.setText(dest);
        destinationTextView12.setText(dest);
        destinationTextView13.setText(dest);
        destinationTextView14.setText(dest);
        destinationTextView15.setText(dest);
        destinationTextView16.setText(dest);
        destinationTextView17.setText(dest);

    }
}
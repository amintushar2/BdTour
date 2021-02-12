package com.raslab.bdtour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.raslab.bdtour.R;

public class AirDetailsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    TableLayout tabLayout;
    String dest;
    TextView destinationTextView,destinationTextView2,destinationTextView3,destinationTextView4,destinationTextView5,destinationTextView6,destinationTextView7
            ,destinationTextView8,destinationTextView9,destinationTextView10,destinationTextView11,destinationTextView12,destinationTextView13,destinationTextView14,
            destinationTextView15,destinationTextView16,destinationTextView17;
    private String Dhaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_details);

        preferences = getSharedPreferences("MyFref", 0);
        dest=preferences.getString("destt","");
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
        tabLayout=findViewById(R.id.tableLayout);

        if (dest.equals("Dhaka")){
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

        }else if (dest.equals("Chittagong")){
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
        }else if (dest.equals("Cox's Bazar")){
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
        }else if (dest.equals("Sylhet")){
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
        }else {
            tabLayout.setVisibility(View.GONE);
            Toast.makeText(this, "No Air Details Found" , Toast.LENGTH_SHORT).show();
        }

    }
}
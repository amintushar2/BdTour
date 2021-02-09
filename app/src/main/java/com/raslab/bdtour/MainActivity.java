package com.raslab.bdtour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
MaterialButton showDatePicker,adultIcreaseButton, adultDecreasingButton,childIcreaseButton, childDecreasingButton , searhHotelBTn;
MaterialTextView textView,adultCountTV,childCountTV;
AutoCompleteTextView autoCompleteTextView;
TextInputLayout dsInputLayout;
    int adultCount = 1;
    int childCount = 0;
    long msDiff;
    long daysDiff;
    String startDates,lastDate,adultCountS, childCounts,dif;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("MyFref",0);
        editor =preferences.edit();

        dsInputLayout= findViewById(R.id.textInputLayout);
        showDatePicker= findViewById(R.id.showDatepicker);
        textView=findViewById(R.id.textView);
        adultIcreaseButton=findViewById(R.id.incresseBTn1);
        adultDecreasingButton=findViewById(R.id.decreseBtn1);
        childIcreaseButton=findViewById(R.id.incresseBTn2);
        childDecreasingButton=findViewById(R.id.decreseBtn2);
        searhHotelBTn=findViewById(R.id.searchHotel);
        adultCountTV= findViewById(R.id.adultCount);
        childCountTV=findViewById(R.id.childCount);
        adultCountTV.setText(String.valueOf(adultCount));
        childDecreasingButton.setEnabled(false);
        adultDecreasingButton.setEnabled(false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, LOCATIONS);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);


        Calendar calendar =Calendar.getInstance(TimeZone.getTimeZone("UTC+06"));
        calendar.clear();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select Date");

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        builder.setCalendarConstraints(constraintsBuilder.build());



        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        showDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),materialDatePicker.toString());

            }
        });


       materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {

           @Override
           public void onPositiveButtonClick(Pair<Long, Long> selection) {

               showDatePicker.setText(materialDatePicker.getHeaderText());

               Long startDate = selection.first;
               Long endDate = selection.second;
               startDates = startDate.toString();
               lastDate = endDate.toString();
               msDiff= endDate - startDate;
               daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
              dif = String.valueOf(daysDiff);
              textView.setText("Total Days:\n"+dif);
              editor.putString("startDates",startDates);
              editor.putString("lastDate",lastDate);
              editor.putString("daysDiff",dif);
               editor.commit();

           }
       });



       adultIcreaseButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               adultCount++;
               adultCountTV.setText(String.valueOf(adultCount));
               adultCountS =adultCountTV.getText().toString();
               editor.putString("adultCount",adultCountS);
               editor.commit();
               if (adultCount>1){

                   adultDecreasingButton.setEnabled(true);
               }
           }
       });


adultDecreasingButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (adultCount==2){
            adultCount--;
            adultCountTV.setText(String.valueOf(adultCount));
            adultCountS =adultCountTV.getText().toString();
            editor.putString("adultCount",adultCountS);
            editor.commit();
            adultDecreasingButton.setEnabled(false);
        }else {
            adultCount--;
            adultCountTV.setText(String.valueOf(adultCount));
            adultCountTV.setText(String.valueOf(adultCount));
            adultCountS =adultCountTV.getText().toString();
            editor.putString("adultCount",adultCountS);
            editor.commit();
        }
    }
});

        childIcreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childCount++;
                childCountTV.setText(String.valueOf(childCount));
                childCounts =adultCountTV.getText().toString();
                editor.putString("childCount",childCounts);
                editor.commit();
                if (childCount>0){

                    childDecreasingButton.setEnabled(true);

                }

            }
        });
        childDecreasingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childCount==1){
                    childCount--;
                    childCountTV.setText(String.valueOf(childCount));
                    childCounts =adultCountTV.getText().toString();
                    editor.putString("childCount",childCounts);
                    editor.commit();
                    childDecreasingButton.setEnabled(false);
                }else {
                    childCount--;
                    childCountTV.setText(String.valueOf(childCount));
                    childCounts =adultCountTV.getText().toString();
                    editor.putString("childCount",childCounts);
                    editor.commit();
                }
            }
        });
      searhHotelBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if (autoCompleteTextView.getText().toString().isEmpty()){
    dsInputLayout.setError("Enter Destination");
} else{
    String destInattion = autoCompleteTextView.getText().toString();
    editor.putString("destt",destInattion);
    editor.commit();
    Intent intent = new Intent(MainActivity.this,HotelListActivity.class);
    intent.putExtra("destinationLOC",autoCompleteTextView.getText().toString());
    startActivity(intent);
}

            }
        });
    }


    private static final String[] LOCATIONS = new String[] {
            "Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur","Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",
            "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati","Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",
            "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail", "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",
            "Satkhira","Netrakona","Sherpur","Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj",
            "Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon",
            "Habiganj", "Moulvibazar",    "Sunamganj","Sylhet", "Dhaka"
    };
}
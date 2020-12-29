package com.raslab.bdtour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.raslab.bdtour.pojo.UserModel;

import java.util.Random;

public class UserRegFrom extends AppCompatActivity {
    Button submitUserBtn;
    TextInputEditText mobilePhoneEdt,firstNameEdt,lastNameEdt,emailEdt,dobEdt,adressEdt;
    String mobilePhoneEdts,firstNameEdts,lastNameEdts,emailEdts,dobEdts,adressEdts,generateToken,nonACsinglecRB,nonAcDoubleRB,nonACpremiumcRb,aCsingleRB,aCDoubleRb,aCpremiumRb;
    TextInputLayout dobLayout;
    TextInputLayout textInputLayout;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String hotelNAMES,nonACsinglechk,nonAcDoubleChk,nonACpremiumchk,aCsinglechk,aCDoubleChk,aCpremiumchk,gmapLoc,descriptionSnap;

    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg_from);
        submitUserBtn = findViewById(R.id.userFromSubmitBtn);
        mobilePhoneEdt = findViewById(R.id.mobileEdt);
        firstNameEdt=findViewById(R.id.firstnameEDT);
        lastNameEdt=findViewById(R.id.lastNameEDT);
        dobEdt=findViewById(R.id.dobEDT);
        emailEdt=findViewById(R.id.emailEDT);
        adressEdt=findViewById(R.id.adressEDT);
        textInputLayout = findViewById(R.id.mobilePHoneLApyout);
        dobLayout=findViewById(R.id.dobLayout);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

       mobilePhoneEdts =mobilePhoneEdt.getText().toString();
       firstNameEdts=firstNameEdt.getText().toString();
       lastNameEdts=lastNameEdt.getText().toString();
       emailEdts=emailEdt.getText().toString();
       dobEdts=dobEdt.getText().toString();
       adressEdts=adressEdt.getText().toString();

        userModel =new UserModel();

        preferences = getSharedPreferences("MyFref",0);

        hotelNAMES=preferences.getString("HotelName","");
        nonACsinglechk=preferences.getString("nonAcSingleRoom","");
        nonAcDoubleChk=preferences.getString("nonAcDoubleRoom","");
        nonACpremiumchk=preferences.getString("nonAcPremiumRoom","");
        aCDoubleChk=preferences.getString("acDoubleRoom","");
        aCsinglechk=preferences.getString("acSingleRoom","");
        aCpremiumchk=preferences.getString("acPremium","");
        gmapLoc=preferences.getString("hotelGmapLoc","");
        nonACsinglecRB=preferences.getString("description","");




//        //Firebase.
//        firebaseAuth=FirebaseAuth.getInstance();
//        firebaseUser=firebaseAuth.getCurrentUser();
//        rootDatabaseRef=FirebaseDatabase.getInstance().getReference();
//        hotelDatabaseRef=rootDatabaseRef.child("Hotels");
//        hotelUserDatabaseRef=hotelDatabaseRef.child("User");
//       // userDatabaseRef=hotelUserDatabaseRef.child(firebaseUser.getUid());


        MaterialDatePicker.Builder builder= MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker materialDatePicker =builder.build();
        dobLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
            }
        });


        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                  dobEdt.setText(materialDatePicker.getHeaderText());
            }
        });


        submitUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobilePhoneEdts =mobilePhoneEdt.getText().toString();
                firstNameEdts=firstNameEdt.getText().toString();
                lastNameEdts=lastNameEdt.getText().toString();
                emailEdts=emailEdt.getText().toString();
                dobEdts=dobEdt.getText().toString();
                adressEdts=adressEdt.getText().toString();

                editor =preferences.edit();
                editor.putString("mobilePhoneEdts",mobilePhoneEdts);
                editor.putString("firstNameEdts",firstNameEdts);
                editor.putString("lastNameEdts",lastNameEdts);
                editor.putString("emailEdts",emailEdts);
                editor.putString("adressEdts",adressEdts);
                editor.putString("dobEdts",dobEdts);
                editor.commit();
                validation();
                getToken();

            }
        });
    }





//        String token = "";
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            String easy = RandomString.digits ;
//            RandomString tickets = new RandomString(6, new SecureRandom(), easy);
//            token = new StringBuilder(String.valueOf(tickets)).reverse().toString().substring(0, 7);
//            Log.e("TOKEN", token);
//            Toast.makeText(this, ""+token, Toast.LENGTH_SHORT).show();
//        }


//    private void sendUserData() {
//
//
//        UserModel userModel = new UserModel(mobilePhoneEdts,firstNameEdts,lastNameEdts,emailEdts,dobEdts,adressEdts,generateToken);
//        userDatabaseRef.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Intent intent = new Intent(UserRegFrom.this,TokenManager.class);
//                intent.putExtra("phoneno",mobilePhoneEdt.getText().toString());
//                startActivity(intent);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(UserRegFrom.this, "faild to Update", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void validation() {
        if (firstNameEdt.getText().toString().isEmpty()) {
            firstNameEdt.setError("Enter Your First NAme");
            firstNameEdt.requestFocus();
            return;
        }else if (lastNameEdt.getText().toString().isEmpty()){
            lastNameEdt.setError("Enter Your Last Name");
            lastNameEdt.requestFocus();
            return;
        }else if (dobEdt.getText().toString().isEmpty()){
            dobEdt.setError("Enter Your Date Of Birth");
            dobEdt.requestFocus();
            return;

        }else if (emailEdt.getText().toString().isEmpty()|!emailEdt.getText().toString().matches(emailPattern)){
            emailEdt.setError("EnterValid Email");
            emailEdt.requestFocus();
            return;

        }else if (mobilePhoneEdt.getText().toString().isEmpty()|mobilePhoneEdt.getText().toString().length()<11){
            mobilePhoneEdt.setError("Enter Your Valid Mobile No.");
        }else {

            Intent intent = new Intent(UserRegFrom.this,TokenManager.class);
            startActivity(intent);
        }

    }
    private void getToken() {


        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric =  numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 6;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        generateToken = sb.toString();
        editor.putString("generateToken",generateToken);
        editor.commit();

    }
}
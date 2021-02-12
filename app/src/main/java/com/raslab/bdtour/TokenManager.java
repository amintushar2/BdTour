package com.raslab.bdtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.raslab.bdtour.pojo.HotelBookedModel;
import com.raslab.bdtour.pojo.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TokenManager extends AppCompatActivity {
    Button submitTOkensButton, airdetailsBtn, busDetailsBtn;
    TextInputLayout mobileOtpLayout;
    TextInputEditText mobileTokenEditText;
    TextView userDescriptionDetails,phoneNoTV,tokeNtv;
    String phontST;
    String textmessage ;
    String username ;
    String hash_token;
    String encoded_message;
    String otpVerify;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    String description;
    SharedPreferences preferences;
    TextView resendCodes;

    public DatabaseReference rootDatabaseRef;
    public DatabaseReference hotelDatabaseRef;
    public DatabaseReference hotelUserDatabaseRef;
    public DatabaseReference userDatabaseRef;
    public DatabaseReference userBookDatabaseRef;
    public FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth ;

    PhoneAuthProvider.ForceResendingToken resendToken;
    ConstraintLayout constraintLayout;
    String hotelNames,nonACsinglechk,nonAcDoubleChk,nonACpremiumchk,aCsinglechk,aCDoubleChk,aCpremiumchk,gmapLoc,descriptionSnap;
    String userid,mobilePhoneEdts,firstNameEdts,lastNameEdts,emailEdts,dobEdts,adressEdts,generateToken,rentTT,totalR;
    String startDates,lastDate,adultCountS, childCounts,dif,hotelDestrict;
    String roomSelections,id,totalRoom,destinationSTr;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_token_manager);
        resendCodes =findViewById(R.id.resendCode);


        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        phontST = intent.getStringExtra("phoneno");
        textmessage = "ThankYou For Using BDtour";
        username = "amintushar12";
        hash_token = "e0cdc7b3b60abe00a712732e00d52a7e";
        encoded_message= URLEncoder.encode(textmessage);
        phoneNoTV=findViewById(R.id.phoneNoTV);
        tokeNtv=findViewById(R.id.tokenTV);
        tokeNtv.setVisibility(View.INVISIBLE);
        preferences = getSharedPreferences("MyFref", 0);
        id=preferences.getString("id","");

        //userDatabaseRef=hotelUserDatabaseRef.child(firebaseUser.getUid());

        hotelNames=preferences.getString("HotelName","");
        hotelDestrict=preferences.getString("HotelName","");
        nonACsinglechk=preferences.getString("nonAcSingleRoom","");
        nonAcDoubleChk=preferences.getString("nonAcDoubleRoom","");
        nonACpremiumchk=preferences.getString("nonAcPremiumRoom","");
        aCDoubleChk=preferences.getString("acDoubleRoom","");
        aCsinglechk=preferences.getString("acSingleRoom","");
        aCpremiumchk=preferences.getString("acPremium","");
        gmapLoc=preferences.getString("hotelGmapLoc","");
        mobilePhoneEdts=preferences.getString("mobilePhoneEdts","");
        firstNameEdts=preferences.getString("firstNameEdts","");
        lastNameEdts=preferences.getString("lastNameEdts","");
        emailEdts=preferences.getString("emailEdts","");
        dobEdts=preferences.getString("dobEdts","");
        adressEdts=preferences.getString("adressEdts","");
        startDates=preferences.getString("startDates","");
        lastDate=preferences.getString("lastDate","");
        dif=preferences.getString("daysDiff","");
        childCounts=preferences.getString("childCount","");
        adultCountS=preferences.getString("adultCount","");
        nonACsinglechk=preferences.getString("nonSingle","");
        nonAcDoubleChk=preferences.getString("nonDouble","");
        nonACpremiumchk=preferences.getString("nonPremium","");
        aCsinglechk=preferences.getString("acSingleRb","");
        aCDoubleChk=preferences.getString("acDoubleRB","");
        aCpremiumchk=preferences.getString("acPremium","");
        rentTT=preferences.getString("RentPerNight","");
        totalR=preferences.getString("totalPrice","");

        generateToken=preferences.getString("generateToken","");


        phoneNoTV.setText("Your Mobile Numbe : "+mobilePhoneEdts);

        roomSelections=nonACsinglechk+nonAcDoubleChk+nonACpremiumchk+aCsinglechk+aCDoubleChk+aCpremiumchk;
        submitTOkensButton= findViewById(R.id.finishbtn);
        mobileTokenEditText=findViewById(R.id.mobileOtpEdt);
        mobileOtpLayout=findViewById(R.id.mobileOtpLayout);
        userDescriptionDetails=findViewById(R.id.userDescriptionDetails);

        description=
                " Dear: " +firstNameEdts+" "+lastNameEdts+"\n"+
                " You Choose: " +hotelNames+"\n"+
                "\n"+roomSelections+
                "\n"+"Total Room : "+
                "\n"+"Total Cost "+rentTT+" \n"+
                "Book For 2 Days \n"+ "Total Price"+totalR+
        "\n"+
        "Thank You For Choosing Bdtour";
        userDescriptionDetails.setText(description);
        mobileNumber ="+88"+mobilePhoneEdts;
        //Phone Verifications
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(mobileNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(TokenManager.this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);

        submitTOkensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobileTokenEditText.getText().toString().length()!=6 && mobileTokenEditText.getText().toString().isEmpty()){
                    mobileTokenEditText.setError("Please Type Valid Otp");
                }
                else {
                    otpVerify = mobileTokenEditText.getText().toString();

                    verifyVerificationCode(otpVerify);
                }

            }
        });
        resendCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendCode(mobileNumber,resendToken);
            }
        });

        busDetailsBtn=findViewById(R.id.busDetails);
        busDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TokenManager.this,BusDetailsActivity.class);
                intent1.putExtra("dest",destinationSTr);
                startActivity(intent1);

            }
        });
        airdetailsBtn= findViewById(R.id.airDetails);
        airdetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TokenManager.this,AirDetailsActivity.class);
                intent2.putExtra("dest",destinationSTr);
                startActivity(intent2);
            }
        });

    }


    public void resendCode(String mobileNumber,
                           PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobileNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)// Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            Toast.makeText(TokenManager.this, "Updated", Toast.LENGTH_SHORT).show();
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                mobileTokenEditText.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }else{
                Toast.makeText(TokenManager.this, "Faild", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(TokenManager.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
            resendToken = forceResendingToken;
        }
    };
    private void verifyVerificationCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        //signing the user
        signInWithPhoneAuthCredential(credential);

    }

    private void smsSenderApi() {
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encode the message content
        String encoded_message=URLEncoder.encode(textmessage);
        String apiUrl="http://alphasms.biz/index.php?app=ws&";
        StringBuilder sgcPostContent= new StringBuilder(apiUrl);
        sgcPostContent.append("u="+username);
        sgcPostContent.append("&h="+hash_token);
        sgcPostContent.append("&op=pv&to="+phontST);
        sgcPostContent.append("&msg="+encoded_message);
        apiUrl = sgcPostContent.toString();
        Toast.makeText(TokenManager.this, ""+apiUrl, Toast.LENGTH_SHORT).show();
        Log.d("TAG",""+apiUrl);
        try
        {
            //prepare connection
            myURL = new URL(apiUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

            //read the output
            String output;
            while ((output = reader.readLine()) != null)
                //print output
                Log.d("OUTPUT", ""+output);

            //Close connection
            reader.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(TokenManager.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            constraintLayout=findViewById(R.id.constraiTlayout);
                            constraintLayout.setVisibility(View.GONE);
                            //verification successful we will start the profile activity
                            mobileOtpLayout.setVisibility(View.GONE);
                            userDescriptionDetails.setVisibility(View.VISIBLE);
                            submitTOkensButton.setVisibility(View.GONE);
                            sendUserData();
                            smsSenderApi();
                            //sendBookedHotelData();
                        } else {
                            //verification unsuccessful.. display an error message
                            Toast.makeText(TokenManager.this, "Faild Verify Your PHone", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    private void sendUserData() {

//Firebase.
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        rootDatabaseRef= FirebaseDatabase.getInstance().getReference();
        hotelDatabaseRef=rootDatabaseRef.child("Hotels");
        hotelUserDatabaseRef=hotelDatabaseRef.child("User");
//        userDatabaseRef=hotelUserDatabaseRef.child(id);

       userBookDatabaseRef=hotelUserDatabaseRef.child(firebaseAuth.getCurrentUser().getUid());
        userid = userBookDatabaseRef.push().getKey();
        UserModel userModel = new UserModel(userid, mobilePhoneEdts,firstNameEdts,lastNameEdts,emailEdts,dobEdts,adressEdts,generateToken,startDates,lastDate,adultCountS,
                childCounts,dif,hotelNames,hotelDestrict,rentTT,totalR,roomSelections,totalRoom);
      userBookDatabaseRef.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                tokeNtv.setVisibility(View.VISIBLE);
                tokeNtv.setText("Your Token IS : "+generateToken);


                Toast.makeText(TokenManager.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TokenManager.this, "faild to Update", Toast.LENGTH_SHORT).show();

            }
        });
    }


//private  void sendBookedHotelData(){
//    HotelBookedModel hotelBookedModel = new HotelBookedModel(startDates,lastDate,adultCountS, childCounts,dif,hotelNames,hotelDestrict,rentTT,totalR,roomSelections,totalRoom);
//        userBookDatabaseRef.child(userid).setValue(hotelBookedModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(TokenManager.this, "Thanks For Booking", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

}

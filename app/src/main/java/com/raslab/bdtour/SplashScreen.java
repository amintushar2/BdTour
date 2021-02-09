package com.raslab.bdtour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference fnoteDatabase;
    private ProgressBar mProgress;


    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);



        mAuth = FirebaseAuth.getInstance();

        new Thread(new Runnable() {
            public void run() {
                doWork();
                updateUI();
                finish();
            }
        }).start();


    }

    private void doWork() {

        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(600);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
           String phoneNumber= user.getPhoneNumber();
           mAuth.signOut();
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            intent.putExtra("phoneNumbe",phoneNumber);
            startActivity(intent);
            finish();

        }else{
                Intent startIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(startIntent);
                finish();
                Log.i("MainActivity", "loginAuth == null");
            }
        }

}
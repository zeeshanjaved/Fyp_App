package com.example.zeeshanjaved.fyp_app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageexamprep;
   // private  final int timer= 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageexamprep = (ImageView) findViewById(R.id.imageexamprep);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        }, timer);

        Thread timer = new Thread(){

            public void run(){

                try{
                    sleep(3000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent openActivity = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(openActivity);
                }

            }
        };
        timer.start();

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

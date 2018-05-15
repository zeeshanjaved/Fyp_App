package com.example.zeeshanjaved.fyp_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        internet_connection();
        if(isConnect){
            NetworkCallTrialListview networkCallTrialListview = new NetworkCallTrialListview(Main3Activity.this);
            networkCallTrialListview.execute();
        }
        else {
            Toast.makeText(this,"Please connect to Internet Connection",Toast.LENGTH_SHORT).show();
        }
//        NetworkCallTrialListview networkCallTrialListview = new NetworkCallTrialListview(Main3Activity.this);
//        networkCallTrialListview.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnect = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnect;
    }
}

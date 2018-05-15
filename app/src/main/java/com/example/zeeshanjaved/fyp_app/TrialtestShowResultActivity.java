package com.example.zeeshanjaved.fyp_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;

public class TrialtestShowResultActivity extends AppCompatActivity {

    private TextView textheadingresult;
    private TextView totalquestion1 , totalquestion2;
    private TextView attemptquestion1 , attemptquestion2;
    private TextView correctques1 ,correctques2;
    private TextView incorrectques1 , incorrectques2;
    private int attemptcounter, correctcounter, incorrectcounter, totalquestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trialtest_show_result);

        textheadingresult = (TextView) findViewById(R.id.textheadingresult);
        totalquestion1 = (TextView) findViewById(R.id.totalquestion1);
        totalquestion2 = (TextView) findViewById(R.id.totalquestion2);
//
        attemptquestion1= (TextView) findViewById(R.id.attemptquestion1);
        attemptquestion2= (TextView) findViewById(R.id.attemptquestion2);

        correctques1= (TextView) findViewById(R.id.correctques1);
        correctques2= (TextView) findViewById(R.id.correctques2);
//
        incorrectques1=(TextView) findViewById(R.id.incorrectques1);
        incorrectques2=(TextView) findViewById(R.id.incorrectques2);

        SharedPreferences sharedPreferences = getSharedPreferences("counter", Context.MODE_PRIVATE);
      totalquestion = sharedPreferences.getInt("totalquescounter",0);
      attemptcounter = sharedPreferences.getInt("attemptcounter",0);
      correctcounter = sharedPreferences.getInt("correctcounter",0);
      incorrectcounter = sharedPreferences.getInt("incorrectcounter",0);
//
        totalquestion2.setText(totalquestion+"");
        attemptquestion2.setText(attemptcounter+"");
        correctques2.setText(correctcounter+"");
        incorrectques2.setText(incorrectcounter+"");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent intent = new Intent(TrialtestShowResultActivity.this, Main3Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

}

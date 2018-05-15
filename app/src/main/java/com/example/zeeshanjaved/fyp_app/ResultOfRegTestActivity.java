package com.example.zeeshanjaved.fyp_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ResultOfRegTestActivity extends AppCompatActivity {

    private TextView textView;
    private TextView totalquestion11, attemptquestion11,correctques11,incorrectques11;
    private TextView totalquestion22,attemptquestion22,correctques22,incorrectques22;
    private TextView skippedque11, skippedque22;
    private ListView listView;
    private ArrayList<String> correctans ;
    private ArrayList<String> actualans ;
    private String  userans, corectans;
    private TinyDB tinyDB;
    Gson gson;
    private int attemptcounter, correctcounter, incorrectcounter, totalquestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_of_reg_test);

        gson= new Gson();

        textView= (TextView) findViewById(R.id.textresult);
        totalquestion11 = (TextView) findViewById(R.id.totalquestion11);
        totalquestion22 = (TextView) findViewById(R.id.totalquestion22);
//
        attemptquestion11= (TextView) findViewById(R.id.attemptquestion11);
        attemptquestion22= (TextView) findViewById(R.id.attemptquestion22);

        correctques11= (TextView) findViewById(R.id.correctques11);
        correctques22= (TextView) findViewById(R.id.correctques22);
//
        incorrectques11=(TextView) findViewById(R.id.incorrectques11);
        incorrectques22=(TextView) findViewById(R.id.incorrectques22);

        listView = (ListView) findViewById(R.id.listofresult);

        tinyDB= new TinyDB(ResultOfRegTestActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences("counterofregtest", Context.MODE_PRIVATE);
        totalquestion = sharedPreferences.getInt("totalquescounter",0);
        attemptcounter = sharedPreferences.getInt("attemptcounter",0);
        correctcounter = sharedPreferences.getInt("correctcounter",0);
        incorrectcounter = sharedPreferences.getInt("incorrectcounter",0);

        totalquestion22.setText(totalquestion+"");
        attemptquestion22.setText(attemptcounter+"");
        correctques22.setText(correctcounter+"");
        incorrectques22.setText(incorrectcounter+"");

        actualans = tinyDB.getListString("stringuseranslist");
        correctans = tinyDB.getListString("stringcorrectanslist");

        ArrayList<Joiner> joiners = new ArrayList<Joiner>();

        for (int i = 0; i < correctans.size() ; i++) {

            joiners.add(new Joiner(correctans.get(i), actualans.get(i)));
        }



        CustomAdaptorReg customAdaptorReg= new CustomAdaptorReg(ResultOfRegTestActivity.this,joiners);
        listView.setAdapter(customAdaptorReg);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent intent = new Intent(ResultOfRegTestActivity.this, RegisteredTestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}

package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AForActivity extends AppCompatActivity {

    private List<CssRegisteredTestModel> cssRegisteredTestModels;
    private TextView question;
    private TextView textoptionA,textoptionB,textoptionC, textoptionD;
    private TextView textanswerA,textanswerB,textanswerC,textanswerD;
    private TextView correctans;
    private TextView countdown;
    private Button btnnext;
    private ImageButton imgdelete;
    private int count=0;
    private boolean timerrunning;
    private CountDownTimer countDownTimer;
    private long timeleftmilliseconds = 60000; // 10 minutes
   // String timelefttext;
    String corrctans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afor);

        final Activity activity=this;
        SharedPreferences sharedPreferences = getSharedPreferences("regCssTest",MODE_PRIVATE);
        String  response = sharedPreferences.getString("CssTest",null);

        question = (TextView) findViewById(R.id.textquestion);
        textoptionA = (TextView) findViewById(R.id.textoptionA);
        textoptionB = (TextView) findViewById(R.id.textoptionB);
        textoptionC = (TextView) findViewById(R.id.textoptionC);
        textoptionD = (TextView) findViewById(R.id.textoptionD);

        textanswerA = (TextView) findViewById(R.id.textanswerA);
        textanswerB = (TextView) findViewById(R.id.textanswerB);
        textanswerC = (TextView) findViewById(R.id.textanswerC);
        textanswerD = (TextView) findViewById(R.id.textanswerD);
        correctans = (TextView) findViewById(R.id.correctans);

        countdown = (TextView)findViewById(R.id.countdown);
        btnnext = (Button) findViewById(R.id.btnnext);
        imgdelete = (ImageButton) findViewById(R.id.deletebutton);

        Gson gson = new Gson();
        final List<CssRegisteredTestModel> cssRegisteredTestModels = gson.fromJson(response,
                new TypeToken<ArrayList<CssRegisteredTestModel>>(){}.getType());

        question.setText(cssRegisteredTestModels.get(count).getRegcss_question());
        textanswerA.setText(cssRegisteredTestModels.get(count).getRegcss_opt1());
        textanswerB.setText(cssRegisteredTestModels.get(count).getRegcss_opt2());
        textanswerC.setText(cssRegisteredTestModels.get(count).getRegcss_opt3());
        textanswerD.setText(cssRegisteredTestModels.get(count).getRegcss_opt4());
        correctans.setText(cssRegisteredTestModels.get(count).getRegcss_correctans());

        updatetimer();
        startstop();
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Exit the Test?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        activity.finish();
                        startstop();
                        Intent intent = new Intent(activity , RegisteredTestActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                textanswerA.setClickable(true);
                textanswerB.setClickable(true);
                textanswerC.setClickable(true);
                textanswerD.setClickable(true);

                textanswerA.setTextColor(Color.BLACK);
                textanswerB.setTextColor(Color.BLACK);
                textanswerC.setTextColor(Color.BLACK);
                textanswerD.setTextColor(Color.BLACK);

                if(count > 29){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setCancelable(false);
                    builder.setTitle("Test Completed!");
                    builder.setMessage("Wanna see result?");

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            countDownTimer.cancel();
                            activity.finish();
                            Intent intent = new Intent(activity, RegisteredTestActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else
                {
                    question.setText(cssRegisteredTestModels.get(count).getRegcss_question());
                    textanswerA.setText(cssRegisteredTestModels.get(count).getRegcss_opt1());
                    textanswerB.setText(cssRegisteredTestModels.get(count).getRegcss_opt2());
                    textanswerC.setText(cssRegisteredTestModels.get(count).getRegcss_opt3());
                    textanswerD.setText(cssRegisteredTestModels.get(count).getRegcss_opt4());
                    correctans.setText(cssRegisteredTestModels.get(count).getRegcss_correctans());
                }
            }
        });

        textanswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setClickable(false);

                textanswerA.setTextColor(Color.BLUE);

                RecordSavedAsyncTask recordSavedAsyncTask = new RecordSavedAsyncTask(activity,question.getText().toString(),
                        textanswerA.getText().toString(),correctans.getText().toString());

                recordSavedAsyncTask.execute();
            }
        });

        textanswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setClickable(false);
                textanswerB.setTextColor(Color.BLUE);

                RecordSavedAsyncTask recordSavedAsyncTask = new RecordSavedAsyncTask(activity,question.getText().toString(),
                        textanswerB.getText().toString(),correctans.getText().toString());

                recordSavedAsyncTask.execute();
            }
        });

        textanswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setClickable(false);
                textanswerC.setTextColor(Color.BLUE);

                RecordSavedAsyncTask recordSavedAsyncTask = new RecordSavedAsyncTask(activity,question.getText().toString(),
                        textanswerC.getText().toString(),correctans.getText().toString());

                recordSavedAsyncTask.execute();
            }
        });

        textanswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerD.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setTextColor(Color.BLUE);

                RecordSavedAsyncTask recordSavedAsyncTask = new RecordSavedAsyncTask(activity,question.getText().toString(),
                        textanswerD.getText().toString(),correctans.getText().toString());

                recordSavedAsyncTask.execute();
            }
        });

    }

    public void startstop(){

        if(timerrunning){
            stoptimer();
        }
        else{
            starttimer();
        }
    }

    public void starttimer(){

        countDownTimer = new CountDownTimer(timeleftmilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeleftmilliseconds = l;
                updatetimer();
            }

            @Override
            public void onFinish() {

                Intent intent =  new Intent(AForActivity.this,ResultOfRegTestActivity.class);
                startActivity(intent);

            }

        }.start();
        //btnstart.setText("PAUSE");
        timerrunning = true;
    }

    public void stoptimer(){

        countDownTimer.cancel();
       // btnstart.setText("STOP");
        timerrunning = false;
    }

    public void updatetimer(){

        int minutues = (int) timeleftmilliseconds/60000;
        int seconds = (int) timeleftmilliseconds %60000 /1000;

        String timelefttext;
        timelefttext = ""+ minutues;
        timelefttext+= ":" ;

        if(seconds <10)
            timelefttext += "0";
        // seconds = 0;
        timelefttext +=seconds;
        countdown.setText(timelefttext);

       // startstop();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit the Test?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
                Intent intent = new Intent(AForActivity.this , RegisteredTestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}

package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CssRegisteredTestFrag extends Fragment {

    private Activity activity; // Context of Css Reg Test Activity
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
    private long timeleftmilliseconds = 60000; // 1 minutes
    private String userans, trueans;
    private TinyDB tinyDB;
    private ArrayList<String> stringUserAnsList= new ArrayList<String>();
    private ArrayList<String> stringCorrectAnsList= new ArrayList<String>();
    private int attemptquescounter=0, correctquescounter=0, incorrectquescounter=0;
    boolean click =false;
    private String getcorrectans;

    public CssRegisteredTestFrag(Activity activity, List<CssRegisteredTestModel> cssRegisteredTestModels) {
        this.activity=activity;
        this.cssRegisteredTestModels=cssRegisteredTestModels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_css_registered_test, container, false);

        question = (TextView) view.findViewById(R.id.textquestion);
        textoptionA = (TextView) view.findViewById(R.id.textoptionA);
        textoptionB = (TextView) view.findViewById(R.id.textoptionB);
        textoptionC = (TextView) view.findViewById(R.id.textoptionC);
        textoptionD = (TextView) view.findViewById(R.id.textoptionD);

        textanswerA = (TextView) view.findViewById(R.id.textanswerA);
        textanswerB = (TextView) view.findViewById(R.id.textanswerB);
        textanswerC = (TextView) view.findViewById(R.id.textanswerC);
        textanswerD = (TextView) view.findViewById(R.id.textanswerD);
        correctans = (TextView) view.findViewById(R.id.correctans);

        countdown = (TextView) view.findViewById(R.id.countdown);
        btnnext = (Button) view.findViewById(R.id.btnnext);
        imgdelete = (ImageButton) view.findViewById(R.id.deletebutton);

        question.setText(cssRegisteredTestModels.get(count).getRegcss_question());
        textanswerA.setText(cssRegisteredTestModels.get(count).getRegcss_opt1());
        textanswerB.setText(cssRegisteredTestModels.get(count).getRegcss_opt2());
        textanswerC.setText(cssRegisteredTestModels.get(count).getRegcss_opt3());
        textanswerD.setText(cssRegisteredTestModels.get(count).getRegcss_opt4());
        correctans.setText(cssRegisteredTestModels.get(count).getRegcss_correctans());
        getcorrectans = correctans.getText().toString();
        tinyDB = new TinyDB(activity);

        stringCorrectAnsList.add(correctans.getText().toString());
        tinyDB.putListString("stringcorrectanslist",stringCorrectAnsList);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("counterofregtest"
                , Context.MODE_APPEND);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        updatetimer();
        startstop();
       // addindexinuseranslist();
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Exit the Test?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(attemptquescounter >30){
                            attemptquescounter--;
                        }
                        else
                     //   attemptquescounter++;
                        editor.putInt("totalquescounter",30);
                        editor.putInt("attemptcounter",attemptquescounter);
                        editor.putInt("correctcounter",correctquescounter);
                        editor.putInt("incorrectcounter",incorrectquescounter);
                        editor.commit();

                        addindexinuseranslist();
                        startstop();
                        TestDisableStatus();
                        activity.finish();
                        Intent intent = new Intent(activity , ResultOfRegTestActivity.class);
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

                if(click){
                    click=false;
                }
                else {
                    addindexinuseranslist();
                }
                //addindexinuseranslist();
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
                    builder.setMessage("You have finished your test successfully!");

                    builder.setPositiveButton("See Result", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            editor.putInt("totalquescounter",30);
                            editor.putInt("attemptcounter",attemptquescounter);
                            editor.putInt("correctcounter",correctquescounter);
                            editor.putInt("incorrectcounter",incorrectquescounter);
                            editor.commit();
                            countDownTimer.cancel();
                            TestDisableStatus();
                            activity.finish();
                            Intent intent = new Intent(activity, ResultOfRegTestActivity.class);
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
                    getcorrectans = correctans.getText().toString();

                    stringCorrectAnsList.add(correctans.getText().toString());
                    tinyDB.putListString("stringcorrectanslist",stringCorrectAnsList);

                }
            }
        });

        textanswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptquescounter++;
                click=true;
                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setClickable(false);
                textanswerA.setTextColor(Color.BLUE);
                if(textanswerA.getText().toString().equals(getcorrectans)){
                    correctquescounter++;
                }
                else {
                    incorrectquescounter++;
                }
                stringUserAnsList.add(textanswerA.getText().toString());
                tinyDB.putListString("stringuseranslist",stringUserAnsList);

            }
        });

        textanswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                click=true;
                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setClickable(false);
                textanswerB.setTextColor(Color.BLUE);
                if(textanswerB.getText().toString().equals(getcorrectans)){
                    correctquescounter++;
                }
                else {
                    incorrectquescounter++;
                }
                stringUserAnsList.add(textanswerB.getText().toString());
                tinyDB.putListString("stringuseranslist",stringUserAnsList);

            }
        });

        textanswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                click=true;
                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setClickable(false);
                textanswerC.setTextColor(Color.BLUE);
                if(textanswerC.getText().toString().equals(getcorrectans)){
                    correctquescounter++;
                }
                else {
                    incorrectquescounter++;
                }
                stringUserAnsList.add(textanswerC.getText().toString());
                tinyDB.putListString("stringuseranslist",stringUserAnsList);

            }
        });

        textanswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                click=true;
                textanswerA.setClickable(false);
                textanswerB.setClickable(false);
                textanswerD.setClickable(false);
                textanswerC.setClickable(false);
                textanswerD.setTextColor(Color.BLUE);

                if(textanswerD.getText().toString().equals(getcorrectans)){
                    correctquescounter++;
                }
                else {
                    incorrectquescounter++;
                }
                stringUserAnsList.add(textanswerD.getText().toString());
                tinyDB.putListString("stringuseranslist",stringUserAnsList);

            }
        });

        return view;
    }

    public void startstop(){

        if(timerrunning){  // true
            stoptimer();
        }
        else{      // false
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

                SharedPreferences sharedPreferences = activity.getSharedPreferences("counterofregtest",Context.MODE_APPEND);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt("totalquescounter",30);
                editor.putInt("attemptcounter",attemptquescounter);
                editor.putInt("correctcounter",correctquescounter);
                editor.putInt("incorrectcounter",incorrectquescounter);
                editor.commit();
                addindexinuseranslist();
                TestDisableStatus();
                activity.finish();
                Intent intent =  new Intent(activity,ResultOfRegTestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }

        }.start();

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

        timelefttext +=seconds;
        countdown.setText(timelefttext);

    }

    public void addindexinuseranslist(){
        attemptquescounter++;

        stringUserAnsList.add("Not Chosen any given options");
        tinyDB.putListString("stringuseranslist",stringUserAnsList);

    }

    public void TestDisableStatus(){

        SharedPreferences sharedPreferences = activity.getSharedPreferences("store", MODE_PRIVATE);
        String  username, categoryname, status;
        username = sharedPreferences.getString("storeusername",null);
        categoryname = sharedPreferences.getString("storecategoryname",null);
        status = sharedPreferences.getString("updatestatus",null);

        TestDisableAsync testDisableAsync = new TestDisableAsync(activity, username, categoryname, status);
        testDisableAsync.execute();

    }
}
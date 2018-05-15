package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhpTrialTestFrag extends Fragment {

    Activity activity; //  context of phptrial test activity
    List<PhpTestModel> phpTestModels;
    TextView question;
    TextView ansoption1, textoptionA;
    TextView ansoption2, textoptionB;
    TextView ansoption3, textoptionC;
    TextView ansoption4, textoptionD;
    TextView textcorrectAnswer1, textcorrectanswer2;
    TextView textIncorrectanswer;
    TextView textansexplanation;
    String opt1, opt2, opt3, opt4, correctans, ansexplanation;
    Button next;
    ImageButton key;
    int attemptquescounter=0, correctquescounter=0, incorrectquescounter=0, totalquestion=0;
    int count = 0;
    int size=0;
    public PhpTrialTestFrag(Activity activity, List<PhpTestModel> phpTestModels) {
        this.activity = activity;
        this.phpTestModels = phpTestModels;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_php_trial_test, container, false);

        question = view.findViewById(R.id.textquestiondetail);
        textoptionA = view.findViewById(R.id.textoptionA);
        textoptionB = view.findViewById(R.id.textoptionB);
        textoptionC = view.findViewById(R.id.textoptionC);
        textoptionD = view.findViewById(R.id.textoptionD);

        textcorrectAnswer1= view.findViewById(R.id.textcorrectans1);
        textcorrectanswer2 = view.findViewById(R.id.textcorrectans2);
        textIncorrectanswer = view.findViewById(R.id.textwrongans);
        textansexplanation = view.findViewById(R.id.textansexplanation);

        ansoption1 = view.findViewById(R.id.textanswerA);
        ansoption2 = view.findViewById(R.id.textanswerB);
        ansoption3 = view.findViewById(R.id.textanswerC);
        ansoption4 = view.findViewById(R.id.textanswerD);

        next = view.findViewById(R.id.btnnext);
        key = view.findViewById(R.id.imganskey);

         size = phpTestModels.size();
        totalquestion= size;
         --size;

        question.setText(phpTestModels.get(count).getPhp_questionno());
        ansoption1.setText(phpTestModels.get(count).getPhp_option1());
        ansoption2.setText(phpTestModels.get(count).getPhp_option2());
        ansoption3.setText(phpTestModels.get(count).getPhp_option3());
        ansoption4.setText(phpTestModels.get(count).getPhp_option4());
        textcorrectanswer2.setText(phpTestModels.get(count).getPhp_correctanswer());
        textansexplanation.setText(phpTestModels.get(count).getPhp_ansexplanation());

        opt1 = ansoption1.getText().toString();
        opt2 = ansoption2.getText().toString();
        opt3 = ansoption3.getText().toString();
        opt4 = ansoption4.getText().toString();
        correctans = textcorrectanswer2.getText().toString();
        ansexplanation = textansexplanation.getText().toString();

        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(activity, ansexplanation);
                customDialog.show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                textcorrectAnswer1.setVisibility(View.INVISIBLE);
                textIncorrectanswer.setVisibility(View.INVISIBLE);

                ansoption1.setClickable(true);
                ansoption2.setClickable(true);
                ansoption3.setClickable(true);
                ansoption4.setClickable(true);

                if (count > size) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setCancelable(false);
                    builder.setTitle("Test Completed!");
                    builder.setMessage("Wanna see result?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            SharedPreferences sharedPreferences = activity.getSharedPreferences("counter", Context.MODE_APPEND);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("totalquescounter",totalquestion);
                            editor.putInt("attemptcounter",attemptquescounter);
                            editor.putInt("correctcounter",correctquescounter);
                            editor.putInt("incorrectcounter",incorrectquescounter);
                            editor.commit();
                            Intent intent = new Intent(activity, TrialtestShowResultActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            activity.finish();
                            Intent intent = new Intent(activity, Main3Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {

                    question.setText(phpTestModels.get(count).getPhp_questionno());
                    ansoption1.setText(phpTestModels.get(count).getPhp_option1());
                    ansoption2.setText(phpTestModels.get(count).getPhp_option2());
                    ansoption3.setText(phpTestModels.get(count).getPhp_option3());
                    ansoption4.setText(phpTestModels.get(count).getPhp_option4());
                    textcorrectanswer2.setText(phpTestModels.get(count).getPhp_correctanswer());
                    textansexplanation.setText(phpTestModels.get(count).getPhp_ansexplanation());

                    opt1 = ansoption1.getText().toString();
                    opt2 = ansoption2.getText().toString();
                    opt3 = ansoption3.getText().toString();
                    opt4 = ansoption4.getText().toString();
                    correctans = textcorrectanswer2.getText().toString();
                    ansexplanation = textansexplanation.getText().toString();

                }
            }
        });

        ansoption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                if( opt1.equals(correctans)){
                    textcorrectAnswer1.setVisibility( View.VISIBLE);
                    correctquescounter++;
                }
                else
                {
                    textIncorrectanswer.setVisibility(View.VISIBLE);
                    incorrectquescounter++;
                }
                ansoption1.setClickable(false);
                ansoption2.setClickable(false);
                ansoption3.setClickable(false);
                ansoption4.setClickable(false);
            }
        });

        ansoption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                if(opt2.equals(correctans)){
                    textcorrectAnswer1.setVisibility(View.VISIBLE);
                    correctquescounter++;
                }
                else {
                    textIncorrectanswer.setVisibility(View.VISIBLE);
                    incorrectquescounter++;

                }
                ansoption1.setClickable(false);
                ansoption2.setClickable(false);
                ansoption3.setClickable(false);
                ansoption4.setClickable(false);
            }
        });

        ansoption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                if( opt3.equals(correctans)){
                    textcorrectAnswer1.setVisibility(View.VISIBLE);
                    correctquescounter++;

                }
                else {
                    textIncorrectanswer.setVisibility(View.VISIBLE);
                    incorrectquescounter++;
                }
                ansoption1.setClickable(false);
                ansoption2.setClickable(false);
                ansoption3.setClickable(false);
                ansoption4.setClickable(false);
            }
        });

        ansoption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptquescounter++;
                if( opt4.equals(correctans)){
                    textcorrectAnswer1.setVisibility(View.VISIBLE);
                    correctquescounter++;

                }
                else {
                    textIncorrectanswer.setVisibility(View.VISIBLE);
                    incorrectquescounter++;
                }
                ansoption1.setClickable(false);
                ansoption2.setClickable(false);
                ansoption3.setClickable(false);
                ansoption4.setClickable(false);
            }
        });

        return view;
    }

}

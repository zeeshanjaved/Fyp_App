package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CssRegisteredTestActivity extends AppCompatActivity {

    private String username;
    private String categoryname;
    private String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css_registered_test);

        Activity activity = this;

        SharedPreferences sharedPreferences = getSharedPreferences("regTest",MODE_PRIVATE);
        String jsonresult = sharedPreferences.getString("CssTest",null);

        if(jsonresult!=null) {

            Gson gson = new Gson();
            List<CssRegisteredTestModel> cssRegisteredTestModels = gson.fromJson(jsonresult,
                    new TypeToken<ArrayList<CssRegisteredTestModel>>() {}.getType());

           getSupportFragmentManager().beginTransaction().add(R.id.regcsstestcontinor,
                   new CssRegisteredTestFrag(activity,cssRegisteredTestModels)).commit();

        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit the Test?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intent = new Intent(CssRegisteredTestActivity.this , ResultOfRegTestActivity.class);
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

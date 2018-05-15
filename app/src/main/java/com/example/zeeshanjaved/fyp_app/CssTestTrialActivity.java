package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CssTestTrialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css_test_trial);

        SharedPreferences sharedPreferences = getSharedPreferences("Tests", MODE_PRIVATE);
        String jsonresponse = sharedPreferences.getString("cssTest",null);

        Activity activity = this;
        if(jsonresponse!=null){

            Gson gson = new Gson();
            List<CssTestModel> cssTestModels = gson.fromJson(jsonresponse, new TypeToken<ArrayList<CssTestModel>>()
            {}.getType());

            getSupportFragmentManager().beginTransaction().add(R.id.csstrialtestcontainore,
                    new CssTrialTestFragment(activity,cssTestModels)).commit();

        }
    }
}

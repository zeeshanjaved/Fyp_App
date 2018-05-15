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

public class HtmlTestTrialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_test_trial);

        Activity activity = this;  // context of htmltrialtest activity
        SharedPreferences sharedPreferences = getSharedPreferences("Tests" , MODE_PRIVATE);
        String response = sharedPreferences.getString("htmlTest",null);

        if (response!=null)
        {
            Gson gson = new Gson();
            List<HtmlTestModel> htmlTestModels= gson.fromJson(response, new TypeToken<ArrayList<HtmlTestModel>>()
            {}.getType());

            getSupportFragmentManager().beginTransaction().add(R.id.htmltrialtestcontainer,
                    new HtmlTestTrialFragment(activity,htmlTestModels))
                    .commit();
        }
    }
}

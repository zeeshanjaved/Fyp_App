package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class PhpTrialTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_trial_test);


        SharedPreferences sharedPreferences = getSharedPreferences("Tests" , MODE_PRIVATE);
        String jsonresponse = sharedPreferences.getString("phpTest",null);

        Activity activity = this;  // context of phptrialtest activity
        if (jsonresponse!=null)
        {
            Gson gson = new Gson();
            List<PhpTestModel> phpTestModels= gson.fromJson(jsonresponse, new TypeToken<ArrayList<PhpTestModel>>()
            {}.getType());

            getSupportFragmentManager().beginTransaction().add(R.id.phptrialtestcontainor,
                    new PhpTrialTestFrag(activity,phpTestModels))
                    .commit();
        }
    }
}

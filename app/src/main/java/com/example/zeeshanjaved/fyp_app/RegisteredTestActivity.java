package com.example.zeeshanjaved.fyp_app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisteredTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_test);

        FragmentManager fragmentManager = getSupportFragmentManager();
        RegisteredTestDescriptionFrag registeredTestDescriptionFrag = new RegisteredTestDescriptionFrag(this);
        fragmentManager.beginTransaction().add(R.id.registeredtestcontainor, registeredTestDescriptionFrag).commit();
    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(RegisteredTestActivity.this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}

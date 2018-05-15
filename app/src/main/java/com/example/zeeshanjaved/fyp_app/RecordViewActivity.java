package com.example.zeeshanjaved.fyp_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecordViewActivity extends AppCompatActivity {

    private TextView textnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_view);

        textnews =(TextView) findViewById(R.id.textnews);


    }
}

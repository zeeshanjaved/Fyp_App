package com.example.zeeshanjaved.fyp_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FaqsActivity extends AppCompatActivity {

    private TextView textque1 , textque2, textque3, textque4, textque5,textque6,textque7,textque8,textque9,textque10 ;
    private TextView textans1, textans2, textans3, textans4, textans5, textans6,textans7,textans8,textans9,textans10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        textque1 = (TextView) findViewById(R.id.textque1);
        textque2 = (TextView) findViewById(R.id.textque2);
        textque3 = (TextView) findViewById(R.id.textque3);
        textque4 = (TextView) findViewById(R.id.textque4);
        textque5 = (TextView) findViewById(R.id.textque5);
        textque6 = (TextView) findViewById(R.id.textque6);
        textque7 = (TextView) findViewById(R.id.textque7);
        textque8 = (TextView) findViewById(R.id.textque8);
        textque9 = (TextView) findViewById(R.id.textque9);
        textque10 = (TextView) findViewById(R.id.textque10);

        textans1 = (TextView) findViewById(R.id.textans1);
        textans2 = (TextView) findViewById(R.id.textans2);
        textans3 = (TextView) findViewById(R.id.textans3);
        textans4 = (TextView) findViewById(R.id.textans4);
        textans5 = (TextView) findViewById(R.id.textans5);
        textans6 = (TextView) findViewById(R.id.textans6);
        textans7 = (TextView) findViewById(R.id.textans7);
        textans8 = (TextView) findViewById(R.id.textans8);
        textans9 = (TextView) findViewById(R.id.textans9);
        textans10 = (TextView) findViewById(R.id.textans10);

        textque1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textans1.setVisibility(View.VISIBLE);
                
            }
        });

        textans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans1.setVisibility(View.GONE);
            }
        });

        textque2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans2.setVisibility(View.VISIBLE);

            }
        });

        textans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textans2.setVisibility(View.GONE);
            }
        });

        textque3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans3.setVisibility(View.VISIBLE);
            }
        });

        textans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans3.setVisibility(View.GONE);
            }
        });

        textque4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans4.setVisibility(View.VISIBLE);
            }
        });

        textans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textans4.setVisibility(View.GONE);
            }
        });

        textque5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans5.setVisibility(View.VISIBLE);
            }
        });

        textans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans5.setVisibility(View.GONE);
            }
        });

        textque6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans6.setVisibility(View.VISIBLE);
            }
        });
        textans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans6.setVisibility(View.GONE);
            }
        });

        textque7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans7.setVisibility(View.VISIBLE);
            }
        });
        textans7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans7.setVisibility(View.GONE);
            }
        });

        textque8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans8.setVisibility(View.VISIBLE);
            }
        });
        textans8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans8.setVisibility(View.GONE);
            }
        });

        textque9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans9.setVisibility(View.VISIBLE);
            }
        });
        textans9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans9.setVisibility(View.GONE);
            }
        });

        textque10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans10.setVisibility(View.VISIBLE);
            }
        });
        textans10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textans10.setVisibility(View.GONE);
            }
        });
    }
}

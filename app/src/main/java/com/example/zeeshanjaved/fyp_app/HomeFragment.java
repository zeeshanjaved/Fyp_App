package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Activity activity;
    private TextView textaboutus, textfaqs, textnews, textcontact, textfreetest, textregisteredtest;
    private ImageButton imgaboutus, imgfaqs, imgnews, imgcontact, imgfreetest, imgresgisteredtest;


    public HomeFragment(Activity activity) {
        this.activity=activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textaboutus = (TextView) view.findViewById(R.id.textaboutus);
        textfaqs = (TextView) view.findViewById(R.id.textfaqs);
        textnews = (TextView) view.findViewById(R.id.textnews);
        textcontact = (TextView) view.findViewById(R.id.textcontact);
        textfreetest = (TextView) view.findViewById(R.id.texttrialtest);
        textregisteredtest = (TextView) view.findViewById(R.id.textpaidtest);

        imgaboutus = (ImageButton) view.findViewById(R.id.imgaboutus);
        imgfaqs = (ImageButton) view.findViewById(R.id.imgfaqs);
        imgnews = (ImageButton) view.findViewById(R.id.imgnews);
        imgcontact = (ImageButton) view.findViewById(R.id.imgcontact);
        imgfreetest = (ImageButton) view.findViewById(R.id.imgtrialtest);
        imgresgisteredtest = (ImageButton) view.findViewById(R.id.imgpaidtest);

        // ClickListener on Image About US
        imgaboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        // ClickListener on Image FAQs
        imgfaqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, FaqsActivity.class);
                startActivity(intent);
            }
        });

        // ClickListener on Image News
        imgnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, RecordViewActivity.class);
                startActivity(intent);
            }
        });

        // ClickListener on Image Contact US
        imgcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ContactActivity.class);
                startActivity(intent);
            }
        });

        // ClickListener on Image Trial Test
        imgfreetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, Main3Activity.class);
                startActivity(intent);
            }
        });

        // ClickListener on Image Piad Test
        imgresgisteredtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, RegisteredTestActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }

}

package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Zeeshan Javed on 12/23/2017.
 */

public class CustomDialog extends Dialog {

    Activity activity;
    TextView textdialogtitle, textansdialog, textbackdialog ;
    String ansexplanation;

    public CustomDialog(Activity context, String ansexplanation) {
        super(context);
        this.activity=context;
        this.ansexplanation=ansexplanation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.custom_dialog);
        textdialogtitle =(TextView) findViewById(R.id.textdialogtitle);
        textansdialog = (TextView) findViewById(R.id.textansindialog);
        textbackdialog =(TextView) findViewById(R.id.textbackdialog);

        textansdialog.setText(ansexplanation);
        textbackdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        dismiss();
    }
}

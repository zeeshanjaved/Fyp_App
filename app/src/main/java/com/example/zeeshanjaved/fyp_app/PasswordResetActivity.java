package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;

public class PasswordResetActivity extends AppCompatActivity {

    private EditText editnewpass, editconnewpass;
    private Button btnproceed;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        activity = this;
        editnewpass = (EditText) findViewById(R.id.editresetnewpass);
        editconnewpass = (EditText) findViewById(R.id.editresetconfpass);
        btnproceed = (Button) findViewById(R.id.btnproceed);

//        Intent intent = getIntent();
//        username = intent.getStringExtra("username");

        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                internet_connection();
                String password = editnewpass.getText().toString();
                String confpassword = editconnewpass.getText().toString();

                if(password.trim().equals("") || confpassword.trim().equals("")){

                    Toast.makeText(activity,"Fields should not be empty",Toast.LENGTH_SHORT).show();
                }
                else if(password.length() <=7 || confpassword.length() >= 13){
                    Toast.makeText(activity, "Password Length should 8 to 12 character",Toast.LENGTH_SHORT).show();
                }
                else if(! editnewpass.getText().toString().matches(editconnewpass.getText().toString())){
                    Toast.makeText(activity, "Password Not Matched",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(internet_connection()){
                        PasswordResetAsync passwordResetAsync = new PasswordResetAsync(activity
                                , editnewpass.getText().toString());
                        passwordResetAsync.execute();
                    }
                    else{
                        snackbardisplay();
                    }


                }

            }
        });
    }

    public boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnect = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnect;
    }


    public void  snackbardisplay(){

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                "No Internet Connection", Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(ContextCompat.getColor(this,R.color.incorrectanscolor));

        snackbar.setAction(R.string.InternetConnection, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                finish();
//                Intent intent = new Intent(, SplashActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
            }
        }).show();
    }


}

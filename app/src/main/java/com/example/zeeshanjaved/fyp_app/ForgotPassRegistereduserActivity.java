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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;

public class ForgotPassRegistereduserActivity extends AppCompatActivity {

    private ScrollView scrolluser;
    private EditText editregisteredusername;
    private Button btnnext;
    private ImageView imgexampprep;
    private TextView textvaliduser;

    // private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_registereduser);

        scrolluser =(ScrollView) findViewById(R.id.scrollviewregisterduser);
        editregisteredusername = (EditText) findViewById(R.id.editforgotuser);
        btnnext = (Button) findViewById(R.id.btnfornext);
        imgexampprep = (ImageView) findViewById(R.id.imageexamprep);
        textvaliduser = (TextView) findViewById(R.id.textvalidusername);

        // username doseno t exist
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet_connection();
                if (editregisteredusername.getText().toString().trim().equals("")) {
                    Toast.makeText(ForgotPassRegistereduserActivity.this, "Username should not empty", Toast.LENGTH_SHORT).show();
                } else {
                    if(internet_connection()){
                        RegisteredUsernameAsync registeredUsernameAsync = new RegisteredUsernameAsync(ForgotPassRegistereduserActivity.this,
                                editregisteredusername.getText().toString());
                        registeredUsernameAsync.execute();
                    }
                    else {
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

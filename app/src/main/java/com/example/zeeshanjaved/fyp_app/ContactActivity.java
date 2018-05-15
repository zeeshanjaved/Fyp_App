package com.example.zeeshanjaved.fyp_app;

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

public class ContactActivity extends AppCompatActivity {

    private TextView textcontactheading, textline1, textline2, textline3;
    private ImageView imgcontactname, imgcontactnum, imgcontactemail;
    private EditText editcontactname, editcontactnum, editcontactemail, editproblem;
    private Button btncotactproceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        editcontactname = (EditText) findViewById(R.id.editcontactname);
        editcontactnum = (EditText)findViewById(R.id.editcontactnum);
        editcontactemail = (EditText)findViewById(R.id.editcontactemail);
        editproblem =(EditText) findViewById(R.id.editproblem);

       // internet_connection();
        btncotactproceed = (Button) findViewById(R.id.btncotactproceed);

        btncotactproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet_connection();
                if(isConnect){
                    if (editcontactname.getText().toString().trim().equals("") ||
                            editcontactnum.getText().toString().trim().equals("") ||
                            editcontactemail.getText().toString().trim().equals("") ||
                            editproblem.getText().toString().trim().equals("")) {

                        Toast.makeText(ContactActivity.this, "All Fields must be filled! Try Agin.", Toast.LENGTH_SHORT).show();
                    } else if (!editcontactemail.getText().toString().contains("@")) {
                        Toast.makeText(ContactActivity.this, "Email isn't correct!", Toast.LENGTH_LONG).show();
                    }else if(! editcontactemail.getText().toString().contains(".com")){
                        Toast.makeText(ContactActivity.this, "Email isn't correct!", Toast.LENGTH_LONG).show();
                    }
                    else if (editcontactnum.getText().toString().length() != 11) {
                        Toast.makeText(ContactActivity.this, "Phone Format not Correct! Try Again.", Toast.LENGTH_SHORT).show();
                    } else {

                        ContactUsAsync contactUsAsync = new ContactUsAsync(ContactActivity.this,
                                editcontactname.getText().toString(), editcontactnum.getText().toString(),
                                editcontactemail.getText().toString(), editproblem.getText().toString());
                        contactUsAsync.execute();

                    }
                }
                else{
                    snackbardisplay();
                }

            }
        });

    }
    boolean internet_connection(){
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

        snackbar.setActionTextColor(ContextCompat.getColor(this,R.color.incorrectanscolor)).show();

//        snackbar.setAction(R.string.InternetConnection, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                finish();
//                Intent intent = new Intent(ContactActivity.this, SplashActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//            }
//        }).show();
    }

}

package com.example.zeeshanjaved.fyp_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static java.security.AccessController.getContext;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editphone, editcode;
    private Button btncode, btnnext;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    private String mVerificationId;
    private String username;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editphone = (EditText) findViewById(R.id.editphonenumber);
        editcode = (EditText) findViewById(R.id.editcodenumber);
        btnnext = (Button) findViewById(R.id.btnnext);
        btncode = (Button) findViewById(R.id.btncode);

        mAuth = FirebaseAuth.getInstance();

        Intent intent =  getIntent();
        username = intent.getStringExtra("username");

        mAuthlistener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

//               if(firebaseAuth.getCurrentUser() !=null){


//                   Intent intent1 = new Intent(ForgotPasswordActivity.this,PasswordResetActivity.class);
//                   intent1.putExtra("username",username);
//                   startActivity(intent1);

                   //      Toast.makeText(ForgotPasswordActivity.this,"Welcome Forgot Password Activity! " + firebaseAuth.getCurrentUser().getProviderId(), Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(ForgotPasswordActivity.this,PasswordResetActivity.class);
//                   startActivity(intent);
//                   finish();
  //             }
           }
       };

        btncode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String  phonenumber = editphone.getText().toString();
                if(phonenumber.trim().equals("")){

                    Toast.makeText(ForgotPasswordActivity.this, "Please Enter Valid Phone Number", Toast.LENGTH_LONG).show();
                }
                else {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phonenumber, 60, TimeUnit.SECONDS, ForgotPasswordActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                    //
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    Toast.makeText(ForgotPasswordActivity.this, "OnVerification Failed" +e.getMessage(),Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    // super.onCodeSent(s, forceResendingToken);
                                    mVerificationId= s;
                                }

                                @Override
                                public void onCodeAutoRetrievalTimeOut(String s) {
                                    // super.onCodeAutoRetrievalTimeOut(s);
                                    Toast.makeText(ForgotPasswordActivity.this, "On Code AutoRetrival TIme out " +s, Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                }

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editcode.getText().toString();
                if(code.trim().equals("")){
                    Toast.makeText(ForgotPasswordActivity.this," Please enter verified code ",Toast.LENGTH_LONG).show();
                }
                else
                signInWithCredential(PhoneAuthProvider.getCredential(mVerificationId,code));
            }
        });

    }

    public void signInWithCredential(PhoneAuthCredential phoneAuthCredential){

        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(ForgotPasswordActivity.this, "Password Reset Activity ", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this,PasswordResetActivity.class);
                          //  intent.putExtra("username",username);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ForgotPasswordActivity.this, "Failed To Password Reset Activity: " + task.getException().getMessage()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(ForgotPasswordActivity.this , ForgotPassRegistereduserActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
//    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthlistener);
    }

}

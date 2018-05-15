package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFrag extends Fragment {

    private Activity activity;  // Context of Main Activity
    private Button btnsignup, btnlogin;
    private TextView textforgot_password;
    private EditText editusername, editpassword;
    private String username, password;
    private View view;
    public static boolean isConnect;

    public LoginFrag(Activity activity) {

        this.activity = activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        btnsignup = view.findViewById(R.id.btnsignup);
        btnlogin = view.findViewById(R.id.btnlogin);
        textforgot_password = view.findViewById(R.id.textforgot_password);
        editusername = view.findViewById(R.id.editusername);
        editpassword = view.findViewById(R.id.editpassword);


      //  internet_connection();
       /*
       * Forgot password clickable*/
        textforgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet_connection();
                if(isConnect){
                    Intent intent = new Intent(activity, ForgotPassRegistereduserActivity.class);
                    startActivity(intent);
                }
                else{
                    snackbardisplay();
                }


            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = activity.getSharedPreferences("specificusername",Context.MODE_APPEND);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("s_username",editusername.getText().toString());
                editor.commit();

                internet_connection();
                if(isConnect){
                    StudentLoginAsyncTask studentLoginAsyncTask = new StudentLoginAsyncTask(activity,
                            editusername.getText().toString(), editpassword.getText().toString());
                    studentLoginAsyncTask.execute();
                }
                else {

                    snackbardisplay();
                }
            }

        });
        /*
       * Sign Up buttnon clickable*/

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet_connection();
                if(isConnect){
                    FragmentManager fragmentManager = ((MainActivity) activity).getSupportFragmentManager();
                    SignUpFrag signUpFrag = new SignUpFrag(activity);
                    fragmentManager.beginTransaction().replace(R.id.fragmentcontainer, signUpFrag).commit();
                }
                else {
                    snackbardisplay();
                }

            }
        });

        return view;
    }

    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)activity.getSystemService(activity.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
         isConnect = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnect;
    }


    public void  snackbardisplay(){

        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                "No Internet Connection", Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(ContextCompat.getColor(activity,R.color.incorrectanscolor));

        snackbar.setAction(R.string.InternetConnection, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.finish();
                Intent intent = new Intent(activity, SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        }).show();
    }

}





package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisteredTestDescriptionFrag extends Fragment {

    private TextView textreaddescription, textreaddescription1,textreaddescription2,textreaddescription3;
    private TextView textreaddescription4,textreaddescription5,textreaddescription6;
    private Button btnnext;
    private Activity activity; // Context of Registered Test Activity
    SharedPreferences usernameSP;
    SharedPreferences.Editor editor;
    private String username;
    public RegisteredTestDescriptionFrag(Activity activity) {
        this.activity=activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registered_test_description, container, false);

        textreaddescription = (TextView) view.findViewById(R.id.textreaddescription);
        textreaddescription1 = (TextView) view.findViewById(R.id.textreaddescription1);
        textreaddescription2 = (TextView) view.findViewById(R.id.textreaddescription2);
        textreaddescription3 = (TextView) view.findViewById(R.id.textreaddescription3);
        textreaddescription4 = (TextView) view.findViewById(R.id.textreaddescription4);
        textreaddescription5 = (TextView) view.findViewById(R.id.textreaddescription5);
        textreaddescription6 = (TextView) view.findViewById(R.id.textreaddescription6);
        btnnext = (Button) view.findViewById(R.id.btnnext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameSP = activity.getSharedPreferences("specificusername", Context.MODE_PRIVATE);
                editor = usernameSP.edit();
                username = usernameSP.getString("s_username", null);

                internet_connection();
                if(isConnect){

                    NetworkCallForRegTestCategoryList networkCallForRegTestCategoryList = new NetworkCallForRegTestCategoryList(
                            activity, username);
                    networkCallForRegTestCategoryList.execute();
                }
                else{
                    snackbardisplay();
                }
//                NetworkCallForRegTestCategoryList networkCallForRegTestCategoryList = new NetworkCallForRegTestCategoryList(activity);
//                networkCallForRegTestCategoryList.execute();
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

//                activity.finish();
//                Intent intent = new Intent(activity, SplashActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
            }
        }).show();
    }
}

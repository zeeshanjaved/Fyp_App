package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 10/11/2017.
 */

public class SignUpLoginAsyncTask extends AsyncTask<Void, Void, String> {

    private Activity activity;
    private String fullname, username, email, password, contactno;
    private String jsonresult;

    public SignUpLoginAsyncTask(Activity activity,String fullname, String username, String email, String password,
                               String contactno){
        this.activity=activity;
        this.fullname=fullname;
        this.username=username;
        this.email=email;
        this.password=password;
        this.contactno=contactno;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formbody = new FormBody.Builder()
                .add("fullname", fullname)
                .add("username",username)
                .add("email", email)
                .add("password", password)
                .add("contactno", contactno)
                .build();

        Request request = new Request.Builder().url(IP_ADDRESS+"checkifuserexist")
                .post(formbody).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            jsonresult = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonresult;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s.equals("true")){

             android.support.v4.app.FragmentManager fragmentManager = ((MainActivity) activity).getSupportFragmentManager();
            LoginFrag loginFrag = new LoginFrag(activity);
            fragmentManager.beginTransaction().replace(R.id.fragmentcontainer, loginFrag).commit();
//            Intent intent = new Intent(activity, Main2Activity.class);
//            ((MainActivity)activity).startActivity(intent);
        }

        else
            Toast.makeText(activity,"Username already exist! Try another one.",Toast.LENGTH_LONG).show();
    }
}

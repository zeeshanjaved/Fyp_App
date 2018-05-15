package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 12/11/2017.
 */

public class RegisteredUsernameAsync extends AsyncTask<Void,Void,String> {

    private Activity activity;
    private String jsonresult;
    private String username;

    public RegisteredUsernameAsync(Activity activity, String username){
        this.activity=activity;
        this.username=username;
    }
    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"registeredusers")
                .post(requestBody)
                .build();

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
            Intent intent = new Intent(activity, ForgotPasswordActivity.class);
            SharedPreferences sharedPreferences = activity.getSharedPreferences("temp_username",Context.MODE_APPEND);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName",username).commit();

            ((ForgotPassRegistereduserActivity) activity).startActivity(intent);
        }

        else{
            Toast.makeText(activity,"Username does not Exist",Toast.LENGTH_SHORT).show();
        }

    }
}

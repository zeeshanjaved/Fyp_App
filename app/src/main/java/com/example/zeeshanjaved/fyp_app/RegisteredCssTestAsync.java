package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_APPEND;
import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 1/2/2018.
 */

public class RegisteredCssTestAsync extends AsyncTask<Void,Void,String> {

    private Activity activity; // Registered Test Activity
    private String jsonresult;

    public RegisteredCssTestAsync(Activity activity){
        this.activity=activity;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"registeredcsstestcontoller")
                .get()
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

        SharedPreferences sharedPreferences = activity.getSharedPreferences("regTest",MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CssTest",s.toString()).commit();

        Intent intent = new Intent(activity, CssRegisteredTestActivity.class);
        ((RegisteredTestActivity)activity).startActivity(intent);


    }
}

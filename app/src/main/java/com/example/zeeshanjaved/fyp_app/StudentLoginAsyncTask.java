package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 10/3/2017.
 */

public class StudentLoginAsyncTask extends AsyncTask<Void, Void, String> {

    private Activity activity;
    private String username, password;
    private String jsonresult;

    public StudentLoginAsyncTask(Activity activity, String username, String password) {
        this.activity = activity;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formbody = new FormBody.Builder().add("username", username).add("password", password).build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"loginapi").post(formbody)
                .build();
//
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
            Intent intent = new Intent(activity, Main2Activity.class);
            ((MainActivity)activity).startActivity(intent);
        }
        else
            Toast.makeText(activity,"Username or password Incorrect",Toast.LENGTH_LONG).show();
    }

}


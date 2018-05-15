package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by Zeeshan Javed on 12/11/2017.
 */

public class PasswordResetAsync extends AsyncTask<Void, Void, String> {

    private Activity activity;
    private String jsonresult;
    private String newpassword, username;
    SharedPreferences usernameSP;
    SharedPreferences.Editor editor;

    public PasswordResetAsync(Activity activity, String newpassword) {
        this.activity = activity;
        this.newpassword = newpassword;
    }

    @Override
    protected void onPreExecute() {
        usernameSP = activity.getSharedPreferences("temp_username", Context.MODE_PRIVATE);
        editor = usernameSP.edit();
        username = usernameSP.getString("userName", null);
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", newpassword)
                .build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"updatepassword")
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

        if (s.equals("true")) {
            editor.clear();
            editor.commit();
            Toast.makeText(activity, "Password updated successfully!", Toast.LENGTH_SHORT).show();
            activity.startActivity(new Intent(activity, MainActivity.class));
        } else
            Toast.makeText(activity, "Passwored Reset Failed", Toast.LENGTH_SHORT).show();

    }
}

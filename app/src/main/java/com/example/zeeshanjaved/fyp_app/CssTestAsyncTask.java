package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_APPEND;
import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 12/18/2017.
 */

public class CssTestAsyncTask extends AsyncTask<Void,Void,String> {

    private Activity activity; // context of main3activity
    private String jsonresult;

    public CssTestAsyncTask(Activity activity){
        this.activity=activity;
    }
    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"csstestcontroller")
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
    protected void onPostExecute(String jsonresponse) {
        super.onPostExecute(jsonresponse);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("Tests", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cssTest", jsonresponse.toString()).commit();

        activity.startActivity( new Intent(activity,CssTestTrialActivity.class));



    }
}

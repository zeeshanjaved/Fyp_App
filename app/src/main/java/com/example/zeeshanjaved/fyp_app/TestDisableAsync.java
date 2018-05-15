package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 1/16/2018.
 */

public class TestDisableAsync extends AsyncTask<Void,Void,String> {

    private Activity activity ; //
    private String jsonresult;
    private String username, categoryname, status;

    public TestDisableAsync(Activity activity,String username,String categoryname, String status ) {
        this.activity = activity;
        this.username=username;
        this.categoryname=categoryname;
        this.status=status;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",username)
                .add("category_name",categoryname)
                .add("status",status)
                .build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"updateTestStatus")
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
    }
}

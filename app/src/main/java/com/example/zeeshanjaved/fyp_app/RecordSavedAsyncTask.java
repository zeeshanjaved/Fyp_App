package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 1/3/2018.
 */

public class RecordSavedAsyncTask extends AsyncTask<Void,Void,String> {

    private Activity activity; // Context of Afor Activity
    private String question, useranswer, correctans;
    private String jsonresult;

    public RecordSavedAsyncTask(Activity activity, String question, String useranswer, String correctans) {
        this.activity = activity;
        this.question = question;
        this.useranswer = useranswer;
        this.correctans = correctans;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("regcss_question",question)
                .add("regcss_useranswer",useranswer)
                .add("regcss_correctans",correctans)
                .build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"regtestrecordscontroller")
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

package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
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
 * Created by Zeeshan Javed on 12/12/2017.
 */

public class ContactUsAsync extends AsyncTask<Void,Void,String> {

    private Activity activity;
    private String name, email, contactno, message;
    private String jsonresult;

    public ContactUsAsync(Activity activity, String name, String contactno, String email,  String message){
        this.activity=activity;
        this.name=name;
        this.email=email;
        this.contactno=contactno;
        this.message=message;
    }
    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("fullname",name)
                .add("contactno",contactno)
                .add("email",email)
                .add("message",message)
                .build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"contactuscontroller")
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

            Toast.makeText(activity,"Submitted Successfully!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, Main2Activity.class);
            ((ContactActivity)activity).startActivity(intent);
        }
        else
            Toast.makeText(activity,"Submission Failed",Toast.LENGTH_SHORT).show();
    }
}

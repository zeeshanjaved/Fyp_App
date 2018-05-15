package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
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
 * Created by Zeeshan Javed on 12/18/2017.
 */

public class NetworkCallTrialListview extends AsyncTask<Void,Void,String> {

    private Activity activity;  // context of main3activity
    private String jsonresult;


    public NetworkCallTrialListview(Activity activity ){
        this.activity=activity;

    }
    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient =new OkHttpClient();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"categorylistcontroller")
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

        SharedPreferences sharedPreferences = activity.getSharedPreferences("storeunreglist",MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unreglist",s.toString()).commit();

        Gson gson = new Gson();
        List<TrialTestModel> trialTestModels= gson.fromJson(jsonresult, new TypeToken<ArrayList<TrialTestModel>>()
        {}.getType());

        FragmentManager fragmentManager = ((Main3Activity)activity).getSupportFragmentManager();
        TrialTestFragment trialTestFragment = new TrialTestFragment(activity, trialTestModels);
        fragmentManager.beginTransaction().add(R.id.trialtestselectioncontainor,trialTestFragment).commit();

    }
}

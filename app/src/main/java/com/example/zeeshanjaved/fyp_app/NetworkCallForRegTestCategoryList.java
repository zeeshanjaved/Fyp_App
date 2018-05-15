package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_APPEND;
import static com.example.zeeshanjaved.fyp_app.MainActivity.IP_ADDRESS;

/**
 * Created by Zeeshan Javed on 1/1/2018.
 */

public class NetworkCallForRegTestCategoryList extends AsyncTask<Void,Void,String> {

    private Activity activity; // Registered Test Activity Context
    private String jsonresult;
    private String user_name;

    public NetworkCallForRegTestCategoryList (Activity activity, String user_name){
        this.activity=activity;
        this.user_name=user_name;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",user_name)
                .build();

        Request request = new Request.Builder()
                .url(IP_ADDRESS+"gettestlist")
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


        Gson gson = new Gson();
        List<RegTestCategoryListModel> regTestCategoryListModels = gson.fromJson(jsonresult,
                new TypeToken<ArrayList<RegTestCategoryListModel>> (){}.getType());

        FragmentManager fragmentManager = ((RegisteredTestActivity)activity).getSupportFragmentManager();
        RegisteredTestCategoryFrag registeredTestCategoryFrag = new RegisteredTestCategoryFrag(activity, regTestCategoryListModels);
        fragmentManager.beginTransaction().replace(R.id.registeredtestcontainor, registeredTestCategoryFrag)
                .addToBackStack("Back to reg desc frag")
                .commit();
    }
}

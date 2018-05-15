package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrialTestFragment extends Fragment {

    private ListView listviewtrialtest;
    private ImageView imgtop;
    private List<TrialTestModel> trialTestModels;
    private Activity activity;  // context of main3activity

    public TrialTestFragment(Activity activity, List<TrialTestModel> trialTestModels ) {
        this.activity=activity;
        this.trialTestModels=trialTestModels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trial_test, container, false);

        listviewtrialtest = view.findViewById(R.id.listviewtrialtest);
        imgtop = view.findViewById(R.id.imgtop);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("storeunreglist", Context.MODE_PRIVATE);
        String unreglist = sharedPreferences.getString("unreglist",null);

        Gson gson =  new Gson();
        final List<UnregListModel> unregListModels = gson.fromJson(unreglist, new TypeToken<ArrayList<UnregListModel>>()
        {}.getType());


        CustomAdapterofTrialTest customAdapterofTrialTest = new CustomAdapterofTrialTest(activity, trialTestModels);
        listviewtrialtest.setAdapter(customAdapterofTrialTest);

        listviewtrialtest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                internet_connection();
                if(position==0){

                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("HTML")) {
                        if (isConnect) {
                            HtmlTestAsyncTask htmlTestAsyncTask = new HtmlTestAsyncTask(activity);
                            htmlTestAsyncTask.execute();
                        } else {
                            Toast.makeText(activity, "Please connect to internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("CSS")){
                            if(isConnect){
                                CssTestAsyncTask cssTestAsyncTask = new CssTestAsyncTask(activity);
                                cssTestAsyncTask.execute();
                            }
                            else{
                                Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                            }
                    }
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("PHP")){
                                if(isConnect){
                                    PhpTestAsyncTask phpTestAsyncTask = new PhpTestAsyncTask(activity);
                                    phpTestAsyncTask.execute();
                                }
                                else{
                                    Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                                }
                    }
                }
                else if(position==1){
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("HTML")) {
                        if (isConnect) {
                            HtmlTestAsyncTask htmlTestAsyncTask = new HtmlTestAsyncTask(activity);
                            htmlTestAsyncTask.execute();
                        } else {
                            Toast.makeText(activity, "Please connect to internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("CSS")){
                        if(isConnect){
                            CssTestAsyncTask cssTestAsyncTask = new CssTestAsyncTask(activity);
                            cssTestAsyncTask.execute();
                        }
                        else{
                            Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("PHP")){
                        if(isConnect){
                            PhpTestAsyncTask phpTestAsyncTask = new PhpTestAsyncTask(activity);
                            phpTestAsyncTask.execute();
                        }
                        else{
                            Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else if(position==2){

                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("HTML")) {
                        if (isConnect) {
                            HtmlTestAsyncTask htmlTestAsyncTask = new HtmlTestAsyncTask(activity);
                            htmlTestAsyncTask.execute();
                        } else {
                            Toast.makeText(activity, "Please connect to internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("CSS")){
                        if(isConnect){
                            CssTestAsyncTask cssTestAsyncTask = new CssTestAsyncTask(activity);
                            cssTestAsyncTask.execute();
                        }
                        else{
                            Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(unregListModels.get(position).getCategoryname().equalsIgnoreCase("PHP")){
                        if(isConnect){
                            PhpTestAsyncTask phpTestAsyncTask = new PhpTestAsyncTask(activity);
                            phpTestAsyncTask.execute();
                        }
                        else{
                            Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    if(isConnect){
                        Toast.makeText(activity," Coming Soon!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(activity,"Please connect to internet",Toast.LENGTH_SHORT).show();
                    }

//                 Toast.makeText(activity," Not Found",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return  view;
    }
    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)activity.getSystemService(activity.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnect = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnect;
    }


}

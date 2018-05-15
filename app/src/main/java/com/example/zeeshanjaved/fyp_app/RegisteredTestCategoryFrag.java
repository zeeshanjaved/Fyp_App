package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;
import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisteredTestCategoryFrag extends Fragment {

    private Activity activity; // Context of Registered Test Activity
    private ListView listview;
    private ImageView imgtop;
    private List<RegTestCategoryListModel> regTestCategoryListModels;
    String storecategoryname;
    String storeusername;
    String status="false";

    public RegisteredTestCategoryFrag(Activity activity, List<RegTestCategoryListModel> regTestCategoryListModels ) {
        this.activity=activity;
        this.regTestCategoryListModels=regTestCategoryListModels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_registered_test_category, container, false);
        listview = (ListView) view.findViewById(R.id.listregisteredtestcat);
        imgtop = (ImageView) view.findViewById(R.id.imgregisteredtest);

        CustomAdaptorRegisteredTest customAdaptorRegisteredTest = new CustomAdaptorRegisteredTest(activity,regTestCategoryListModels);
        listview.setAdapter(customAdaptorRegisteredTest);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                internet_connection();
                if(position==0){

                    if(regTestCategoryListModels.get(position).getStatus().equals("true") &&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("CSS")){

                        storecategoryname = regTestCategoryListModels.get(position).getCategory_name();
                        storeusername = regTestCategoryListModels.get(position).getUser_name();
                       SharedPreferences sharedPreferences = activity.getSharedPreferences("store",MODE_APPEND);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("storeusername",storeusername.toString());
                        editor.putString("storecategoryname",storecategoryname.toString());
                        editor.putString("updatestatus",status);
                        editor.commit();

                        if(isConnect)
                        {

                            RegisteredCssTestAsync registeredCssTestAsync = new RegisteredCssTestAsync(activity);
                            registeredCssTestAsync.execute();


                        }
                        else
                        {
                            Toast.makeText(activity,"Please connect to Internet",Toast.LENGTH_SHORT).show();
                        }
//                        RegisteredCssTestAsync registeredCssTestAsync = new RegisteredCssTestAsync(activity);
//                        registeredCssTestAsync.execute();
                    }
                    else if(regTestCategoryListModels.get(position).getStatus().equals("true") &&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("JAVA")){

                        storecategoryname = regTestCategoryListModels.get(position).getCategory_name();
                        storeusername = regTestCategoryListModels.get(position).getUser_name();
                        SharedPreferences sharedPreferences = activity.getSharedPreferences("store",MODE_APPEND);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("storeusername",storeusername.toString());
                        editor.putString("storecategoryname",storecategoryname.toString());
                        editor.putString("updatestatus",status);
                        editor.commit();

                        if(isConnect)
                        {
//                            RegisteredCssTestAsync registeredCssTestAsync = new RegisteredCssTestAsync(activity);
//                            registeredCssTestAsync.execute();

// JAVA Async
                        }
                        else
                        {
                            Toast.makeText(activity,"Please connect to Internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(regTestCategoryListModels.get(position).getStatus().equalsIgnoreCase("false")
                            && regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("JAVA")){

                        Toast.makeText(activity,"You have no permission to perform this test now!",Toast.LENGTH_SHORT).show();
                    }
                    else if(regTestCategoryListModels.get(position).getStatus().equalsIgnoreCase("false")&&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("CSS")){
                        Toast.makeText(activity,"You have no permission to perform this test now!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(activity,"Coming Soon!",Toast.LENGTH_SHORT).show();
                    }

                }
                else if(position==1){
                    if(regTestCategoryListModels.get(position).getStatus().equals("true") &&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("CSS")){

                        storecategoryname = regTestCategoryListModels.get(position).getCategory_name();
                        storeusername = regTestCategoryListModels.get(position).getUser_name();
                        SharedPreferences sharedPreferences = activity.getSharedPreferences("store",MODE_APPEND);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("storeusername",storeusername.toString());
                        editor.putString("storecategoryname",storecategoryname.toString());
                        editor.putString("updatestatus",status);
                        editor.commit();

                        if(isConnect)
                        {
                            RegisteredCssTestAsync registeredCssTestAsync = new RegisteredCssTestAsync(activity);
                            registeredCssTestAsync.execute();
                        }
                        else
                        {
                            Toast.makeText(activity,"Please connect to Internet",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(regTestCategoryListModels.get(position).getStatus().equals("true") &&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("JAVA")){

                        storecategoryname = regTestCategoryListModels.get(position).getCategory_name();
                        storeusername = regTestCategoryListModels.get(position).getUser_name();
                        SharedPreferences sharedPreferences = activity.getSharedPreferences("store",MODE_APPEND);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("storeusername",storeusername.toString());
                        editor.putString("storecategoryname",storecategoryname.toString());
                        editor.putString("updatestatus",status);
                        editor.commit();

                        if(isConnect)
                        {
//                            RegisteredCssTestAsync registeredCssTestAsync = new RegisteredCssTestAsync(activity);
//                            registeredCssTestAsync.execute();

// JAVA Async
                        }
                        else
                        {
                            Toast.makeText(activity,"Please connect to Internet",Toast.LENGTH_SHORT).show();
                        }
//                        RegisteredCssTestAsync registeredCssTestAsync = new RegisteredCssTestAsync(activity);
//                        registeredCssTestAsync.execute();
                    }
                    else if(regTestCategoryListModels.get(position).getStatus().equalsIgnoreCase("false") &&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("JAVA")){
                        Toast.makeText(activity,"You have no permission to perform this test now!",Toast.LENGTH_SHORT).show();
                    }
                    else if(regTestCategoryListModels.get(position).getStatus().equalsIgnoreCase("false") &&
                            regTestCategoryListModels.get(position).getCategory_name().equalsIgnoreCase("CSS")){

                        Toast.makeText(activity,"You have no permission to perform this test now!",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(activity,"Coming Soon!",Toast.LENGTH_SHORT).show();
                    }

                }

                else {
                    Toast.makeText(activity,"Coming Soon!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view ;
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

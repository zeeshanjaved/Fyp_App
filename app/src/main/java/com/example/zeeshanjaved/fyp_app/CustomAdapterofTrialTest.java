package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zeeshan Javed on 12/18/2017.
 */

public class CustomAdapterofTrialTest extends ArrayAdapter<TrialTestModel> {


    private Activity activity;  // context of main3activity
    private static LayoutInflater inflater;
    private List<TrialTestModel> trialTestModels;

   public CustomAdapterofTrialTest(Activity activity, List<TrialTestModel> trialTestModels){
       super(activity,0,trialTestModels);
       this.activity=activity;
       this.trialTestModels=trialTestModels;

       inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
   }

    public class Holder
    {
        TextView categoryname;
        ImageView img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TrialTestModel trialTestModel = getItem(position);

        Holder holder = new Holder();

        View view;
        view = inflater.inflate(R.layout.customlistviewtrialtest, parent, false);

        holder.categoryname = (TextView) view.findViewById(R.id.textoflistview);
        holder.img = (ImageView) view.findViewById(R.id.imgoflistview);

        holder.categoryname.setText(trialTestModels.get(position).getCategoryname());
        holder.img.setImageResource(R.drawable.css);

        return view;
    }
}

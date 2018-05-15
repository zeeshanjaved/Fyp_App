package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.graphics.Color;
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
 * Created by Zeeshan Javed on 1/14/2018.
 */

public class CustomAdaptorReg extends ArrayAdapter<Joiner> {

    private Activity activity;  // context of main3activity
    private static LayoutInflater inflater;
    private List<Joiner> joiners;

    public CustomAdaptorReg(Activity activity, List<Joiner> joiners){
        super(activity,0,joiners);
        this.activity=activity;
        this.joiners=joiners;

        inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
    }

    public class Holder
    {
        TextView question;
        TextView correctans;
        TextView userans;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Joiner joiner = getItem(position);

        CustomAdaptorReg.Holder holder = new CustomAdaptorReg.Holder();

        View view;
        view = inflater.inflate(R.layout.displayresult, parent, false);

        holder.question = (TextView) view.findViewById(R.id.question);
        holder.correctans = (TextView) view.findViewById(R.id.corectans);
        holder.userans = (TextView) view.findViewById(R.id.userans);

       holder.question.setText("Answers");
        holder.correctans.setText(joiner.getCorrctanswer().toString());
        holder.userans.setText(joiner.getActualanswer().toString());

        if(holder.userans.getText().toString().equals("Not Chosen any given options")){
            holder.userans.setTextColor(Color.RED);
        }


        return view;
    }
}

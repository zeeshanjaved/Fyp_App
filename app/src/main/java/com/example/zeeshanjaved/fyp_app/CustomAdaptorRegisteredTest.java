package com.example.zeeshanjaved.fyp_app;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Zeeshan Javed on 12/30/2017.
 */

public class CustomAdaptorRegisteredTest extends ArrayAdapter<RegTestCategoryListModel> {

    private Activity activity; // Context of Registered Test Activity
    private static LayoutInflater inflater;
    private List<RegTestCategoryListModel> regTestCategoryListModels;

    public CustomAdaptorRegisteredTest(Activity activity, List<RegTestCategoryListModel> regTestCategoryListModels){
        super(activity,0,regTestCategoryListModels);
        this.activity=activity;
        this.regTestCategoryListModels=regTestCategoryListModels;

        inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
    }

    public class Holder
    {
        TextView categoryname;
        TextView availabilitystatus;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        RegTestCategoryListModel regTestCategoryListModel = getItem(position);

        Holder holder = new Holder();

        View view;
        view = inflater.inflate(R.layout.customlistviewofregisteredtest, parent, false);

        holder.categoryname = (TextView) view.findViewById(R.id.textoflistview1);
        holder.availabilitystatus = (TextView) view.findViewById(R.id.textoflistview2);

        holder.categoryname.setText(regTestCategoryListModels.get(position).getCategory_name());
        holder.availabilitystatus.setText(regTestCategoryListModels.get(position).getStatus());
      //  Picasso.with(activity).load(regTestCategoryListModels.get(position).getImage()).into(holder.image);
       // holder.image.setImageURI(Uri.parse(regTestCategoryListModels.get(position).getImage()));
      //  Picasso.with(activity).load(regTestCategoryListModels.get(position).getImage()).into(holder.image);
       // holder.image.setImageResource(R.drawable.css);
      //  holder.image.setImageResource(Integer.parseInt(regTestCategoryListModels.get(position).getImage()));
      //  holder.image.setImageURI(Uri.parse(regTestCategoryListModels.get(position).getImage()));

        return view;

    }
}

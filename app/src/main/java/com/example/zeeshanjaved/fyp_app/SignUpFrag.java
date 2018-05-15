package com.example.zeeshanjaved.fyp_app;


import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.zeeshanjaved.fyp_app.LoginFrag.isConnect;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFrag extends Fragment {

    private Activity activity;
    private TextView textalreadylogin;
    private TextView textsignup_title;
    private Button btncreateaccount;
    private ImageView imgfullname, imgusername, imgemail, imgpassword, imgconfpass, imgcontact;
    private EditText editfullname, editusername, editemail, editpassword, editconfpass, editcontact;
    private String getpassword;

    public SignUpFrag(Activity activity) {

        this.activity = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragsign_up, container, false);

        textsignup_title = (TextView) view.findViewById(R.id.textsignup_title);
        textalreadylogin = (TextView) view.findViewById(R.id.textalreadylogin);
        btncreateaccount = (Button) view.findViewById(R.id.btncreateaccount);

        imgfullname = (ImageView) view.findViewById(R.id.imagefullname);
        imgusername = (ImageView) view.findViewById(R.id.imageusername);
        imgemail = (ImageView) view.findViewById(R.id.imageemail);
        imgpassword = (ImageView) view.findViewById(R.id.imagepassword);
        imgconfpass = (ImageView) view.findViewById(R.id.imageconfpassword);
        imgcontact = (ImageView) view.findViewById(R.id.imagecontact);

        editfullname = (EditText) view.findViewById(R.id.editfullname);
        editusername = (EditText) view.findViewById(R.id.editusername);
        editemail = (EditText) view.findViewById(R.id.editemail);
        editpassword = (EditText) view.findViewById(R.id.editpassword);
        editconfpass = (EditText) view.findViewById(R.id.editconfpass);
        editcontact = (EditText) view.findViewById(R.id.editcontact);

        textalreadylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((MainActivity) activity).getSupportFragmentManager();
                LoginFrag loginFrag = new LoginFrag(activity);
                fragmentManager.beginTransaction().replace(R.id.fragmentcontainer, loginFrag).commit();
            }
        });

        btncreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet_connection();
                getpassword = editpassword.getText().toString();

                if(isConnect){
                    if (editfullname.getText().toString().trim().equals("") || editusername.getText().toString().trim().equals("") ||
                            editemail.getText().toString().trim().equals("") || editpassword.getText().toString().trim().equals("") ||
                            editconfpass.getText().toString().trim().equals("") || editcontact.getText().toString().trim().equals("")) {

                        Toast.makeText(activity, "All fields must be filled! Try Agin.", Toast.LENGTH_LONG).show();
                    } else if (!editemail.getText().toString().contains("@")) {

                        Toast.makeText(activity, "email is not correct", Toast.LENGTH_SHORT).show();
                    } else if (!editpassword.getText().toString().matches(editconfpass.getText().toString())) {
                        Toast.makeText(activity, "Confirm Password not match!", Toast.LENGTH_LONG).show();
                    } else if (getpassword.length() <= 7 || getpassword.length() >= 13) {
                        Toast.makeText(activity, "Password length 8 to 12 character", Toast.LENGTH_SHORT).show();
                    } else if (editcontact.getText().toString().length() != 11) {
                        Toast.makeText(activity, "Phone Format not Correct! Try Again.", Toast.LENGTH_SHORT).show();
                    } else {

                        SignUpLoginAsyncTask signUpLoginAsyncTask = new SignUpLoginAsyncTask(activity,
                                editfullname.getText().toString(),
                                editusername.getText().toString(),
                                editemail.getText().toString(),
                                editpassword.getText().toString(),
                                editcontact.getText().toString());

                        signUpLoginAsyncTask.execute();
                    }
                }
                else
                {
                    Toast.makeText(activity,"Please connect to Internet",Toast.LENGTH_SHORT).show();
                }
//                if (editfullname.getText().toString().trim().equals("") || editusername.getText().toString().trim().equals("") ||
//                        editemail.getText().toString().trim().equals("") || editpassword.getText().toString().trim().equals("") ||
//                        editconfpass.getText().toString().trim().equals("") || editcontact.getText().toString().trim().equals("")) {
//
//                    Toast.makeText(activity, "All fields must be filled! Try Agin.", Toast.LENGTH_LONG).show();
//                } else if (!editemail.getText().toString().contains("@")) {
//
//                    Toast.makeText(activity, "email is not correct", Toast.LENGTH_SHORT).show();
//                } else if (!editpassword.getText().toString().matches(editconfpass.getText().toString())) {
//                    Toast.makeText(activity, "Confirm Password not match!", Toast.LENGTH_LONG).show();
//                } else if (getpassword.length() <= 7 || getpassword.length() >= 13) {
//                    Toast.makeText(activity, "Password length 8 to 12 character", Toast.LENGTH_SHORT).show();
//                } else if (editcontact.getText().toString().length() != 11) {
//                    Toast.makeText(activity, "Phone Format not Correct! Try Again.", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    SignUpLoginAsyncTask signUpLoginAsyncTask = new SignUpLoginAsyncTask(activity,
//                            editfullname.getText().toString(),
//                            editusername.getText().toString(),
//                            editemail.getText().toString(),
//                            editpassword.getText().toString(),
//                            editcontact.getText().toString());
//
//                    signUpLoginAsyncTask.execute();
//                }
            }
        });

        return view;
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

package com.example.zeeshanjaved.fyp_app;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Zeeshan Javed on 12/5/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    String TAG= "MY TAG";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token: "+ refreshedToken);
    }


}

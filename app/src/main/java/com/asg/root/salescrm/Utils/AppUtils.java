package com.asg.root.salescrm.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class AppUtils {

    public static boolean isOnline( Context context ) {
        ConnectivityManager cm = (ConnectivityManager) context. getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}

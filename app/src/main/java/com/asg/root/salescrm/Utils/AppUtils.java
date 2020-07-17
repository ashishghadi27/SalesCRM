package com.asg.root.salescrm.Utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;
import static android.content.Context.ACTIVITY_SERVICE;

public class AppUtils {

    public static boolean isOnline( Context context ) {
        ConnectivityManager cm = (ConnectivityManager) context. getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}

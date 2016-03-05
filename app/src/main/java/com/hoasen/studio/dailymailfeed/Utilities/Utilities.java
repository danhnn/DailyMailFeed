package com.hoasen.studio.dailymailfeed.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Harry Nguyen on 04-Mar-16.
 */
public class Utilities {
    static public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}

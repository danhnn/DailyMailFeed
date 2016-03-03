package com.hoasen.studio.dailymailfeed.Utilities;

import android.util.Log;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public class DMLog {
    static String KEY = "DMFeed";

    public static void log(String string){
        Log.i(KEY,string);
    }
}

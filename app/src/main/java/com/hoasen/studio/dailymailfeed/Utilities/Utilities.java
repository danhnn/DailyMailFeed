package com.hoasen.studio.dailymailfeed.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Harry Nguyen on 04-Mar-16.
 */
public class Utilities {
    static Context context;

    static public void setContext(Context c) {
        context = c;
    }

    static public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    static public String getXml(String fileName) {
        String xmlString = null;

        try {
            InputStream is = context.getAssets().open(fileName);
            int length = is.available();
            byte[] data = new byte[length];
            is.read(data);
            xmlString = new String(data);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return xmlString;
    }
}

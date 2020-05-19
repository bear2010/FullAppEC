package com.ff.libbasiccore;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class Util {

    private static String TAG = Util.class.getName();

    public static int getScreenWidth() {
        if (GBConfig.getContext() == null) {
            Log.d(TAG, "getScreenWidth: error");
            return 1080;
        }
        final Resources mrs = GBConfig.getContext().getResources();
        final DisplayMetrics dm = mrs.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        if (GBConfig.getContext() == null) {
            Log.d(TAG, "getScreenHeight: error");
            return 1920;
        }
        final Resources mrs = GBConfig.getContext().getResources();
        final DisplayMetrics dm = mrs.getDisplayMetrics();
        return dm.heightPixels;
    }
}

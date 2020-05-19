package com.ff.fullappec;

import android.app.Application;
import android.util.Log;

import com.ff.libbasiccore.GBConfig;
import com.ff.libbasiccore.CFType;

public class MainApp extends Application {

    private String TAG = MainApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        GBConfig.setContext(getApplicationContext());
        GBConfig.setConfig(CFType.API_HOST, "https://123.sogou.com");
        Log.d(TAG, "onCreate: ");
    }
}

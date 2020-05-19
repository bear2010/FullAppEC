package com.ff.fullappec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ff.libbasiccore.BaseFragment;
import com.ff.libbasiccore.GBConfig;
import com.ff.libbasiccore.CFType;
import com.ff.libbasiccore.ProxyActivity;

public class MainActivity extends ProxyActivity {
    private String TAG = MainActivity.class.getName();

    private TextView tTVtest ;

    @Override
    public BaseFragment setDefaultFragment() {
        Log.d(TAG, "setDefaultFragment: ");
        return new RootFragment();
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GBConfig.setContext(this.getApplicationContext());
    }*/

    public void testSth(View view) {
        GBConfig.setConfig(CFType.API_HOST,"www.baidu.com");
        Log.d(TAG, "onCreate: " + CFType.APP_CONTEXT.name());
        Log.d(TAG, "testSth: "+GBConfig.getConfig(CFType.API_HOST));
        tTVtest.setText(GBConfig.getConfig(CFType.API_HOST).toString());
    }
}

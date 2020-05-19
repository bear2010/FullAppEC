package com.ff.fullappec;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ff.libbasiccore.BaseFragment;
import com.ff.libbasiccore.CFType;
import com.ff.libbasiccore.GBConfig;
import com.ff.libbasiccore.net.INetCallback;
import com.ff.libbasiccore.net.RestClient;
import com.ff.libbasiccore.ui.LoaderCreator;

public class RootFragment extends BaseFragment {

    private String TAG = RootFragment.class.getName();
    TextView mTv = null;
    Button mbt = null;

    Handler mainHandler = new Handler(Looper.getMainLooper());
    @Override
    public Object setLayout() {
        Log.d(TAG, "setLayout: ");
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        Log.d(TAG, "onBindView: ");
        mTv = ((TextView) rootview.findViewById(R.id.Tvtest));

        mbt = ((Button) rootview.findViewById(R.id.bttset));
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testSth(v);
                testNet();
            }
        });
    }

    public void testSth(View view) {
        GBConfig.setConfig(CFType.API_HOST,"https://123.sogou.com");
        Log.d(TAG, "onCreate: " + CFType.APP_CONTEXT.name());
        Log.d(TAG, "testSth: "+GBConfig.getConfig(CFType.API_HOST));
        mTv.setText(GBConfig.getConfig(CFType.API_HOST).toString());
    }

    private void testNet()
    {
        String url = "http://www.baidu.com"; //Expected URL scheme 'http' or 'https'
        Log.d(TAG, "testNet: 00000");
        RestClient mClient = new RestClient(url, new INetCallback() {
            @Override
            public void OnSuccess(String response) {
                Log.d(TAG, "OnSuccess: ");
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        mTv.setText(response);
                    }
                });
            }

            @Override
            public void OnError() {
                Log.d(TAG, "OnError: ");
            }

            @Override
            public void OnFailuer() {
                Log.d(TAG, "OnFailuer: ");
            }

            @Override
            public void OnRequestStart() {
                Log.d(TAG, "OnRequestStart: ");
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        LoaderCreator.showLoading(RootFragment.this.getContext());
                    }
                });
            }

            @Override
            public void OnRequestEnd() {
                Log.d(TAG, "OnRequestEnd: ");
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoaderCreator.stopLoading();
                    }
                }, 5000);
            }
        });
        Log.d(TAG, "testNet: 33333");
        mClient.params("","");
        mClient.get();
        Log.d(TAG, "testNet: 999999");
    }
}

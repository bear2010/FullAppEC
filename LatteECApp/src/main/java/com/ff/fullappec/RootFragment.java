package com.ff.fullappec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ff.libbasiccore.BaseFragment;
import com.ff.libbasiccore.CFType;
import com.ff.libbasiccore.GBConfig;

public class RootFragment extends BaseFragment {

    private String TAG = RootFragment.class.getName();
    TextView mTv = null;
    Button mbt = null;
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
            }
        });
    }

    public void testSth(View view) {
        GBConfig.setConfig(CFType.API_HOST,"www.baidu.com");
        Log.d(TAG, "onCreate: " + CFType.APP_CONTEXT.name());
        Log.d(TAG, "testSth: "+GBConfig.getConfig(CFType.API_HOST));
        mTv.setText(GBConfig.getConfig(CFType.API_HOST).toString());
    }
}

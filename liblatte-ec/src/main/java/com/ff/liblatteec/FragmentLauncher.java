package com.ff.liblatteec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.ff.libbasiccore.BaseFragment;

public class FragmentLauncher extends BaseFragment {

    private AppCompatTextView mTv = null;
    private AppCompatTextView mTvR2 = null;
    private String TAG = FragmentLauncher.class.getName();

    @Override
    public Object setLayout() {
        return R.layout.layout_launcer;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

        mTv = ((AppCompatTextView) rootview.findViewById(R.id.tv_timer));
        //在Build。gradle里面，apply plugin: 'com.jakewharton.butterknife'会导致编译出R2。但是无法使用
        //butterknife 的注解才能用R2

//        mTvR2 = ((AppCompatTextView) rootview.findViewById(R2.id.tv_R2));

        Button mBtn = ((Button) rootview.findViewById(R.id.btn_launcher));
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testR2();
            }
        });

    }

    private void testR2() {
        try {
            Log.d(TAG, "onClick: 1=" +mTv);
            Log.d(TAG, "onClick: 1=" +R.id.tv_timer);
            mTv.setText("this is launcehr");
//            Log.d(TAG, "onClick: 2=" +mTvR2);
//            Log.d(TAG, "onClick: 2=" +R2.id.tv_R2);
//            mTvR2.setText("this is R2 TextView");
            Log.d(TAG, "onClick: 3");
            start( new FragmentScrollFlash(),SINGLETASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

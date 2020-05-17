package com.ff.libbasiccore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseFragment extends SwipeBackFragment {

    private String TAG =  BaseFragment.class.getName();

    abstract public Object setLayout();
    abstract public void onBindView(@Nullable Bundle savedInstanceState,  View rootview);

//    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder= null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        Object mObject =  setLayout();
        View rootView = null;
        if (mObject instanceof Integer) {
            Log.d(TAG, "onCreateView: integer");
            rootView = inflater.inflate((Integer) mObject, container, false);
        } else if (mObject instanceof View) {
            Log.d(TAG, "onCreateView: view");
            rootView = (View) mObject;
        }
        Log.d(TAG, "onCreateView: go on");
        if (rootView != null) {
            Log.d(TAG, "onCreateView: not null a");
//            mUnbinder = ButterKnife.bind(this, rootView);
            Log.d(TAG, "onCreateView: not null b");
            onBindView(savedInstanceState, rootView);
            Log.d(TAG, "onCreateView: not null c");
        }
        Log.d(TAG, "onCreateView: rootview"+rootView);
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}

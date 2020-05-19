package com.ff.liblatteec;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ff.libbasiccore.BaseFragment;

public class FragmentSign extends BaseFragment {
    @Override
    public Object setLayout() {
        return R.layout.layout_sign;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        RecyclerView mRecyclerView;
    }
}

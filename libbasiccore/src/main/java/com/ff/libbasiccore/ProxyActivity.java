package com.ff.libbasiccore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {
    public abstract BaseFragment setDefaultFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContent(savedInstanceState);
    }

    private void initContent(@Nullable Bundle savedInstanceState) {
        FrameLayout mLayout =  new FrameLayout(this);
        mLayout.setId(R.id.ContainerID);
        if (savedInstanceState == null){
            loadRootFragment(R.id.ContainerID, setDefaultFragment());
        }
        setContentView(mLayout);

    }

}

package com.ff.libbasiccore.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.ff.libbasiccore.BaseFragment;

public abstract class FragmentSubPage extends BaseFragment implements View.OnKeyListener {
    private int ICON = 0;
    private CharSequence TITLE = null;

    public FragmentSubPage(int icon, CharSequence title) {
        ICON = icon;
        TITLE = title;
    }

//    @Override
//    public Object setLayout() {
//        return null;
//    }
//
//    @Override
//    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        final View root = getView();
        if (root != null) {
            root.setFocusableInTouchMode(true);
            root.requestFocus();
            root.setOnKeyListener(this);
        }
    }

    private void exitAll() {
        _mActivity.finish();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //check 双击退出
        return false;
    }

    public int getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}

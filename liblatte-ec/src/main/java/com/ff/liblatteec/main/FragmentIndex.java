package com.ff.liblatteec.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.ff.libbasiccore.ui.FragmentSubPage;
import com.ff.liblatteec.R;

public class FragmentIndex extends FragmentSubPage {
    public FragmentIndex(int icon, CharSequence title) {
        super(icon, title);
    }

    @Override
    public Object setLayout() {
        return R.layout.layout_subpage_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

    }
}

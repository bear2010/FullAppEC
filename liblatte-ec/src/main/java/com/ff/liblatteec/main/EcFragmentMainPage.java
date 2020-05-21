package com.ff.liblatteec.main;


import com.ff.libbasiccore.ui.FragmentMainPage;
import com.ff.libbasiccore.ui.FragmentSubPage;
import com.ff.liblatteec.R;

import java.util.LinkedHashMap;

public class EcFragmentMainPage extends FragmentMainPage {
    private final LinkedHashMap<Integer, FragmentSubPage> mItems = new LinkedHashMap<>();

    @Override
    public int setDefaultIndex() {
        return 0;
    }

    @Override
    public LinkedHashMap<Integer, FragmentSubPage> setBottomItems() {
        FragmentSubPage item = new FragmentIndex(R.mipmap.ic_launcher,"主页0");
        mItems.put(0,item);
        item = new FragmentOrder(R.mipmap.ic_launcher,"订单1");
        mItems.put(1,item);
        item = new FragmentIndex(R.mipmap.ic_launcher,"附近0");
        mItems.put(2,item);
        item = new FragmentSearch(R.mipmap.ic_launcher,"发现3");
        mItems.put(3,item);
        item = new FragmentIndex(R.mipmap.ic_launcher,"我的0");
        mItems.put(4,item);
        return mItems;
    }
}

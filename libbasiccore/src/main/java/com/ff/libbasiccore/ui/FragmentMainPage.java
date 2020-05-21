package com.ff.libbasiccore.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ff.libbasiccore.BaseFragment;
import com.ff.libbasiccore.R;

import java.util.LinkedHashMap;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class FragmentMainPage extends BaseFragment implements View.OnClickListener{
    private final LinkedHashMap<Integer, FragmentSubPage> Items = new LinkedHashMap<>();

    private String TAG = FragmentMainPage.class.getName();
    private int mCurrent = 0;
    private int mdefault = 0;
    private int mColor = Color.RED;

    public abstract int setDefaultIndex() ;
    public abstract LinkedHashMap<Integer, FragmentSubPage> setBottomItems();

    private LinearLayout mBottombar = null;
    @Override
    public Object setLayout() {
        return R.layout.layout_mainpage;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {

        Items.putAll(setBottomItems());
        mBottombar = ((LinearLayout) rootview.findViewById(R.id.bottom_bar));
        int size = Items.size();
        SupportFragment[] itemsArray = new SupportFragment[size];
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i=0;i<size;i++){
            RelativeLayout item =(RelativeLayout) (inflater.inflate(R.layout.layoutbottomitem_icon_text, null));
            mBottombar.addView(item);
            item.setTag(i);
            item.setOnClickListener(this);
            Log.d(TAG, "onBindView: set item "+ i);
            ImageView image = ((ImageView) item.getChildAt(0));
            image.setImageDrawable(getResources().getDrawable(Items.get(i).getICON()));
            TextView text = ((TextView) item.getChildAt(1));
            text.setText(Items.get(i).getTITLE());
            if (i == mdefault){
                text.setTextColor(mColor);
            }
            itemsArray[i] = Items.get(i);
        }

        loadMultipleRootFragment(R.id.page_container,mdefault,itemsArray);
//        start(Items.get(mdefault));
    }
//    private void ResetColor()
//    {
//
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdefault = setDefaultIndex();
    }

    @Override
    public void onClick(View v) {
        int tag = ((int) v.getTag());
        Log.d(TAG, "onClick: tap idex=" +tag);
        showHideFragment(Items.get(tag),Items.get(mCurrent));
        mCurrent = tag;
    }
}

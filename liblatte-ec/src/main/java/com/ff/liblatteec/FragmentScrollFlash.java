package com.ff.liblatteec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ff.libbasiccore.BaseFragment;

import java.util.ArrayList;

public class FragmentScrollFlash extends BaseFragment implements OnItemClickListener {
    private ConvenientBanner<Integer> mBnaner = null;
    private String TAG = FragmentScrollFlash.class.getName();
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    private void initBanner() {
        if (localImages.size() ==0) {
            Log.d(TAG, "initBanner: init image");
            localImages.add(R.mipmap.ic_test_0);
            localImages.add(R.mipmap.ic_test_1);
            localImages.add(R.mipmap.ic_test_2);
            localImages.add(R.mipmap.ic_test_3);
            localImages.add(R.mipmap.ic_test_4);
            localImages.add(R.mipmap.ic_test_5);
            localImages.add(R.mipmap.ic_test_6);
        }
        mBnaner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                }, localImages)
                .setPageIndicator(new int[]{R.drawable.dot_white, R.drawable.dot_dark})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .isCanLoop();
        mBnaner.startTurning();
    }

    @Override
    public Object setLayout() {
        mBnaner = new ConvenientBanner<>(getContext());
        return mBnaner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        initBanner();
    }


    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
//        //开始自动翻页
        mBnaner.startTurning();
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
//        //停止翻页
        mBnaner.stopTurning();
    }


    @Override
    public void onItemClick(int position) {
//        Toast.makeText(this," clicked "+position+" img",Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(this, HeaderActivity.class));
        mBnaner.setCanLoop(!mBnaner.isCanLoop());
    }
}

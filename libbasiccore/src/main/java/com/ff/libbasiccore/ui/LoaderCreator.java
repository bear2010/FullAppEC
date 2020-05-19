package com.ff.libbasiccore.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.ff.libbasiccore.R;
import com.ff.libbasiccore.Util;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

public final class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADER_MAP = new WeakHashMap<>();
    private static AppCompatDialog dialog = null ;
    public static void showLoading(Context context) { //context must be activty or fragment. not applcoationcontext
        showLoading(context, LoaderType.BallBeatIndicator.name());
    }

    public static void stopLoading() {
        if (dialog != null){
            dialog.cancel();
        }
    }

    public static void showLoading(Context context, String type) {
        final AVLoadingIndicatorView midc = create(context, type);
        dialog = new AppCompatDialog(context, R.style.dialogEC);
        dialog.setContentView(midc);
        int w = Util.getScreenWidth();
        int h = Util.getScreenHeight();
        final Window dw = dialog.getWindow();
        if (dw != null) {
            WindowManager.LayoutParams lp = dw.getAttributes();
            lp.width = w / 8;
            lp.height = h / 8;
            lp.gravity = Gravity.CENTER;
        }
        dialog.show();
    }

    static AVLoadingIndicatorView create(Context context, String type) {
        AVLoadingIndicatorView mAVLoadingIndicatorView = new AVLoadingIndicatorView(context);
        Indicator midc = LOADER_MAP.get(type);
        if (midc == null) {
            midc = getIndicator(type);
            LOADER_MAP.put(type, midc);
        }
        mAVLoadingIndicatorView.setIndicator(midc);
        return mAVLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        final StringBuffer dname = new StringBuffer();
        if (!name.contains(".")) {
            final String pname = AVLoadingIndicatorView.class.getPackage().getName();
            dname.append(pname)
                    .append(".indicators")
                    .append(".");
        }
        dname.append(name);
        try {
            final Class<?> dclass = Class.forName(dname.toString());
            return (Indicator) dclass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

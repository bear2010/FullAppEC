package com.ff.libbasiccore;

import android.content.Context;
import android.util.Log;

import java.util.WeakHashMap;

public class GBConfig {

    private final static WeakHashMap<String, Object> LATTE_CFG = new WeakHashMap<>();
    private static String TAG = GBConfig.class.getName();

    /*    public static ConfigGlobal getInstance(){
            return CFG_holder.INSTANCE;
        }

        private static class CFG_holder {
            private final static ConfigGlobal INSTANCE = new ConfigGlobal();
        }
    */
    public static void setContext(Context mContext) {
        Log.d(TAG, "setContext: " + mContext);
        LATTE_CFG.put(CFType.APP_CONTEXT.name(), mContext.getApplicationContext());
    }

    public static Context getContext() {
        return (Context) LATTE_CFG.get(CFType.APP_CONTEXT.name());
    }
/*    public final void WithApiHost(String host){
        LATTE_CFG.put(ConfigType.API_HOST.name(),host);
    }*/

//    public final String getApiHost(){
//        return  (String) LATTE_CFG.get(ConfigType.API_HOST.name());
//    }

    final public static <T> T getConfig(Enum<CFType> key) {
        return (T) LATTE_CFG.get(key.name());
    }

    final public static <T> void setConfig(Enum<CFType> key, T value) {
        LATTE_CFG.put(key.name(), value);
    }
}

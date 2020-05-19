package com.ff.libbasiccore.net;

import android.util.Log;

import com.ff.libbasiccore.CFType;
import com.ff.libbasiccore.GBConfig;
//import com.google.gson.Gson;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestClient {

    private INetCallback mCallback = null;
    private String mUrl = null;
    private RequestBody mbody = null;
    private WeakHashMap<String, Object> mParams = new WeakHashMap<>();
    private String TAG = RestClient.class.getName();

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }


    public RestClient(String url, INetCallback callback) {
        Log.d(TAG, "RestClient: constructor aa");
        mUrl = url;
        Log.d(TAG, "RestClient: constructor bb");
        mCallback = callback;
        Log.d(TAG, "RestClient: constructor cc");
        mParams = ParamsHolder.PARAMS;
//        initNet();
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Log.d(TAG, "RestClient: constructor bbb");
    }

    public void params(WeakHashMap<String, Object> pm) {
        mParams.putAll(pm);
    }

    public void params(String key, Object value) {
        mParams.put(key, value);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    private static IRestService mServcie = RestServiceHolder.mRestServcie;

    //    private static IRestService mServcie = null;
    private void request(HttpMethod httpMethod) {
        Log.d(TAG, "request: 00000");
        //todo
        //check the params and url is null
        Call<String> callRetrofit = null;
//        mServcie
        if (mCallback != null) {
            mCallback.OnRequestStart();//for 进度条
        }

        switch (httpMethod) {
            case GET:
                callRetrofit = mServcie.get(mUrl, mParams);
                break;
            case PUT:
                callRetrofit = mServcie.put(mUrl, mParams);
                break;
            case POST:
                callRetrofit = mServcie.post(mUrl, mParams);
                break;
            case DELETE:
                callRetrofit = mServcie.delete(mUrl, mParams);
                break;
            case DOWNLOAD:
//                mServcie.download(mUrl,)
                break;
            case UPLOAD:
//                callRetrofit = mServcie.upload(mUrl,mParams);
                break;
            default:
                break;
        }

        Log.d(TAG, "request: before real get");
        if (callRetrofit != null) {
            callRetrofit.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d(TAG, "onResponse: 111111");
                    if (response.isSuccessful()) {
                        if (call.isExecuted()) {
//                            mCallback.OnSuccess(response.toString());
                            mCallback.OnSuccess(response.body().toString());
                            mCallback.OnRequestEnd();

                        }
                    } else {
                        mCallback.OnError();
                        mCallback.OnRequestEnd();

                    }
                    Log.d(TAG, "onResponse: 222222222");

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    mCallback.OnFailuer();
                    mCallback.OnRequestEnd();

                }
            });
        }
    }

    private final void initNet() {
        Log.d(TAG, "initNet: 1");
        if (OkhttpHolder.mOkHttp != null) {
            Log.d(TAG, "initNet: mOkHttp");
        }
        Log.d(TAG, "initNet: 2");
        if (RetrofitServiceHolder.mRetrofit != null) {
            Log.d(TAG, "initNet: mRetrofit");
        }
        Log.d(TAG, "initNet: 3");
        if (RestServiceHolder.mRestServcie != null) {
            Log.d(TAG, "initNet: mRestServcie");
        }
        Log.d(TAG, "initNet: 4");
        mServcie = RestServiceHolder.mRestServcie;
        Log.d(TAG, "initNet: 5");

    }

    private final static class RestServiceHolder {
        private static final IRestService mRestServcie =
                RetrofitServiceHolder.mRetrofit.create(IRestService.class);
    }

    private final static class RetrofitServiceHolder {
        private static final String url = GBConfig.getConfig(CFType.API_HOST);
        private static final Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(OkhttpHolder.mOkHttp)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
//                .addConverterFactory(GsonCon)
    }

    private final static class OkhttpHolder {
        private static final int TIMEOUT = 60;
        private final static OkHttpClient mOkHttp = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }


}

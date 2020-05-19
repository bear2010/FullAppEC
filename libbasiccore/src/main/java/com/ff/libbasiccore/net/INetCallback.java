package com.ff.libbasiccore.net;

public interface INetCallback {
    void OnSuccess(String ret);
    void OnError();
    void OnFailuer();
    void OnRequestStart();
    void OnRequestEnd();


}

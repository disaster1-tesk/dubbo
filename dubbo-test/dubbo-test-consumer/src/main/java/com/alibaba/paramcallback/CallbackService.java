package com.alibaba.paramcallback;

public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}

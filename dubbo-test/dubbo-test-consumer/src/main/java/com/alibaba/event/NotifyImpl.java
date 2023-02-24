package com.alibaba.event;

import java.util.HashMap;
import java.util.Map;

public class NotifyImpl implements Notify {
    public Map<Integer, String> ret = new HashMap<>();
    public Map<Integer, Throwable> errors = new HashMap<>();

    public void onreturn(String msg, Integer id) {
        System.out.println("onreturn:" + msg);
        ret.put(id, msg);
    }

    public void onthrow(Throwable ex, Integer id) {
        errors.put(id, ex);
    }
}

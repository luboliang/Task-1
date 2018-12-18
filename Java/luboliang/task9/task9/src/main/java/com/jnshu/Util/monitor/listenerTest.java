package com.jnshu.Util.monitor;

import com.jnshu.Util.monitor.CountUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class listenerTest implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        CountUtil.add();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        CountUtil.subtract();

    }
}

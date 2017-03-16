package com.ibps.openapi.util;

import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogTagger extends HandlerInterceptorAdapter implements ServletRequestListener {

    private void init(String requestId) {
        if (MDC.get("requestId") == null && requestId != null) {
            MDC.put("requestId", requestId);
        }
    }

    private void clear() {
        MDC.clear();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        init(request.getHeader("requestId"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        init(request.getHeader("requestId"));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        clear();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        clear();
    }
}

package com.jnshu.Util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        System.out.println(cookies);
        if (cookies==null||cookies.length==0) {
            System.out.println("没有Cookie");
        } else {
            System.out.println("有cookie");
            String name = "";
            String password = "";
            for (Cookie cookie : cookies) {
//                System.out.println("库克的value:"+cookie.getValue());
//                System.out.println("库克的name:"+cookie.getName());
                System.out.println(cookie.getName().equals("name"));
                if (cookie.getName().equals("name")) {
//                    System.out.println("验证成功" + cookie.getValue());
                    name = cookie.getValue();
                    httpServletRequest.getSession().setAttribute("name", name);
                    return true;
                }
            }
        }
        httpServletResponse.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

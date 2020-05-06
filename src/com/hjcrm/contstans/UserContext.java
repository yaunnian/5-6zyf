package com.hjcrm.contstans;

import com.hjcrm.system.bean.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserContext {
    public static final String LOGIN_USER="login_user";
    public static final String LOGIN_NAME="user";
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static User getLoginUser(){
        return (User)getRequest().getSession().getAttribute(UserContext.LOGIN_USER);
    }

    public static void setLoginUser(User user){
        getRequest().getSession().setAttribute(UserContext.LOGIN_USER,user);
        getRequest().getSession().setAttribute(UserContext.LOGIN_NAME,user.getUsername());
    }

    public static void clearLoginUser(){
        getRequest().getSession().removeAttribute(UserContext.LOGIN_USER);
    }
}

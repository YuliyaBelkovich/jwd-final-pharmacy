package com.epam.jwd.command.impl;

import com.epam.jwd.command.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomRequestContext implements RequestContext {

    private final HttpServletRequest request;

    public CustomRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    public List<String> getParamsList() {
        return request.getParameterMap().values().stream().flatMap(Arrays::stream).collect(Collectors.toList());
    }

    public String getParameter(String name){
        return request.getParameter(name);
    }

    public boolean hasParameter(String name){
        if(request.getParameterMap().containsKey(name) && !request.getParameter(name).equals("")){
            return true;
        } else return false;
    }

    public HttpSession getSession(){
        return request.getSession();
    }

    public void setAttribute(String name, Object attr) {
        request.setAttribute(name, attr);
    }

    public String getUrl(){
       return request.getRequestURI()+"?"+request.getQueryString();
    }
}

package com.jeffrey.tomcat.servlet;

import com.jeffrey.tomcat.http.HttpServletRequestP;
import com.jeffrey.tomcat.http.HttpServletResponseP;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 19:09
 * @description:
 * 只保留了三个核心方法声明
 **/
public interface ServletP {
    void init() throws ServletException;

    void service(HttpServletRequestP var1, HttpServletResponseP var2) throws  IOException;

    void destroy();
}

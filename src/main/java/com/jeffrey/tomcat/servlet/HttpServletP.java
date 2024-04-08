package com.jeffrey.tomcat.servlet;

import com.jeffrey.tomcat.http.HttpServletRequestP;
import com.jeffrey.tomcat.http.HttpServletResponseP;

import java.io.IOException;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 19:14
 * @description:
 **/
public abstract class HttpServletP implements ServletP {

    @Override
    public void service(HttpServletRequestP var1, HttpServletResponseP var2) throws IOException {
        if ("GET".equalsIgnoreCase(var1.getMethod())){
            this.doGet(var1,var2);
        }else if ("POST".equalsIgnoreCase(var1.getMethod())){
            this.doPost(var1,var2);
        }
    }
    public abstract void doGet(HttpServletRequestP var1, HttpServletResponseP var2);
    public abstract void doPost(HttpServletRequestP var1, HttpServletResponseP var2);
}

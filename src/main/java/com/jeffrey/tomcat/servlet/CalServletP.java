package com.jeffrey.tomcat.servlet;

import com.jeffrey.tomcat.http.HttpServletRequestP;
import com.jeffrey.tomcat.http.HttpServletResponseP;
import com.jeffrey.tomcat.util.WebUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 19:15
 * @description:
 **/
public class CalServletP extends HttpServletP{
    @Override
    public void init() throws ServletException {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doGet(HttpServletRequestP request, HttpServletResponseP response) {
        int num1 = WebUtil.parseInt(request.getParameter("num1"),0);
        int num2 = WebUtil.parseInt(request.getParameter("num2"),0);

        int sum = num1 + num2;

        //返回计算结果
        OutputStream outputStream = response.getOutputStream();
        String res = HttpServletResponseP.respHeader
                + "<h1>" + num1 + "+" + num2 + "=" + sum + "</h1>";
        try {
            outputStream.write(res.getBytes());
            outputStream.flush();
             //关闭
             outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequestP request, HttpServletResponseP response) {
        doGet(request,response);

    }
}

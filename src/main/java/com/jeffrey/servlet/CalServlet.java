package com.jeffrey.servlet;

import com.jeffrey.util.WebUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class CalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收提交的数据进行运算
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        //把 num1 2转为 int
        int i1 = WebUtil.parseInt(num1, 0);
        int i2= WebUtil.parseInt(num2, 0);
        int res = i1 + i2;

        //输出显示
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        writer.print("<h1>" + num1 + "+" + num2 + "=" + res + "</h1>");
        writer.flush();
        writer.close();

    }
}

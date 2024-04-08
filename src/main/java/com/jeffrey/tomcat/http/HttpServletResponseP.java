package com.jeffrey.tomcat.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 19:16
 * @description:
 * 1、HttpServletResponseP对象封装outputStream
 * 2、即可以通过HttpServletResponseP对象返回http响应给浏览器、客户端
 * 3.等待于原生的servlet的httpServletResponse
 **/
public class HttpServletResponseP {

    private OutputStream outputStream = null;

    //写一个http响应头
    public static final String respHeader = "HTTP/1.1 200\r\n" +
            "Content-Type: text/html;charset=utf-8\r\n\r\n";//响应头和响应体之间有两个换行！！！！

    public HttpServletResponseP(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

//    private void encapHttpResponse(){
//        try {
//            //输出
//            String s = respHeader + "<h1>hi,jeffrey</h1>";
//            System.out.println("======返回的数据是=========");
//            System.out.println(s);
//            outputStream.write(s.getBytes());
//            outputStream.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

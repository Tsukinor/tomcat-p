package com.jeffrey.tomcat.handler;

import com.jeffrey.tomcat.http.HttpServletRequestP;
import com.jeffrey.tomcat.http.HttpServletResponseP;
import com.jeffrey.tomcat.servlet.CalServletP;
import com.jeffrey.tomcat.TomcatV3;

import java.io.*;
import java.net.Socket;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 18:09
 * @description:    HttpRequestHandler的对象是一个线程对象
 *  处理http请求
 **/
public class HttpRequestHandler implements Runnable{
    //定义一个socket
    private Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        InputStream inputStream ;
        try {
             inputStream = socket.getInputStream();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
//        String res = "";
//            System.out.println("当前线程的名称--》" + Thread.currentThread().getName());
//            System.out.println("------ tomcat-v2 接收数据--------");
//        while ((res = bufferedReader.readLine()) != null){
//            //如果长度为0
//            if (res.length() == 0){
//                break;
//            }
//            System.out.println(res);
//        }
            HttpServletRequestP httpServletRequestP = new HttpServletRequestP(inputStream);
//            String num1 = httpServletRequestP.getParameter("num1");
//            String num2 = httpServletRequestP.getParameter("num2");
//            String method = httpServletRequestP.getMethod();
//            String uri = httpServletRequestP.getUri();
//            System.out.println(num1);
//            System.out.println(num2);
//            System.out.println(method);
//            System.out.println(uri);

            //输出显示
            HttpServletResponseP httpServletResponseP = new HttpServletResponseP(socket.getOutputStream());
//            String s = HttpServletResponseP.respHeader + "<h1>hi......fff</h1>";
//            OutputStream outputStream = httpServletResponseP.getOutputStream();
//            outputStream.write(s.getBytes());
//            outputStream.flush();

            //创建一个servlet对象 --》一
//            CalServletP calServletP = new CalServletP();
//            calServletP.doGet(httpServletRequestP,httpServletResponseP);
            String uri = httpServletRequestP.getUri();
            String servletName = TomcatV3.servletUrlMapping.get(uri);
            CalServletP calServletP = TomcatV3.servletMapping.get(servletName);
            calServletP.service(httpServletRequestP,httpServletResponseP);
            //关闭通道
//            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //最后一定要确保socket关闭
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

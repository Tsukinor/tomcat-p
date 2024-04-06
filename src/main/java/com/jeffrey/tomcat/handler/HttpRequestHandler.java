package com.jeffrey.tomcat.handler;

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

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        String res = "";
            System.out.println("当前线程的名称--》" + Thread.currentThread().getName());
            System.out.println("------ tomcat-v2 接收数据--------");
        while ((res = bufferedReader.readLine()) != null){
            //如果长度为0
            if (res.length() == 0){
                break;
            }
            System.out.println(res);
        }

        //输出
        String respHeader = "HTTP/1.1 200\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n\r\n";//响应头和响应体之间有两个换行！！！！
        String s = respHeader + "<h1>hi,jeffrey</h1>";
            System.out.println("======返回的数据是=========");
            System.out.println(s);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            outputStream.flush();

        //关闭通道
            outputStream.close();
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

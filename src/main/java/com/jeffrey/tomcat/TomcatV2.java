package com.jeffrey.tomcat;

import com.jeffrey.tomcat.handler.HttpRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 18:25
 * @description:
 **/
public class TomcatV2 {
    public static void main(String[] args) throws Exception {
        //在8080端口监听
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("tomcat v2 在8080端口监听");
        //只要serverSocket没有关闭，就一直等待浏览器、客户端连接
        while (!serverSocket.isClosed()){
            //1.接收到浏览器连接后，如果成功，就会得到socket
            //2.这个socket就是服务器和浏览器的数据通道
            Socket accept = serverSocket.accept();
            //创建一个线程对象，并且把socket给该线程
            Thread thread = new Thread(new HttpRequestHandler(accept));
            thread.start();
        }

    }
}

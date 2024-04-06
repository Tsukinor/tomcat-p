package com.jeffrey.tomcat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 16:35
 * @description:第一个版本的tomcat，可以完成，接收浏览器的请求，并返回信息
 **/
public class TomcatV1 {
    public static void main(String[] args) throws Exception{
        //1.创建serverSocket在8080端口监听
        ServerSocket serverSocket = new ServerSocket(8080);
        while (!serverSocket.isClosed()){
            //等待浏览器/客户端的连接
            //如果有连接来，就创建一个socket
            //这个socket就是服务端和浏览器的连接通道
            System.out.println("-----服务器在8080端口监听-----");
            Socket socket = serverSocket.accept();

            //先接收浏览器发送的数据
            //inputStream 是字节流==》BufferedReader 字符流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream,"UTF-8"));

            String mes = null;
            System.out.println("========接收到浏览器发送的数据==========");
            //循环读取
            while((mes = bufferedReader.readLine()) != null){
                //判断mes的长度是否为0
                if (mes.length() == 0){
                    break;//退出while
                }
                System.out.println(mes);
            }

            //发送-http响应数据
            OutputStream outputStream = socket.getOutputStream();
            String respHeader = "HTTP/1.1 200\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
            String resp = respHeader + "hi,jeffrey";
            outputStream.write(resp.getBytes());
            outputStream.flush();


            //关闭通道
            outputStream.close();
            inputStream.close();
            socket.close();
        }

    }
}

package com.jeffrey.tomcat;

import com.jeffrey.tomcat.handler.HttpRequestHandler;
import com.jeffrey.tomcat.servlet.CalServletP;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-07 20:40
 * @description:
 * 实现通过xml+反射来初始化容器
 **/
public class TomcatV3 {
    //1.存放容器servletMapping
    //-concurrentHashMap
    //-hashMap
    //key  - value
    //ServletName  存放对应的实例
    public static final ConcurrentHashMap<String, CalServletP>
            servletMapping = new ConcurrentHashMap();

    //2.容器 servletUrlMapping
    //concurrentHashMap
    //存放对应实例类的uri
    public static final ConcurrentHashMap<String, String>
            servletUrlMapping = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        TomcatV3 tomcatV3 = new TomcatV3();
        tomcatV3.init();
        tomcatV3.run();
    }

    //启动v3容器
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("在8080端口监听");
            while (!serverSocket.isClosed()){
                Socket accept = serverSocket.accept();
                HttpRequestHandler httpRequestHandler = new HttpRequestHandler(accept);
                httpRequestHandler.run();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //直接对两个容器初始化
    public void init() {
        //读取web.xml => dom.4j
        //得到web.xml文件路径
        String path = CalServletP.class.getResource("/").getPath();
        //使用dom.4j技术完成读取
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));
//            System.out.println(document);
            //得到根元素
            Element rootElement = document.getRootElement();
            //得到根元素下面的元素
            List<Element> elements = rootElement.elements();
            //遍历并过滤到servlet
            for (Element element : elements) {
                if ("servlet".equalsIgnoreCase(element.getName())) {
                    //这是一个servlet配置
//                    System.out.println("发现servlet");
                    //使用反射将servlet实例放入到servletMapping
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMapping.put(servletName.getText(),
                            (CalServletP)Class.forName(servletClass.getText().trim()).newInstance());
                } else if ("servlet-mapping".equalsIgnoreCase(element.getName())) {
                    //这是一个servlet-mapping
                    System.out.println("发现servlet-mapping");
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUrlMapping.put(urlPattern.getText(),servletName.getText());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //验证这两个容器是否初始化成功
        System.out.println("servletMapping= " + servletMapping);
        System.out.println("servletUrlMapping= " + servletUrlMapping);
    }
}
package com.jeffrey.tomcat.http;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 19:13
 * @description: 封装http请求的数据
 *      get /calservlet?num1=10&num2=30
 *      比如method（get）、uri（/calservlet）、还有参数列表（num1=10&num2=30）
 *      HspRequest作用就等价原生的servlet中的httpServletRequest
 *      目前仅考虑Get请求
 **/
public class HttpServletRequestP {

    private String method;
    private String uri;

    private InputStream inputStream = null;
    //存放参数值
    private HashMap<String,String> parameters =
            new HashMap<>();

    //构造器
    //inputStream 是和 对应http请求的socket关联
    public HttpServletRequestP(InputStream inputStream){
       this.inputStream = inputStream;
       encapHttpRequest();
    }

    //对http请求进行封装
    private void encapHttpRequest(){
        //inputStream --> BufferedReader
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            //读取第一行
            String requestLine = bufferedReader.readLine();
            String[] s = requestLine.split(" ");
            method = s[0];
            //先看看有没有参数列表
            int index = s[1].indexOf("?");
            if (index == -1){//说明没有参数列表
                uri = s[1];
            }else {
                uri = s[1].substring(0,index);
                String parameter = s[1].substring(index+1);
                //num1 = 10,num2 = 30 ... parametersP
                String[] parametersPair = parameter.split("&");
                //防止用户提交时按/servlet? 提交
                if (null != parametersPair && !"".equals(parametersPair)){
                    //再次分割
                    for (String s1 :parametersPair) {
                        String[] split = s1.split("=");
                        if (split.length == 2){
                            //放入parameters
                            parameters.put(split[0],split[1]);
                        }
                    }

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getParameter(String name){
        if (parameters.containsKey(name)){
            return parameters.get(name);
        }else {
            return null;
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

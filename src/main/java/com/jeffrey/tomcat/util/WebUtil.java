package com.jeffrey.tomcat.util;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-07 14:43
 * @description:
 **/
public class WebUtil {
    //将字符转换为字符串
    public static int parseInt(String strNum,int defaultVal){
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println(strNum + "不能转成数字");
        }
        return defaultVal;
    }

}

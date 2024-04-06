package com.jeffrey.util;

/**
 * @program: tomcat-p
 * @author: Jeffrey
 * @create: 2024-04-06 15:34
 * @description:
 **/
public class WebUtil {
    public static int parseInt(String strNum,int defaultVal){

        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println(strNum + "格式不对，转换失败");
        }
        return defaultVal;
    }
}

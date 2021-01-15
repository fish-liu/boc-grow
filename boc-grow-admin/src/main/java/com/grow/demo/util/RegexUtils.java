package com.grow.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * @author liuxw
 * @date 2019/9/18
 * @since 1.0
 */
public class RegexUtils {
    /**
     * Ip 地址匹配规则
     */
    private static String IP_pattern = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";


    /**
     * 解析出str 字符串中的ip地址
     * @param str
     * @return
     */
    public static String getIP(String str){
        Pattern r = Pattern.compile(IP_pattern);
        Matcher m = r.matcher(str);

        if(m.find()){
            return m.group();
        }else {
            return "";
        }
    }


    public static void main(String []args){

        String str = "serviceUrl = http://10.99.32.148:80/luckyscmassetadmin";

        System.out.println(getIP(str));
    }


}

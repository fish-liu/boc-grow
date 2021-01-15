package com.grow.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 获取操作系统
 * @author liuxw
 * @date 2019/3/26
 * @since 1.0
 */
public class SystemUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUtils.class);

    private static String WINDOW = "Windows";


    private static String LINUX = "Linux";

    private static final String OS = System.getProperty("os.name");

    private SystemUtils(){}

    /**
     * 判断是否是windows系统
     * @return
     */
    public static boolean isWin(){

        return OS.indexOf(WINDOW)>=0;
    }

    /**
     * 判断是否是linux系统
     * @return
     */
    public static boolean isLinux(){

        return OS.indexOf(LINUX)>=0;
    }

    /**
     * 获取操作系统名
     * @return
     */
    public static String getOs(){
        return OS;
    }

    /**
     * 获取项目的绝对路径
     * @return
     */
    public static String getProjectAbsolutePath(){
        String absPath;
        try{
            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()){
                path = new File("");
            }
            absPath = path.getAbsolutePath();
        }catch (Exception e){
            LOGGER.error("set AbsolutePath error");
            absPath = "";
        }
        return absPath;
    }


    /**
     * 获取本机ip的InetAddress形式
     *
     * @return
     * @throws Exception
     */
    public static String getSystemLocalIP(){

        String ip = null;
        try {
            if (isWin()) {
                ip = getWinLocalIp();
            } else {
                ip = getUnixLocalIp();
            }
        }catch (Exception e){

        }
        return ip;
    }


    /**
     * 获取window系统的ip，貌似不会返回null
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getWinLocalIp() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        return inet.getHostAddress();
    }

    /**
     * 获取类Unix系统的IP
     *
     * @return
     * @throws Exception
     */
    public static String getUnixLocalIp() throws Exception {

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        if (null == netInterfaces) {
            throw new Exception("获取类Unix系统的IP失败");
        }

        InetAddress inet = null;
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            if (ni.isUp()) {
                Enumeration<InetAddress> addressEnumeration = ni.getInetAddresses();
                while (addressEnumeration.hasMoreElements()) {
                    inet = addressEnumeration.nextElement();
                    if (inet.isSiteLocalAddress() && !inet.isLoopbackAddress() && inet.getHostAddress().indexOf(":") == -1) {
                        return inet.getHostAddress();
                    }
                }
            }
        }
        throw new Exception("获取类Unix系统的IP失败");
    }


}

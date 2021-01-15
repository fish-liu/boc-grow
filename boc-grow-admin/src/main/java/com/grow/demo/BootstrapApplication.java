package com.grow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * 启动类
 * @author liuxw
 */
@SpringBootApplication
public class BootstrapApplication {
    public static void main( String[] args ) {
        //设置启动之后的日志路径
        setLogPath();
        //jar包形式的启动类，是以main为入口的
        SpringApplication.run(BootstrapApplication.class,args);

        // Load Balance策略（支持灵活的FailOver和FailFast HA策略，以及Round Robin、LRU、Consistent Hash等Load Balance策略）
    }

    /**
     * 设置jar 包启动时，log4j的目录
     */
    private static void setLogPath(){
        String loggingPath = System.getProperty("logging.path");
        if(null == loggingPath || "".equals(loggingPath)){
            System.setProperty("logging.path",System.getProperty("user.dir")+ File.separator +"logs");
        }
    }
}




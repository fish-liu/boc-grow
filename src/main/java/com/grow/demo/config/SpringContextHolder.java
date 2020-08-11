package com.grow.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContextHolder工具类,拥有ApplicationContext属性
 * @author liuxw
 * @since 1.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringContextHolder.class);
    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        log.info("---SpringContextHolder----setApplicationContext--");
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        log.info("----getBean-------"+applicationContext.containsBean(name));
        return (T) applicationContext.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clz) {
        return (T) applicationContext.getBean(clz);
    }

    public static boolean isSingleton(String name){
        return applicationContext.isSingleton(name);
    }


}

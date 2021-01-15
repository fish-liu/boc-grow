package com.grow.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @since 1.0
 */
@Configuration
public class SpringMvcWebConfigSupport implements WebMvcConfigurer {

    /**
     * 将static下面的js，css文件加载出来
     * @param registry
     */
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
     }
}

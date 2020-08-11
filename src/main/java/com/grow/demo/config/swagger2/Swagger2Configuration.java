package com.grow.demo.config.swagger2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ModelBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 配置
 * @author liuxw
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties({Swagger2Properties.class})
@ConditionalOnClass({Docket.class, ModelBuilder.class})
@EnableSwagger2
public class Swagger2Configuration {

    Swagger2Properties properties;

    public Swagger2Configuration(Swagger2Properties properties){
        this.properties = properties;
    }

    @Bean
    public Docket buildDocket(){

        // 如果是测试环境，或者开发环境，显示swagger2 的信息
        //if(env.startsWith("test") || env.indexOf("development")>-1 ){

            if(null == this.properties.getBasePackage() || "".equals(this.properties.getBasePackage())){

                return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo()).groupName("API接口文档")
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.fish.demo.controller"))
                        .paths(PathSelectors.any())
                        .build();

            }else {
                return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo()).groupName("API接口文档")
                        .select()
                        .apis(RequestHandlerSelectors.basePackage(this.properties.getBasePackage()))
                        .paths(PathSelectors.any())
                        .build();
            }
        /*}else {
            //非测试和开发环境，不显示swagger2 的信息
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfoOnline()).groupName("fish-framework")
                    .select()
                    .paths(PathSelectors.none())
                    .build();
        }*/

    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title(this.properties.getTitle())
                .description(this.properties.getDescription())
                .license(this.properties.getLicense())
                .licenseUrl(this.properties.getLicenseUrl())
                .termsOfServiceUrl(this.properties.getTermsOfServiceUrl())
                .version(this.properties.getVersion())
                .contact(new Contact(this.properties.getContactName(),
                                     this.properties.getContactUrl(),
                            this.properties.getContactEmail()))
                .build();
    }

    /**
     * 非测试开发环境的页面内容
     * @return
     */
    private ApiInfo apiInfoOnline() {
        return new ApiInfoBuilder()
                .title("")
                .description("")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("")
                .contact(new Contact("","", ""))
                .build();
    }

}

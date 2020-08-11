package com.grow.demo.config.swagger2;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger2 的配置参数
 * @author liuxw
 * @since 1.0
 */
@ConfigurationProperties(prefix = "project.swagger2")
public class Swagger2Properties {
    /**
     * swagger2 扫描的controller包路径
     */
    private String basePackage;
    /**
     * swagger2 文档页面的title
     */
    private String title = "fish-framework swagger2";
    /**
     * swagger2 文档页面的描述
     */
    private String description = "fish-framework swagger2 API";
    /**
     * swagger2 文档页面的 license
     */
    private String license;
    /**
     * swagger2 文档页面的 licenseUrl
     */
    private String licenseUrl;
    /**
     * swagger2 文档页面的 termsOfServiceUrl
     */
    private String termsOfServiceUrl = "";
    /**
     * swagger2 文档页面的联系人
     */
    private String contactName ="admin";
    /**
     * swagger2 文档页面的联系url
     */
    private String contactUrl;
    /**
     * swagger2 文档页面的联系人email
     */
    private String contactEmail ="";
    /**
     * swagger2 文档页面的version
     */
    private String version ="v1.0.0";

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

package com.hy.srb.sms.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties implements InitializingBean {

    /*aliyun:
    sms:
    region-id: cn-hangzhou
    key-id: LTAI5tAswVLFXthxTnQFQr8j
    key-secret: 47OreiFhEjuEZTRfI7foRypTT7sP9v
    template-code: SMS_154950909
    sign-name: 阿里云短信测试*/
    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;

    public static String REGION_Id;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String TEMPLATE_CODE;
    public static String SIGN_NAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_Id = regionId;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        TEMPLATE_CODE = templateCode;
        SIGN_NAME = signName;
    }
}

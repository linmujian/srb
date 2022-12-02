package com.hy.srb.sms.service.impl;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import com.hy.common.exception.Assert;
import com.hy.common.exception.BusinessException;
import com.hy.common.result.ResponseEnum;
import com.hy.srb.sms.service.SmsService;
import com.hy.srb.sms.utils.SmsProperties;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void send(String mobile, Map<String, Object> param) {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(SmsProperties.KEY_ID)
                .accessKeySecret(SmsProperties.KEY_SECRET)
                //.securityToken("<your-token>") // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        Gson gson = new Gson();
        String jsonParam = gson.toJson(param);
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName(SmsProperties.SIGN_NAME)
                .templateCode(SmsProperties.TEMPLATE_CODE)
                .phoneNumbers(mobile)
                .templateParam(jsonParam)
                // 请求级配置重写，可以设置Http请求参数等。
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        try {
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            // Synchronously get the return value of the API request
            SendSmsResponse resp = response.get();
            String message = resp.getBody().getMessage();
            Assert.equals(message,"OK",ResponseEnum.ALIYUN_SMS_LIMIT_CONTROL_ERROR);
            System.out.println(new Gson().toJson(resp));
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new BusinessException(ResponseEnum.ALIYUN_SMS_ERROR , e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new BusinessException(ResponseEnum.ALIYUN_SMS_ERROR , e);
        }
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

        // Finally, close the client
        client.close();
    }
}

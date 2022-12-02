package com.hy.srb.sms.controller.api;

import com.hy.common.exception.Assert;
import com.hy.common.result.ResponseEnum;
import com.hy.common.result.Result;
import com.hy.common.utils.RandomUtils;
import com.hy.common.utils.RegexValidateUtils;
import com.hy.srb.sms.client.CoreUserInfoClient;
import com.hy.srb.sms.service.SmsService;
import com.hy.srb.sms.utils.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
//@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public Result send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        //MOBILE_NULL_ERROR(-202, "手机号不能为空"),
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //MOBILE_ERROR(-203, "手机号不正确"),
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        //判断手机号是否已经被注册,如果调用的远程接口所在的服务器宕机或异常，就会走服务熔断fallback,返回一个false暂时表示手机号没被注册
        boolean result = coreUserInfoClient.checkMobile(mobile);
//        System.out.println(result);
        Assert.isTrue(!result, ResponseEnum.MOBILE_EXIST_ERROR);

        //生成验证码
        String code = RandomUtils.getFourBitRandom();
        //组装短信模板参数
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        //发送短信
//        smsService.send(mobile, param);

        //将验证码存入redis
        redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);

        return Result.ok().setMessage("短信发送成功");
    }
}
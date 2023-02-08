package com.hy.srb.sms.receiver;

import com.hy.srb.base.dto.SmsDTO;
import com.hy.srb.rabbitmq.constant.MQConst;
import com.hy.srb.sms.service.SmsService;
import com.hy.srb.sms.utils.SmsProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.annotation.Resource;
import java.util.HashMap;

public class SmsReceiver {

    @Resource
    private SmsService smsService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MQConst.QUEUE_SMS_ITEM, durable = "true"),
            exchange = @Exchange(value = MQConst.EXCHANGE_TOPIC_SMS),
            key = {MQConst.ROUTING_SMS_ITEM}))
    public void sendMessage(SmsDTO smsDTO){
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", smsDTO.getMessage());
        smsService.send(smsDTO.getPhone(),map);
    }

}

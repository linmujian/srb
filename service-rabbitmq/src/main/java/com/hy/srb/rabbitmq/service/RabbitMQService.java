package com.hy.srb.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitMQService {

    @Resource
    AmqpTemplate amqpTemplate;

    public void send(String exchange,String routingKey,Object message){
        amqpTemplate.convertAndSend(exchange , routingKey , message);
    }

}

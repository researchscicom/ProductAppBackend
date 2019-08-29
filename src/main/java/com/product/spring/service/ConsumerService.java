package com.product.spring.service;


import org.springframework.amqp.core.Message;

public interface ConsumerService {
    Object consumerMessage(Object data) throws Exception;
}

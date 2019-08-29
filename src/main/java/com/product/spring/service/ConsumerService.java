package com.product.spring.service;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;

public interface ConsumerService {
    void consumerMessage(AsyncRabbitTemplate.RabbitMessageFuture data);
}

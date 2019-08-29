package com.product.spring.service;

import com.product.spring.model.Product;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class ProducerServiceImp implements ProducerService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMsg(Product product) {
        amqpTemplate.convertSendAndReceive("product.direct","product.routingkey",product);
    }
}

package com.product.spring.service;

import com.product.spring.dao.ProductDAO;
import com.product.spring.model.Product;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ConsumerServiceImp implements ConsumerService{
    private static final String EXCHANGE_NAME = "customer.direct";
    @Autowired
    public ProductService productService;

    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "customer.queue",durable = "true"),
                    exchange = @Exchange(value = EXCHANGE_NAME),
                    key = "customer.routingkey")
    )
    public Object consumerMessage(Object data) throws Exception{
        System.out.println("=============== Message ==================");
        System.out.println(data);
        System.out.println("==========================================");
        Product product=productService.getProduct(1);
        return product.toString();
    }
}

package com.product.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.spring.model.Product;
import org.springframework.amqp.AmqpIOException;
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
    private ProductService productService;

    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "customer.queue",durable = "true"),
                    exchange = @Exchange(value = EXCHANGE_NAME),
                    key = "customer.routingkey")
    )
    public Object consumerMessage(Long proId) throws AmqpIOException {
        System.out.println("=============== Message ==================");
        System.out.println(proId);
        System.out.println("==========================================");
        Product product=productService.getProduct(proId);
        if(product==null){
            return null;
        }
        else{
            ObjectMapper obj = new ObjectMapper();
            try {
                String pro = obj.writeValueAsString(product);
                return pro;
            }catch(JsonProcessingException e){
                return null;
            }
        }
    }
}

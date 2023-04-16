package com.isep.acme.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.isep.acme.mappers.ProductMapper;
import com.isep.acme.model.Product;
import com.isep.acme.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ProductConsumer {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @RabbitListener(queues = "#{productCreatedQueue}")
    public void receiveCreatedProductMessage(Message message) {
        try {

            String bodyMessage = new String(message.getBody(), "UTF-8");
            log.info("Received message for created product: {}", bodyMessage);

            Product product = productMapper.createProductFromMessage(bodyMessage);
            productService.create(product);

            log.info("Product created successfully with SKU: {}", product.getSku());

        } catch (Exception e) {
            log.error("Error receving message.", e.getMessage());
        }
    }
}

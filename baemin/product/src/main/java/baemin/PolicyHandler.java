package baemin;

import baemin.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelled_IncreaseStock(@Payload OrderCancelled orderCancelled){

        if(!orderCancelled.validate()) return;

        System.out.println("\n\n##### listener IncreaseStock : " + orderCancelled.toJson() + "\n\n");

        Product product = productRepository.findById(orderCancelled.getProductId()).get();
        System.out.println("\n\n================================ INCREASE STOCK ================================\n\n");
        product.setStock(product.getStock()+orderCancelled.getQty());
        productRepository.save(product);
    }

    
    
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
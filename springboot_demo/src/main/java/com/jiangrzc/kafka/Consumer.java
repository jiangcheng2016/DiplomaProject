package com.jiangrzc.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class Consumer {

    private  static Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "test")
    public void recevieMessage(ConsumerRecord<String,String> record){
        logger.info("offset={}, key={}, value={}",record.offset(),record.key(),record.value());
    }
}

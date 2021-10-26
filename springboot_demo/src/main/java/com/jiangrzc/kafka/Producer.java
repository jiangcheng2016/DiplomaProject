package com.jiangrzc.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Transactional
    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMessage(){
        for(int i = 0; i < 10; i ++)
        {
            kafkaTemplate.send("test","消息" + i);
        }
    }
}

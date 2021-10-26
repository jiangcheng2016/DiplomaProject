package com.jiangrzc.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@EnableKafkaStreams
public class StreamConfig {

    @Bean
    public KStream<String,String> kStream(StreamsBuilder streamsBuilder){
        KStream<String,String> stream = streamsBuilder.stream("stream-in");
        //从 stream-in 队列中读取数据，处理后发送给 stream-out 队列
        //发送数据 key, value 分别使用的序列化类为Serdes.String(), JsonSerde
        stream.map(this::uppercaseValue).to("stream-out", Produced.with(Serdes.String(),new JsonSerde<>()));

        return stream;
    }

    /**
     * 消息转换，新的消息， key- 原来的value值， value- 一个map
     */

    private KeyValue<String, Map> uppercaseValue(String key, String value){
        Map<String,String> map = new HashMap<>();
        map.put("message",value.toUpperCase());
        map.put("timestamp",System.currentTimeMillis() + "");
        return new KeyValue(value,map);
    }
}

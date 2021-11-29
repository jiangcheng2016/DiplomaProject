package com.jiangrzc.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlumeKafkaInterceptorDemo implements Interceptor {

    private List<Event> events;

    //初始化
    @Override
    public void initialize() {
        events = new ArrayList<>();
    }

    @Override
    public Event intercept(Event event) {
        // 获取event的header
        Map<String, String> header = event.getHeaders();
        // 获取event的boby
        String body = new String(event.getBody());
        // 根据body中的数据设置header

        System.out.println(body);

        //根据数据格式，fa f3 20 0x , x 代表不同数据
        if(body.charAt(7) == '1'){
            header.put("topic", "topic-for-meteorological");
        }else if(body.charAt(7) == '2'){
            header.put("topic", "topic-for-channel");
        }else if(body.charAt(7) == '3'){
            header.put("topic","topic-for-frequence");
        }else if(body.charAt(7) == '4'){
            header.put("topic","topic-for-channelParam");
        }

//        if (body.contains("mete")) {
//            header.put("topic", "topic-for-meteorological");
//        } else if (body.contains("channel")) {
//            header.put("topic", "topic-for-channel");
//        } else if(body.contains("params")){
//            header.put("topic","topic-for-channelParam");
//        }else if(body.contains("frequence")){
//            header.put("topic","topic-for-frequence");
//        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        // 对每次批数据进来清空events
        events.clear();
        // 循环处理单个event
        for (Event event : events) {
            events.add(intercept(event));
        }

        return events;
    }

    @Override
    public void close() {

    }

    // 静态内部类创建自定义拦截器对象
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new FlumeKafkaInterceptorDemo();
        }

        @Override
        public void configure(Context context) {

        }
    }


}

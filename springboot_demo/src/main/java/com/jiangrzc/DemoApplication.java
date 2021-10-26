package com.jiangrzc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

/**
 * 1.启动类名称，必须叫 XXXApplication
 * 2.启动类必须放在包(package中) 不能直接放在 src/main/java中
 * 3.启动类会自动扫描启动类所在包及子包中注解
 */

@SpringBootApplication
@EnableKafkaStreams
public class DemoApplication {
    public static void main(String[] args) {
        /**
         * 固定写法：
         * 表示哪个类是启动类
         * 第二个参数： spring boot 项目获取jvm参数
         */
        SpringApplication.run(DemoApplication.class,args);
    }

}

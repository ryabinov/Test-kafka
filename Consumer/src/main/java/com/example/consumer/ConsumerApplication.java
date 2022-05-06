package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class ConsumerApplication {

    @KafkaListener(topics="msg")
    public void msgListener(String msg){
        System.out.println(msg);
    }

    @KafkaListener(topics="privet_pacani")
    public void msgListener1(String msg){
        System.out.println(msg);
    }
    @KafkaListener(topics="topic2")
    public void msgListener2(String msg){
        System.out.println(msg);
    }
    @KafkaListener(topics="topic42")
    public void msgListener3(String msg){
        System.out.println(msg);
    }
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}

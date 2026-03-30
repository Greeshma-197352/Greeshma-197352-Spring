package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    // Constructor injection
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * Endpoint to send a Kafka message.
     * Example POST request: /kafka/publish?topic=my-topic&message=HelloKafka
     */
    @PostMapping("/publish")
    public String sendMessage(@RequestParam("topic") String topic,
                              @RequestParam("message") String message) {
        kafkaProducerService.sendMessage(topic, message);
        return "Message sent to Kafka topic: " + topic;
    }
}
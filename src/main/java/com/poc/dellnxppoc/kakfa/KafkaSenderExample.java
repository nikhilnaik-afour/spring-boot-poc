package com.poc.dellnxppoc.kakfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaSenderExample {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaTemplate<String, User> userKafkaTemplate;
    private RoutingKafkaTemplate routingKafkaTemplate;

    @Autowired
    KafkaSenderExample(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> userKafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
        this.userKafkaTemplate = userKafkaTemplate;
    }

    public void sendMessage(String message, String topicName) {
        LOG.info("Sending : {}", message);
        LOG.info("--------------------------------");

        kafkaTemplate.send(topicName, message);
    }

    void sendMessageWithCallback(String message, String topicName) {
        LOG.info("Sending : {}", message);
        LOG.info("---------------------------------");

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOG.warn("Failure Callback: Unable to deliver message [{}]. {}", message, exception.getMessage());
                future.completeExceptionally(exception);
            } else {
                LOG.info("Success Callback: [{}] delivered with offset -{}", message,
                        result.getRecordMetadata().offset());
                future.complete(result);
            }
        });

    }
    void sendCustomMessage(User user, String topicName) {
        LOG.info("Sending Json Serializer : {}", user);
        LOG.info("--------------------------------");

        userKafkaTemplate.send(topicName, user);
    }

    void sendWithRoutingTemplate(String message, String topicName) {
        LOG.info("Sending : {}", message);
        LOG.info("--------------------------------");

        routingKafkaTemplate.send(topicName, message.getBytes());
    }
}

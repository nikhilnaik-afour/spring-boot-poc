package com.poc.dellnxppoc.kakfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenersExample {

    private final Logger LOG = LoggerFactory.getLogger(KafkaListenersExample.class);

    @KafkaListener(topics = "reflectoring-1", groupId = "reflectoring-group-1")
    void listener(String message) {
        LOG.info("Listener : Message Received :  [{}]", message);
    }

    @KafkaListener(topics = { "reflectoring-1", "reflectoring-2" }, groupId = "reflectoring-group-2")
    void commonListenerForMultipleTopics(String message) {
        LOG.info("MultipleTopicListener - [{}]", message);
    }
    @KafkaListener(id = "1", topics = "reflectoring-user", groupId = "reflectoring-user", containerFactory = "userKafkaListenerContainerFactory")
    void listenerWithMessageConverter(User user) {
        LOG.info("MessageConverterUserListener [{}]", user);
    }

    @KafkaListener(topics = "reflectoring-others")
    @SendTo("reflectoring-2")
    String listenAndReply(String message) {
        LOG.info("ListenAndReply [{}]", message);
        return "This is a reply sent to 'reflectoring-2' topic after receiving message at 'reflectoring-others' topic";
    }
    @KafkaListener(topicPartitions = @TopicPartition(topic = "reflectoring-3", partitionOffsets = {
            @PartitionOffset(partition = "0", initialOffset = "0") }), groupId = "reflectoring-group-3")
    void listenToParitionWithOffset(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                    @Header(KafkaHeaders.OFFSET) int offset) {
        LOG.info("ListenToPartitionWithOffset [{}] from partition-{} with offset-{}", message, partition, offset);
    }

    @KafkaListener(topics = "reflectoring-bytes")
    void listenerForRoutingTemplate(String message) {
        LOG.info("RoutingTemplate BytesListener [{}]", message);
    }

}

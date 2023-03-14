package com.solvd.review.kafka.config;

import com.solvd.review.kafka.parser.XmlParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    @Bean
    public ReceiverOptions<String, Long> receiverOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, XmlParser.getValue("bootstrapServers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, XmlParser.getValue("groupId"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, XmlParser.getValue("keyDeserializer"));
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  XmlParser.getValue("valueDeserializer"));
        ReceiverOptions<String, Long> receiverOptions = ReceiverOptions.create(props);
        return receiverOptions
                .subscription(Collections.singleton( XmlParser.getValue("topic")))
                .addAssignListener(receiverPartitions -> log.info("Assigned: " + receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.info("Revoked: " + receiverPartitions));
    }

    @Bean
    public KafkaReceiver<String, Long> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }

}

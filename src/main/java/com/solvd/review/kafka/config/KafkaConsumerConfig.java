package com.solvd.review.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
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

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "group";
    private static final String TOPIC = "movies";

    @Bean
    public ReceiverOptions<String, Long> receiverOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        ReceiverOptions<String, Long> receiverOptions = ReceiverOptions.create(props);
        return receiverOptions
                .subscription(Collections.singleton(TOPIC))
                .addAssignListener(receiverPartitions -> log.info("Assigned: " + receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.info("Revoked: " + receiverPartitions));
    }

    @Bean
    public KafkaReceiver<String, Long> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }

}

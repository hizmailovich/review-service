package com.solvd.review.kafka;

import com.solvd.review.service.ReviewClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final KafkaReceiver<String, Long> kafkaReceiver;
    private final ReviewClient reviewClient;

    @PostConstruct
    private void receive() {
        kafkaReceiver.receive()
                .subscribe(record -> {
                    ReceiverOffset offset = record.receiverOffset();
                    log.info("Received id: " + record.value());
                    reviewClient.deleteByMovieId(record.value());
                    offset.acknowledge();
                });
    }

}

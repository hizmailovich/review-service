package com.solvd.review.kafka;

import com.solvd.review.domain.Event;
import com.solvd.review.service.ReviewService;
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

    private final KafkaReceiver<String, Event> kafkaReceiver;
    private final ReviewService reviewService;

    @PostConstruct
    private void receive() {
        this.kafkaReceiver.receive()
                .subscribe(record -> {
                    ReceiverOffset offset = record.receiverOffset();
                    log.info("Received event: " + record.value());
                    if (record.value().getAction() != null
                            && record.value().getAction() == Event.Action.DELETE_REVIEW) {
                        this.reviewService.deleteByMovieId(
                                record.value().getMovie().getId()
                        );
                    }
                    offset.acknowledge();
                });
    }

}

package com.dish.user.config;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationEventProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
    /**
     * Sends a message to a Kafka topic.
     *
     * @param message The message object to be sent.
     */
    public void sendMessage(Object message) {
        log.info("Call to email_notification using kafka: {}", NotificationEventProducer.class.getName());
        kafkaTemplate.send("email_notification", new JSONObject(message).toString());
    }
}

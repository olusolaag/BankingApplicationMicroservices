package com.learning.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.broker.message.ReplyMessage;

@Service
public class AccountReplyListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountReplyListener.class);
	
    @KafkaListener(topics = "account-reply")
    public void listen(ReplyMessage message) {
        logger.info("Reply message received: {}", message);
    }
}

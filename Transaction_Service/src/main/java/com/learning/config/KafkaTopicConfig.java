package com.learning.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic topicTransaction() {
        return TopicBuilder.name("transaction").partitions(2).replicas(1).build();
    }

    @Bean
    public NewTopic topicAccountReply() {
        return TopicBuilder.name("account-reply").partitions(1).replicas(1).build();
    }
}

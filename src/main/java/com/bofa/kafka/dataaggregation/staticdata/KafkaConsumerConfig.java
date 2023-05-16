package com.bofa.kafka.dataaggregation.staticdata;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.bofa.kafka.dataaggregation.doa.CreditorInfo;
import com.bofa.kafka.dataaggregation.doa.DebitorInfo;

@Configuration
public class KafkaConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value(value = "${spring.kafka.consumer.group-id}")
	private String userGroupId;

	@Value(value = "${kafka.topic.creditor}")
	private String creditorTopic;

	@Value(value = "${kafka.topic.debitor}")
	private String debitorTopic;

	// 2. Consume user objects from Kafka

	public ConsumerFactory<String, CreditorInfo> creditorConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(CreditorInfo.class));
	}

	public ConsumerFactory<String, DebitorInfo> debitorConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(DebitorInfo.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CreditorInfo> creditorKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CreditorInfo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(creditorConsumerFactory());
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DebitorInfo> debitorKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DebitorInfo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(debitorConsumerFactory());
		return factory;
	}

}

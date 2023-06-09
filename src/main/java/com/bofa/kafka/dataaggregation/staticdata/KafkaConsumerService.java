package com.bofa.kafka.dataaggregation.staticdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bofa.kafka.dataaggregation.doa.CreditorInfo;
import com.bofa.kafka.dataaggregation.doa.DebitorInfo;

@Service
public class KafkaConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@KafkaListener(topics = "${kafka.topic.creditor}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "creditorKafkaListenerContainerFactory")
	public void consume(CreditorInfo message) {
		String insertStatemnt = "INSERT INTO CreditorInfo VALUES ('" + message.getAccountID() + "'," + "'"
				+ message.getFirstName() + "'," + "'" + message.getLastName() + "'" + ")";
		logger.info(String.format("CreditorInfo recieved -> %s", insertStatemnt));
		jdbcTemplate.execute(insertStatemnt);
	}

	@KafkaListener(topics = "${kafka.topic.debitor}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "debitorKafkaListenerContainerFactory")
	public void consume(DebitorInfo message) {
		String insertStatemnt = "INSERT INTO DebitorInfo VALUES ('" + message.getAccountID() + "'," + "'"
				+ message.getFirstName() + "'," + "'" + message.getLastName() + "'" + ")";
		logger.info(String.format("DebitorInfo created -> %s", insertStatemnt));
		jdbcTemplate.execute(insertStatemnt);
	}
}

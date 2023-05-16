package com.bofa.kafka.dataaggregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DataaggregationApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DataaggregationApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS CreditorInfo(accountID VARCHAR(10) , firstName VARCHAR(10) ,lastName VARCHAR(10)  )");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS DebitorInfo(accountID VARCHAR(10) , firstName VARCHAR(10) ,lastName VARCHAR(10)  )");

	}

}

package com.bofa.kafka.dataaggregation.streamdata;

import java.util.List;

import org.apache.kafka.streams.kstream.ValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bofa.kafka.dataaggregation.doa.CreditorInfo;
import com.bofa.kafka.dataaggregation.doa.DebitorInfo;
import com.bofa.kafka.dataaggregation.doa.PaymentAggregatedInfo;

@Service
public class AggregatedValueMapper implements ValueMapper<PaymentAggregatedInfo, PaymentAggregatedInfo> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger logger = LoggerFactory.getLogger(AggregatedValueMapper.class);
	
	@Override
	public PaymentAggregatedInfo apply(PaymentAggregatedInfo value) {

		String fromAccount = value.getTransferInfo().getFromAccountID();
		String toAccount = value.getTransferInfo().getToAccountID();
		List<CreditorInfo> creditor = jdbcTemplate.query(
				"SELECT * FROM CreditorInfo where accountID ='" + fromAccount + "'",
				(resultSet, rowNum) -> new CreditorInfo(resultSet.getString("accountID"),
						resultSet.getString("firstName"), resultSet.getString("lastName")));

		List<DebitorInfo> debitor = jdbcTemplate.query(
				"SELECT * FROM CreditorInfo where accountID ='" + fromAccount + "'",
				(resultSet, rowNum) -> new DebitorInfo(resultSet.getString("accountID"),
						resultSet.getString("firstName"), resultSet.getString("lastName")));

		if (creditor.size() > 0)
			value.setCreditorInfo(creditor.get(0));
		if (debitor.size() > 0)
			value.setDebitorInfo(debitor.get(0));
		logger.info(String.format("PaymentAggregatedInfo created -> %s", value));
		
		return value;
	}

}

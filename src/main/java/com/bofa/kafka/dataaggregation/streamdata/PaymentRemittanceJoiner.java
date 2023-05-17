package com.bofa.kafka.dataaggregation.streamdata;

import org.apache.kafka.streams.kstream.ValueJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bofa.kafka.dataaggregation.doa.PaymentAggregatedInfo;
import com.bofa.kafka.dataaggregation.doa.RemittanceInfo;

@Service
public class PaymentRemittanceJoiner
		implements ValueJoiner<PaymentAggregatedInfo, RemittanceInfo, PaymentAggregatedInfo> {
	
	private final Logger logger = LoggerFactory.getLogger(PaymentRemittanceJoiner.class);

	@Override
	public PaymentAggregatedInfo apply(PaymentAggregatedInfo value1, RemittanceInfo remittanceInfo) {
		// TODO Auto-generated method stub
		value1.setRemittanceInfo(remittanceInfo);
		logger.info(String.format("PaymentAggregatedInfo created -> %s", value1));
		return value1;
	}

}

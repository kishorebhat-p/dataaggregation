package com.bofa.kafka.dataaggregation.streamdata;

import org.apache.kafka.streams.kstream.ValueJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bofa.kafka.dataaggregation.doa.PaymentAggregatedInfo;
import com.bofa.kafka.dataaggregation.doa.PaymentInfo;
import com.bofa.kafka.dataaggregation.doa.TransferInfo;

@Service
public class PaymentTransactionJoiner implements ValueJoiner<PaymentInfo, TransferInfo, PaymentAggregatedInfo> {

	private final Logger logger = LoggerFactory.getLogger(PaymentTransactionJoiner.class);

	
	@Override
	public PaymentAggregatedInfo apply(PaymentInfo paymentInfo, TransferInfo transferInfo) {
		PaymentAggregatedInfo aggInfo = new PaymentAggregatedInfo();
		aggInfo.setPaymentInfo(paymentInfo);
		aggInfo.setTransferInfo(transferInfo);
		logger.info(String.format("PaymentAggregatedInfo created -> %s", aggInfo));
		return aggInfo;
	}

}

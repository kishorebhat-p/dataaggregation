package com.bofa.kafka.dataaggregation.serdes;

import org.apache.kafka.common.serialization.Serdes.WrapperSerde;

import com.bofa.kafka.dataaggregation.doa.PaymentAggregatedInfo;

public class PaymentAggregatedInfoSerde extends WrapperSerde<PaymentAggregatedInfo> {

	public PaymentAggregatedInfoSerde() {
		super(new JsonSerializer<>(), new JsonDeserializer<>(PaymentAggregatedInfo.class));
	}

}

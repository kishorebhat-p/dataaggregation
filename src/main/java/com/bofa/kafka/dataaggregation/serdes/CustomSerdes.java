package com.bofa.kafka.dataaggregation.serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.bofa.kafka.dataaggregation.doa.CreditorInfo;
import com.bofa.kafka.dataaggregation.doa.DebitorInfo;
import com.bofa.kafka.dataaggregation.doa.PaymentAggregatedInfo;
import com.bofa.kafka.dataaggregation.doa.PaymentInfo;
import com.bofa.kafka.dataaggregation.doa.RemittanceInfo;
import com.bofa.kafka.dataaggregation.doa.TransferInfo;

public class CustomSerdes {

	public static Serde<CreditorInfo> CreditorSerde() {
		JsonSerializer<CreditorInfo> serializer = new JsonSerializer<>();
		JsonDeserializer<CreditorInfo> deserializer = new JsonDeserializer<>(CreditorInfo.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<DebitorInfo> debitorSerde() {
		JsonSerializer<DebitorInfo> serializer = new JsonSerializer<>();
		JsonDeserializer<DebitorInfo> deserializer = new JsonDeserializer<>(DebitorInfo.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<PaymentAggregatedInfo> paymentAggrSerde() {
		JsonSerializer<PaymentAggregatedInfo> serializer = new JsonSerializer<>();
		JsonDeserializer<PaymentAggregatedInfo> deserializer = new JsonDeserializer<>(PaymentAggregatedInfo.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<PaymentInfo> paymentSerde() {
		JsonSerializer<PaymentInfo> serializer = new JsonSerializer<>();
		JsonDeserializer<PaymentInfo> deserializer = new JsonDeserializer<>(PaymentInfo.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<RemittanceInfo> remittanceSerde() {
		JsonSerializer<RemittanceInfo> serializer = new JsonSerializer<>();
		JsonDeserializer<RemittanceInfo> deserializer = new JsonDeserializer<>(RemittanceInfo.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<TransferInfo> transferSerde() {
		JsonSerializer<TransferInfo> serializer = new JsonSerializer<>();
		JsonDeserializer<TransferInfo> deserializer = new JsonDeserializer<>(TransferInfo.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

}

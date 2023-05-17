package com.bofa.kafka.dataaggregation.doa;


//{"PaymentIdentifer": "13222","EventIdentifier":"2322","EventChannelCode":"12asss","EventType":"P2P"}

public class PaymentInfo {
	
	String paymentIdentifer;

	String eventIdentifier;
	
	String eventChannelCode;
	
	String eventType;

	public String getPaymentIdentifer() {
		return paymentIdentifer;
	}

	public void setPaymentIdentifer(String paymentIdentifer) {
		this.paymentIdentifer = paymentIdentifer;
	}

	public String getEventIdentifier() {
		return eventIdentifier;
	}

	public void setEventIdentifier(String eventIdentifier) {
		this.eventIdentifier = eventIdentifier;
	}

	public String getEventChannelCode() {
		return eventChannelCode;
	}

	public void setEventChannelCode(String eventChannelCode) {
		this.eventChannelCode = eventChannelCode;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "PaymentInfo [paymentIdentifer=" + paymentIdentifer + ", eventIdentifier=" + eventIdentifier
				+ ", eventChannelCode=" + eventChannelCode + ", eventType=" + eventType + "]";
	}
	
	
}

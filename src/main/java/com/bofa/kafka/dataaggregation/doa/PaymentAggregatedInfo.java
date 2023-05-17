package com.bofa.kafka.dataaggregation.doa;

public class PaymentAggregatedInfo {

	private CreditorInfo creditorInfo;

	private DebitorInfo debitorInfo;

	private PaymentInfo paymentInfo;

	private RemittanceInfo remittanceInfo;

	private TransferInfo transferInfo;

	public CreditorInfo getCreditorInfo() {
		return creditorInfo;
	}

	public void setCreditorInfo(CreditorInfo creditorInfo) {
		this.creditorInfo = creditorInfo;
	}

	public DebitorInfo getDebitorInfo() {
		return debitorInfo;
	}

	public void setDebitorInfo(DebitorInfo debitorInfo) {
		this.debitorInfo = debitorInfo;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public RemittanceInfo getRemittanceInfo() {
		return remittanceInfo;
	}

	public void setRemittanceInfo(RemittanceInfo remittanceInfo) {
		this.remittanceInfo = remittanceInfo;
	}

	public TransferInfo getTransferInfo() {
		return transferInfo;
	}

	public void setTransferInfo(TransferInfo transferInfo) {
		this.transferInfo = transferInfo;
	}

	@Override
	public String toString() {
		return "PaymentAggregatedInfo [creditorInfo=" + creditorInfo + ", debitorInfo=" + debitorInfo + ", paymentInfo="
				+ paymentInfo + ", remittanceInfo=" + remittanceInfo + ", transferInfo=" + transferInfo + "]";
	}

}

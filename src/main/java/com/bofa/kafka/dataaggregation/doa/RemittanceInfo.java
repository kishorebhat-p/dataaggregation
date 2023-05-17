package com.bofa.kafka.dataaggregation.doa;

public class RemittanceInfo {

	private String transferId;

	private String remittanceInfo;

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getRemittanceInfo() {
		return remittanceInfo;
	}

	public void setRemittanceInfo(String remittanceInfo) {
		this.remittanceInfo = remittanceInfo;
	}

	@Override
	public String toString() {
		return "RemittanceInfo [transferId=" + transferId + ", remittanceInfo=" + remittanceInfo + "]";
	}
	
	
}

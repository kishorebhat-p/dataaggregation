package com.bofa.kafka.dataaggregation.doa;


//{ "transferId": "12asss" , "fromAccountID": "12322" , "toAccountID": "32422" }
public class TransferInfo {
	
	private String transferId;
	
	private String fromAccountID;
	
	private String toAccountID;

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getFromAccountID() {
		return fromAccountID;
	}

	public void setFromAccountID(String fromAccountID) {
		this.fromAccountID = fromAccountID;
	}

	public String getToAccountID() {
		return toAccountID;
	}

	public void setToAccountID(String toAccountID) {
		this.toAccountID = toAccountID;
	}

	@Override
	public String toString() {
		return "TransferInfo [transferId=" + transferId + ", fromAccountID=" + fromAccountID + ", toAccountID="
				+ toAccountID + "]";
	}
	
	

}

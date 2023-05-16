package com.bofa.kafka.dataaggregation.doa;

public class CreditorInfo {

	private String accountID;

	private String firstName;

	private String lastName;

	public CreditorInfo() {
		accountID = "";
		firstName = "";
		lastName = "";
	}

	public CreditorInfo(String accountID, String firstName, String lastName) {
		this.accountID = accountID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "CreditorInfo [accountID=" + accountID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}

package com.bofa.kafka.dataaggregation.doa;

public class DebitorInfo {

	@Override
	public String toString() {
		return "DebitorInfo [accountID=" + accountID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public DebitorInfo() {
		accountID = "";
		firstName = "";
		lastName = "";
	}

	public DebitorInfo(String accountID, String firstName, String lastName) {
		this.accountID = accountID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private String accountID;

	private String firstName;

	private String lastName;

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
}

package com.rabobank.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Statement implements Serializable {
	
	private static final long serialVersionUID = 328154267495108998L;
	private int transactionReference;
	private String accountNumber;
	private double startBalance;
	private double mutation;
	private String description;
	private double endBalance;
	

	public int getTransactionReference() {
		return transactionReference;
	}

	@XmlAttribute(name="reference")
	public void setTransactionReference(int transactionReference) {
		this.transactionReference = transactionReference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@XmlElement(name="accountNumber")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getStartBalance() {
		return startBalance;
	}

	@XmlElement(name="startBalance")
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	public double getMutation() {
		return mutation;
	}

	@XmlElement(name="mutation")
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name="description")
	public void setDescription(String description) {
		this.description = description;
	}

	public double getEndBalance() {
		return endBalance;
	}

	@XmlElement(name="endBalance")
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {
		return "Statement [transactionReference=" + transactionReference + ", accountNumber=" + accountNumber
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", description=" + description
				+ ", endBalance=" + endBalance + "]";
	}

}

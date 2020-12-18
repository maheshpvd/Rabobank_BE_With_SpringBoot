package com.rabobank.vo;

import java.io.Serializable;

public class Failure implements Serializable {

	private static final long serialVersionUID = 3009035202092734083L;
	private int transactionReference;
	private String description;
	
	public Failure(int transactionReference, String description) {
		super();
		this.transactionReference = transactionReference;
		this.description = description;
	}

	public int getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(int transactionReference) {
		this.transactionReference = transactionReference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

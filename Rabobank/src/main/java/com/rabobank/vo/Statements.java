package com.rabobank.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="records")
public class Statements implements Serializable {
	private static final long serialVersionUID = 8497543272765083222L;
	private List<Statement> statements;

	public List<Statement> getStatements() {
		return statements;
	}

	@XmlElement(required=false, name = "record")
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
}
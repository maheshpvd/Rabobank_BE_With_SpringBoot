package com.rabobank.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rabobank.vo.Failure;
import com.rabobank.vo.Statement;

public class StatementValidation {
	
	private StatementValidation() {
		throw new IllegalStateException("StatementValidation class");
	}

	public static List<Failure> getInvalidRecords(List<Statement> statements) {
		List<Failure> invalidStatements = new ArrayList<>();
		Set<Integer> references = new HashSet<>();
		for (Statement statement : statements) {
			if (!references.add(statement.getTransactionReference()) || !isValidEndBalance(statement)) {
				invalidStatements.add(new Failure(statement.getTransactionReference(), statement.getDescription()));
			}
		}
		return invalidStatements;
	}

	public static boolean isValidEndBalance(Statement statement) {
		return Math.round(statement.getEndBalance() - statement.getStartBalance()) == Math.round(statement.getMutation());
	}

}

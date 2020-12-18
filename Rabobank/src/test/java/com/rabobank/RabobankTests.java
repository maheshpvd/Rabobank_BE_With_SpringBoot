package com.rabobank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabobank.validation.StatementValidation;
import com.rabobank.vo.Failure;
import com.rabobank.vo.Statement;

@SpringBootTest(classes=com.rabobank.RabobankTests.class)
class RabobankTests {

	@Test
	public void getFailedRecordsTestCaseForDuplilcates() {
		Statement s1 = new Statement();
		s1.setTransactionReference(111);
		s1.setAccountNumber("ABC123");
		s1.setStartBalance(26.9);
		s1.setMutation(-18.78);
		s1.setDescription("Test_1");
		s1.setEndBalance(8.12);

		Statement s2 = new Statement();
		s2.setTransactionReference(111);
		s2.setAccountNumber("ABC234");
		s2.setStartBalance(74.69);
		s2.setMutation(-44.91);
		s2.setDescription("Test_11");
		s2.setEndBalance(29.78);
		List<Statement> statements = Arrays.asList(s1, s2);
		List<Failure> failedRecords = StatementValidation.getInvalidRecords(statements);
		assertEquals(1, failedRecords.size());

	}

	@Test
	public void getFailedRecordsTestCaseForInvalidEndBalance() {
		Statement s1 = new Statement();
		s1.setTransactionReference(111);
		s1.setAccountNumber("ABC123");
		s1.setStartBalance(26.9);
		s1.setMutation(-18.78);
		s1.setDescription("Test_2");
		s1.setEndBalance(82.12);

		Statement s2 = new Statement();
		s2.setTransactionReference(112);
		s2.setAccountNumber("ABC234");
		s2.setStartBalance(74.69);
		s2.setMutation(-44.91);
		s2.setDescription("Test_21");
		s2.setEndBalance(291.78);
		List<Statement> statements = Arrays.asList(s1, s2);
		List<Failure> failedRecords = StatementValidation.getInvalidRecords(statements);
		assertEquals(statements.size(), failedRecords.size());

	}

	@Test
	public void getNOFailedRecordsTestCase() {
		Statement s1 = new Statement();
		s1.setTransactionReference(111);
		s1.setAccountNumber("ABC123");
		s1.setStartBalance(26.9);
		s1.setMutation(-18.78);
		s1.setDescription("Test_2");
		s1.setEndBalance(8.12);

		Statement s2 = new Statement();
		s2.setTransactionReference(112);
		s2.setAccountNumber("ABC234");
		s2.setStartBalance(74.69);
		s2.setMutation(-44.91);
		s2.setDescription("Test_21");
		s2.setEndBalance(29.78);
		List<Statement> statements = Arrays.asList(s1, s2);
		List<Failure> failedRecords = StatementValidation.getInvalidRecords(statements);
		assertEquals(0, failedRecords.size());

	}

}

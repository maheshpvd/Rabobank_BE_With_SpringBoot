package com.rabobank.service;

import java.io.File;
import java.util.List;

import com.rabobank.common.RabobankException;
import com.rabobank.vo.Statement;

public interface RabobankService {

	public List<Statement> getStatementRecordsFromCSVFile(File file) throws RabobankException;

	public List<Statement> getStatementRecordsFromXMLFile(File file) throws RabobankException;

}

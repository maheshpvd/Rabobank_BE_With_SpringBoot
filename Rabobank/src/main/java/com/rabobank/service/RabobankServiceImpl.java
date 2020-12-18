package com.rabobank.service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.rabobank.common.Constants;
import com.rabobank.common.RabobankException;
import com.rabobank.vo.Statement;
import com.rabobank.vo.Statements;

@Service
public class RabobankServiceImpl implements RabobankService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabobankServiceImpl.class);

	@Override
	public List<Statement> getStatementRecordsFromCSVFile(File file) throws RabobankException {
		List<Statement> statements = new ArrayList<>();
		LOGGER.debug("Processing CSV file started");
		try (CSVReader reader = new CSVReader(new FileReader(file));){
			CsvToBean<Statement> csvToBean = new CsvToBean<Statement>();
			statements = csvToBean.parse(getHeaderMappingForCSV(), reader);
			if (statements.isEmpty()) {
				throw new RabobankException(Constants.NO_RECORDS_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("Exception: ",e.fillInStackTrace());
			throw new RabobankException(Constants.CSV_FILE_PROCESS_ERROR);
		}
		LOGGER.debug("Processing CSV file completed");
		return statements;
	}

	@Override
	public List<Statement> getStatementRecordsFromXMLFile(File file) throws RabobankException {
		Statements statements = null;
		LOGGER.debug("Processing XML file started");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Statements.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			statements = (Statements) jaxbUnmarshaller.unmarshal(file);
			if (statements == null || statements.getStatements().isEmpty()) {
				throw new RabobankException(Constants.NO_RECORDS_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("Exception: ",e.fillInStackTrace());
			throw new RabobankException(Constants.XML_FILE_PROCESS_ERROR);
		}
		LOGGER.debug("Processing XML file completed");
		return statements.getStatements();
	}

	private HeaderColumnNameTranslateMappingStrategy<Statement> getHeaderMappingForCSV() {
		HeaderColumnNameTranslateMappingStrategy<Statement> headers = new HeaderColumnNameTranslateMappingStrategy<Statement>();
		headers.setType(Statement.class);
		headers.setColumnMapping(Constants.columnMapping);
		return headers;
	}

}

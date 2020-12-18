package com.rabobank.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Constants class");
	}


	public static final List<String> fileTypes = new ArrayList<String>() {
		private static final long serialVersionUID = 4909495617622112492L;
		{
			add("XML");
			add("CSV");
		}
	};
	public static final Map<String, String> columnMapping = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("Reference", "transactionReference");
			put("AccountNumber", "accountNumber");
			put("Description", "description");
			put("Start Balance", "startBalance");
			put("Mutation", "mutation");
			put("End Balance", "endBalance");
		}
	};

	public static final String TYPE_CSV = "CSV";
	public static final String CSV_FILE_PROCESS_ERROR = "Unable to process csv file, Please verify input xml for peoper input.";
	public static final String TYPE_XML = "XML";
	public static final String XML_FILE_PROCESS_ERROR = "Unable to process xml file, Please verify input xml for peoper input.";
	public static final String FILE_PATH = "java.io.tmpdir";
	public static final String INVALID_INPUT_ERROR = "Invalid input.";
	public static final String INVALID_FORMAT_ERROR = "Invalid format, only .csv and .xml files are allowed.";
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";
	public static final String NO_RECORDS_ERROR = "No records to validate.";
	public static final String SUCCESS_MESSAGE = "Statements successfully validated.";

}

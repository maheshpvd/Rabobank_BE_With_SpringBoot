package com.rabobank.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.common.Constants;
import com.rabobank.common.RabobankException;
import com.rabobank.service.RabobankService;
import com.rabobank.validation.StatementValidation;
import com.rabobank.vo.Failure;
import com.rabobank.vo.Statement;

@RestController
@RequestMapping("/rabobank")
public class RabobankController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RabobankController.class);

	@Autowired
	RabobankService rabobankService;

	@CrossOrigin(origins = "*")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> statements(@RequestParam("file") MultipartFile file) {
		try {
			LOGGER.debug("Input file: {}", file);
			if (file == null || file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INVALID_INPUT_ERROR);
			}

			String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toUpperCase();
			LOGGER.debug("File extension: {}", extension);
			if (!Constants.fileTypes.contains(extension)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INVALID_FORMAT_ERROR);
			}
			List<Failure> failedRecords = new ArrayList<>();
			File newFile = new File(System.getProperty(Constants.FILE_PATH) + "/" + file.getOriginalFilename());
			file.transferTo(newFile);
			if (extension.equals(Constants.TYPE_CSV)) {
				List<Statement> statements = rabobankService.getStatementRecordsFromCSVFile(newFile);
				failedRecords = StatementValidation.getInvalidRecords(statements);
			} else if (extension.equals(Constants.TYPE_XML)) {
				List<Statement> statements = rabobankService.getStatementRecordsFromXMLFile(newFile);
				failedRecords = StatementValidation.getInvalidRecords(statements);
			}
			LOGGER.debug("Failed Records: {}", failedRecords.size());
			if (!failedRecords.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failedRecords);
			}
		} catch (IllegalStateException | IOException e) {
			LOGGER.error("Exception: ", e.fillInStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.INTERNAL_SERVER_ERROR);
		} catch (RabobankException e) {
			LOGGER.error("RabobankException: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok().body(Constants.SUCCESS_MESSAGE);
	}

}

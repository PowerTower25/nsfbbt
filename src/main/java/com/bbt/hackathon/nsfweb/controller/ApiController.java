package com.bbt.hackathon.nsfweb.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.bbt.hackathon.nsfweb.data.Transaction;
import com.bbt.hackathon.nsfweb.data.TransactionVolume;

@RestController
public class ApiController {

	@RequestMapping("/branchHeatmap")
	public String branchHeatmap(@RequestParam(value="branchId") int branchId, @RequestParam(value="date") Date date) throws Exception {
		String transactionJson = //new String(Files.readAllBytes(Paths.get("transactions.json")), StandardCharsets.UTF_8);
				"[{\r\n" + 
				"  \"BRANCHID\": \"0201\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/19/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/19/2018 14:32:00\",\r\n" + 
				"  \"AMOUNT\": \"17,182.05\",\r\n" + 
				"  \"DESCRIPTION\": \"9041 19-13-35                 DWP ABMRVLWV\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"ATM SETTLEMENT\"\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"  \"BRANCHID\": \"0201\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/20/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/19/2018 10:32:00\",\r\n" + 
				"  \"AMOUNT\": \"16,474.25\",\r\n" + 
				"  \"DESCRIPTION\": \"\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"CREDIT MEMO\"\r\n" + 
				"},\r\n" +
				"{\r\n" + 
				"  \"BRANCHID\": \"0201\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/20/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/19/2018 10:54:00\",\r\n" + 
				"  \"AMOUNT\": \"16,474.25\",\r\n" + 
				"  \"DESCRIPTION\": \"\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"CREDIT MEMO\"\r\n" + 
				"},\r\n" +
				"{\r\n" + 
				"  \"BRANCHID\": \"0201\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/20/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/19/2018 10:32:00\",\r\n" + 
				"  \"AMOUNT\": \"16,474.25\",\r\n" + 
				"  \"DESCRIPTION\": \"\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"CREDIT MEMO\"\r\n" + 
				"},\r\n" +				
				"{\r\n" + 
				"  \"BRANCHID\": \"0201\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/20/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/20/2018 13:00:00\",\r\n" + 
				"  \"AMOUNT\": \"16,474.25\",\r\n" + 
				"  \"DESCRIPTION\": \"\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"CREDIT MEMO\"\r\n" + 
				"},\r\n" +				
				"{\r\n" + 
				"  \"BRANCHID\": \"0401\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/19/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/19/2018 16:32:00\",\r\n" + 
				"  \"AMOUNT\": \"16,474.25\",\r\n" + 
				"  \"DESCRIPTION\": \"\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"CREDIT MEMO\"\r\n" + 
				"},\r\n" +
				"{\r\n" + 
				"  \"BRANCHID\": \"0401\",\r\n" + 
				"  \"ACCOUNTNUMBER\": \"UUJ111110\",\r\n" + 
				"  \"STATEMENTDATE\": \"07/20/2018\",\r\n" + 
				"  \"POSTDATE\": \"07/19/2018 10:32:00\",\r\n" + 
				"  \"AMOUNT\": \"16,474.25\",\r\n" + 
				"  \"DESCRIPTION\": \"\",\r\n" + 
				"  \"TRANSACTIONTYPE\": \"CREDIT MEMO\"\r\n" + 
				"}]";
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Transaction> transactions = mapper.readValue(transactionJson, new TypeReference<List<Transaction>>(){});
		
		List<TransactionVolume> transactionVolumes = transactions.stream()
			.filter(t -> t.getBranchId() == branchId && DateUtils.isSameDay(t.getPostDate(), date))
			.collect(Collectors.groupingBy(t -> getHours(t.getPostDate()), Collectors.counting()))
			.entrySet().stream()
			.map(e -> new TransactionVolume(e.getKey(), e.getValue()))
			.collect(Collectors.toList());
		
		return mapper.writeValueAsString(transactionVolumes);
	}
	
	private int getHours(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
}

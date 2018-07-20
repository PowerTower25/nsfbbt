package com.bbt.hackathon.nsfweb.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bbt.hackathon.nsfweb.data.TransactionJsonContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
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

	@Autowired
	private TransactionJsonContainer transactionJsonContainer;

    @CrossOrigin
	@RequestMapping("/branchHeatmap")
	public String branchHeatmap(@RequestParam(value="branchId") int branchId, @RequestParam(value="date") Date date) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Transaction> transactions = mapper.readValue(transactionJsonContainer.getTransactionJson(), new TypeReference<List<Transaction>>(){});
		
		List<TransactionVolume> transactionVolumes = transactions.stream()
			.filter(t -> t.getBranchId() == branchId && isSameDayOfWeek(t.getPostDate(), date))
			.collect(Collectors.groupingBy(t -> getHours(t.getPostDate()), Collectors.counting()))
			.entrySet().stream()
			.map(e -> new TransactionVolume(e.getKey(), e.getValue()))
			.collect(Collectors.toList());
		
		return mapper.writeValueAsString(transactionVolumes);
	}
	
	private int getHours(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR);
		if (hour == 0)
			hour = 12;
		return hour;
	}
	
	private boolean isSameDayOfWeek(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal.get(Calendar.DAY_OF_WEEK) == cal2.get(Calendar.DAY_OF_WEEK);
	}
}

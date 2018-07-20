package com.bbt.hackathon.nsfweb.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SizeupApi {
	private static String apiKey = "ADD382DE-6641-4C5A-A26C-5085E7F5D6DB";
	private static String sessionId = "";
	private static String apiToken = "";
	private static String instanceId = "";
	
	public static String getRevenuePercentile(int industryId, int geographicLocationId, int revenue) throws Exception {
		setApiParams();
		String url = String.format("https://a2-api.sizeup.com/data/averageRevenue/percentile/?geographicLocationId=%d&industryId=%d&value=%d&cb=sizeup.api.cbb.cb4&o=localhost&s=%s&t=%s&i=%s",
				geographicLocationId, industryId, revenue, sessionId, apiToken, instanceId);

        URL urlObj = new URL(url);
        URLConnection urlc = urlObj.openConnection();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

		String line;
		StringBuilder builder = new StringBuilder(2048);

		while ((line = bfr.readLine()) != null) {
		    builder.append(line);
		}

		String result = builder.toString();
		return result.substring(result.indexOf('(') + 1, result.lastIndexOf(')'));
	}
	
	public static String getEmployeePercentile(int industryId, int geographicLocationId, int employees) throws Exception {
		setApiParams();
		String url = String.format("https://a2-api.sizeup.com/data/AverageEmployees/percentile/?geographicLocationId=%d&industryId=%d&value=%d&cb=sizeup.api.cbb.cb3&o=localhost&s=%s&t=%s&i=%s",
				geographicLocationId, industryId, employees, sessionId, apiToken, instanceId);

        URL urlObj = new URL(url);
        URLConnection urlc = urlObj.openConnection();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

		String line;
		StringBuilder builder = new StringBuilder(2048);

		while ((line = bfr.readLine()) != null) {
		    builder.append(line);
		}

		String result = builder.toString();
		return result.substring(result.indexOf('(') + 1, result.lastIndexOf(')'));
	}
	
	public static void setApiParams() throws Exception {
		if (sessionId.length() > 0)
			return;
		
		String url = String.format("https://api.sizeup.com/js/?callback=onLoadSizeup&apikey=%s",
				apiKey);

        URL urlObj = new URL(url);
        URLConnection urlc = urlObj.openConnection();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

		String line;
		StringBuilder builder = new StringBuilder(2048);

		while ((line = bfr.readLine()) != null) {
		    builder.append(line);
		}
		
		String response = builder.toString();
		
		String param = "me.sessionId = '";
		int startIndex = response.indexOf(param) + param.length();
		int endIndex = response.indexOf('\'', startIndex + 1);
		sessionId = URLEncoder.encode(response.substring(startIndex, endIndex), "UTF-8");
		
		param = "me.apiToken = '";
		startIndex = response.indexOf(param) + param.length();
		endIndex = response.indexOf('\'', startIndex + 1);
		apiToken = URLEncoder.encode(response.substring(startIndex, endIndex), "UTF-8");
		
		param = "me.instanceId = '";
		startIndex = response.indexOf(param) + param.length();
		endIndex = response.indexOf('\'', startIndex + 1);
		instanceId = URLEncoder.encode(response.substring(startIndex, endIndex), "UTF-8");
	}
}

package com.bbt.hackathon.nsfweb.data;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInsights {
    
    private static Map<String, UserInsights> collection = new HashMap<>();
    static {
        // Rose petal -- Flower Shop Owner
    	int retailFloristIndustryId = 8990;
    	int raleighNcGeographicLocationId = 98563;
    	int employees = 5;
    	int revenue = 3200000;
    	
    	boolean overstaffed = false;
    	boolean understaffed = false;
    	
    	try {
    		ObjectMapper mapper = new ObjectMapper();
	    	String employeePercentileJson = SizeupApi.getEmployeePercentile(retailFloristIndustryId, raleighNcGeographicLocationId, employees);
	    	String revenuePercentileJson = SizeupApi.getRevenuePercentile(retailFloristIndustryId, raleighNcGeographicLocationId, revenue);
	    	
	    	SizeupResult employeePercentile = mapper.readValue(employeePercentileJson, SizeupResult.class);
	    	SizeupResult revenuePercentile = mapper.readValue(revenuePercentileJson, SizeupResult.class);
	    	
	    	if (employeePercentile.getPercentile() / revenuePercentile.getPercentile() > 1.2)
	    		overstaffed = true;
	    	if (revenuePercentile.getPercentile() / employeePercentile.getPercentile() > 1.2)
	    		understaffed = true;
    	}
    	catch (Exception ex) {}

        UserInsights user = new UserInsights("rose@petalflowers.biz");
        List<String> opportunities = new ArrayList<>();
        opportunities.add("BB&T Bright Visa with Overdraft Protection");
        opportunities.add("BB&T Fundamentals Checking");
        if (overstaffed)
        	opportunities.add("Sizeup Insight - Client may be overstaffed");
        if (understaffed)
        	opportunities.add("Sizeup Insight - Client may be understaffed");
        user.setOpportunities(opportunities);
        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert("BV 200 Checking", "Avg Balance Below $1,500", "Convert to BV50 Checking – Best Fit"));
        alerts.add(new Alert("Branch Wire", "$65 fee on 6/25", "Cheaper with SBO - $15"));
        
        user.setAlerts(alerts);
        user.setName("Rose Petal");
        user.setCompany("Petal Flowers");
        register(user);
    }
     
    private String email;
    private String name;
    private String company;
    private List<Alert> alerts;
    private List<String> opportunities;
    
    public UserInsights(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }

    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Alert> getAlerts() {
        return alerts;
    }
    
    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;    
    }
    
    public List<String> getOpportunities() {
        return opportunities;
    }
    
    public void setOpportunities(List<String> opportunities) {
        this.opportunities = opportunities;
    }

    
    public static void register(UserInsights user) {
        collection.put(user.getEmail(), user);
    }
    public static UserInsights getInsight(String email) {
        UserInsights u = collection.get(email);
        if (u == null) {
            //return an emoty one
            u = new UserInsights(email);
        }
        return u;
    }
    
}

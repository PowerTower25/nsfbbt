package com.bbt.hackathon.nsfweb.data;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class UserInsights {
    
    private static Map<String, UserInsights> collection = new HashMap<>();
    static {
        // Rose Pedal -- Flower Shop Owner
        UserInsights user = new UserInsights("rose@pedalflowers.biz");
        List<String> opportunities = new ArrayList<>();
        opportunities.add("Sell insurance or something");
        user.setOpportunities(opportunities);
        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert("Business Value 200", "Average daily balance under $1500", "Convert to Business Value 50, because reason"));
        user.setAlerts(alerts);
        user.setName("Rose Pedal");
        user.setCompany("Pedal Flowers");
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

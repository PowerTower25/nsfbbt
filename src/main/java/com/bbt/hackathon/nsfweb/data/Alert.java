package com.bbt.hackathon.nsfweb.data;

public class Alert {
    private String product;
    private String problem;
    private String suggestion;
    
    public Alert(String product, String problem, String suggestion) {
        this.product = product;
        this.problem = problem;
        this.suggestion = suggestion;
    }
    
    public void setProduct(String product) {
        this.product = product;
    }
    
    public String getProduct() {
        return product;
    }
    
    public void setProblem(String problem) {
        this.problem = problem;
    }
    
    public String getProblem() {
        return problem;
    }

    
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    public String getSuggestion() {
        return suggestion;
    }
}


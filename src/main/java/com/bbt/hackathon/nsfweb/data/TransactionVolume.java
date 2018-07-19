package com.bbt.hackathon.nsfweb.data;

public class TransactionVolume {
	
	private int hourOfDay;
    private long transactionCount;
    
    public TransactionVolume(int hourOfDay, long transactionCount) {
        this.hourOfDay = hourOfDay;
        this.transactionCount = transactionCount;        
    }
    
    public int getHourOfDay() {        
        return hourOfDay;
    }
    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }
    
    public long getTransactionCount() {
        return transactionCount;
    }
    public void setTransactionCount(long transactionCount) {
        this.transactionCount = transactionCount;
    }
}

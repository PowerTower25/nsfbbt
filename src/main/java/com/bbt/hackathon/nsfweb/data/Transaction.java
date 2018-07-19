package com.bbt.hackathon.nsfweb.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaction {
	
	private int branchId;
	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private Date postDate;
	
	public Transaction() {
    }
	
	public int getBranchId() {        
        return branchId;
    }
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    
    public Date getPostDate() {        
        return postDate;
    }
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}

package com.bbt.hackathon.nsfweb.data;

public class SizeupResult {
	private String name;
	private float percentile;
	
	public SizeupResult() { }
	public SizeupResult(String name, float percentile) {
		this.name = name;
		this.percentile = percentile;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPercentile() {
		return percentile;
	}
	public void setPercentile(float percentile) {
		this.percentile = percentile;
	}
}

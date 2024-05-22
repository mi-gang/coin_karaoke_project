package com.oopsw.controller;

public class Url {
	public static final int FORWARD = 0;
	public static final int REDIRECT = 1;
	private String url;
	private int flag;
	
	
	public Url(String url){
		this(url, REDIRECT);
	}
	
	public Url(String url, int flag){
		this.setUrl(url);
		this.setFlag(flag);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	

}

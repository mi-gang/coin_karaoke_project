package com.oopsw.controller;

public class Url {
	public static final int FORWARD = 0;
	public static final int redirect = 1;
	String url;
	int flag;
	
	
	public Url(String url){
		this(url, FORWARD);
	}
	
	public Url(String url, int flag){
		this.url = url;
		this.flag = flag;
	}

}

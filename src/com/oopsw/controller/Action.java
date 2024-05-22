package com.oopsw.controller;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	public Url execute(HttpServletRequest request);
}

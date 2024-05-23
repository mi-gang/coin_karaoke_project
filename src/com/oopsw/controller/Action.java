package com.oopsw.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	public Url execute(HttpServletRequest request);
}

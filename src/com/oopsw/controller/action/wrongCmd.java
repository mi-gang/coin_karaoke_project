package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class wrongCmd implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("/jsp/wrongCmd.jsp");
		return url;
	}
	
}

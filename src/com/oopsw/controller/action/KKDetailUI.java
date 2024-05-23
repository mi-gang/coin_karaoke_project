package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class KKDetailUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		return new Url("jsp/kkDetailUI.jsp", Url.FORWARD);
	}

}

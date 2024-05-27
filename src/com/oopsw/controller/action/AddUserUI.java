package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class AddUserUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		return new Url("jsp/addUserUI.jsp", Url.FORWARD);
	}

}

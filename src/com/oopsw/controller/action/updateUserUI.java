package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class updateUserUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		request.getSession().setAttribute("userId", "test@test.com");
		return new Url("jsp/updateUserUI.jsp", Url.FORWARD);
	}

}

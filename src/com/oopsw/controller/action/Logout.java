package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class Logout implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session != null) {
			session.invalidate();
		}
		return new Url("controller?cmd=mainUI", Url.FORWARD);
	}

}

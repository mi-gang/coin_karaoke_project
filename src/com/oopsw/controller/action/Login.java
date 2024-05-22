package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class Login implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("controller?cmd=url", Url.REDIRECT);
		return url;
	}

}

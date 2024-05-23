package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class updateUserUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		// FIXME: updateUserUI jsp로 바꾸기
		return new Url("html/updateUserUI.html", Url.FORWARD);
	}

}

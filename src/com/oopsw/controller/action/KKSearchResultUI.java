package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class KKSearchResultUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		return new Url("jsp/kkSearchResultListUI.jsp", Url.FORWARD);
	}

}

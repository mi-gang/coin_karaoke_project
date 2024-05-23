package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class LoginUI implements Action{

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("html/loginUI.html", Url.FORWARD);
		HttpSession session =request.getSession();
		if(session.getAttribute("userId") != null){
			url.setUrl("controller?cmd=mainUI");
			url.setFlag(Url.REDIRECT);
		}
		return url;
	}
	
	
}

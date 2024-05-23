package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class LoginUI implements Action{

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("jsp/loginUI.jsp", Url.FORWARD);
		HttpSession session =request.getSession();
		
		if(session.getAttribute("userId") != null){
			System.out.println("LoginUI:이미 로그인된 상태: " + session.getAttribute("userId"));
			url.setUrl("controller?cmd=mainUI");
			url.setFlag(Url.REDIRECT);
		}
		return url;
	}
	
	
}

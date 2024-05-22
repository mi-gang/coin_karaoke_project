package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class Login implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("controller?cmd=loginUI", Url.FORWARD);
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		boolean isValidLogin= new UserService().login(userId, password);
		
		if(isValidLogin){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			url.setUrl("controller?cmd=mainUI");
			url.setFlag(Url.REDIRECT);
		}
		
		return url;
	}

}

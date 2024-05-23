package com.oopsw.controller.action;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.UserVO;
import com.oopsw.service.UserService;

public class Login implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("controller?cmd=loginUI", Url.REDIRECT);
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		boolean loginSuccess = false;
		loginSuccess = new UserService().login(userId, password);
		
		if(loginSuccess){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			System.out.println("Login.java: 로그인 성공: " + userId);
			url.setUrl("controller?cmd=mainUI");
			url.setFlag(Url.REDIRECT);
		}
		
		return url;
	}

}

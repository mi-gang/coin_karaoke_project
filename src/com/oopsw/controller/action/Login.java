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
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		boolean loginSuccess = false;
		loginSuccess = new UserService().login(userId, password);
		
		if(loginSuccess){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			System.out.println("로그인 성공: " + userId);
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();
		json.addProperty("result", loginSuccess);
		request.setAttribute("dataToSend", gson.toJson(json));
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

}
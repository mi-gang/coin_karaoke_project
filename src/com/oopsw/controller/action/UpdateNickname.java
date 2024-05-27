package com.oopsw.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class UpdateNickname implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String newNickname = request.getParameter("nickname");
		String userId = (String) session.getAttribute("userId");
		
		JsonObject json = new JsonObject();
		json.addProperty("result", new UserService().updateNickname(userId, newNickname));
		request.setAttribute("dataToSend", json.toString());
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

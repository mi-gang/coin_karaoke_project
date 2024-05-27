package com.oopsw.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class IsAdult implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		boolean result = false;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		try {
			result = new UserService().isAdult(userId);
		} catch (SQLException e) {
			// userId가 없을 때
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		request.setAttribute("dataToSend", json.toString());
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

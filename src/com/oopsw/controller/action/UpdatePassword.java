package com.oopsw.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class UpdatePassword implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserService service = new UserService();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String userId = (String) session.getAttribute("userId");
		boolean result = false;
		
		result = service.updatePassword(userId, oldPassword, newPassword);
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		request.setAttribute("dataToSend", json.toString());
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

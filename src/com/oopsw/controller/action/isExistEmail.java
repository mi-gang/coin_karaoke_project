package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class isExistEmail implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		
		JsonObject json = new JsonObject();
		json.addProperty("result", new UserService().isExistEmail(userId));
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp");
	}

}

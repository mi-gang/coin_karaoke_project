package com.oopsw.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class GetNickname implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String nickname = null;
		
		try {
			nickname = new UserService().getNickname(userId);
		} catch (SQLException e) {
			//해당 userId 존재하지 않는 경우
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("nickname", nickname);
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

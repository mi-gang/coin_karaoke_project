package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class DeleteBookmarkAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		int kkId = Integer.parseInt(request.getParameter("kkId"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		boolean deleteMyBookmark = new UserService().deleteKKBookmark(userId, kkId);
		JsonObject json = new JsonObject();
		json.addProperty("result", deleteMyBookmark);
		request.setAttribute("dataToSend", gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

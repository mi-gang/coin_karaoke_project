package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class AddBookmarkAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();
		
		boolean isLogin = session.getAttribute("userId") != null;
		
		if(isLogin) {
			String userId = session.getAttribute("userId").toString();
			System.out.println(">> userId: ");
			System.out.println(userId);
			int kkId = Integer.parseInt(request.getParameter("kkId"));
			boolean addMyBookmark = new UserService().addKKBookmark(userId, kkId);
			json.addProperty("result", addMyBookmark);
		} else {
			json.addProperty("result", false);
		}
		
		request.setAttribute("dataToSend",  gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
		
		
		/*System.out.println("@@$(*#(*! AddBookmarkAction ");
		System.out.println(session.getAttribute("userId".toString()));
		String userId = null;
		if(session.getAttribute("userId").toString() != null) {
			userId = session.getAttribute("userId").toString();
		} else if(session.getAttribute("userId").toString() == null){
			userId = "";
		}
		System.out.println("userId");
		System.out.println(userId);
		int kkId = Integer.parseInt(request.getParameter("kkId"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		boolean addMyBookmark = new UserService().addKKBookmark(userId, kkId);
		JsonObject json = new JsonObject();
		json.addProperty("result", addMyBookmark);
		request.setAttribute("dataToSend", gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);*/
	}

}

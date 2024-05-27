package com.oopsw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.service.UserService;

public class CheckKKBookmarkAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		System.out.println("CheckKKBookmark Action 파일 --------");
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("userId") != null;
		
		if(isLogin) {
			String userId = session.getAttribute("userId").toString();
			System.out.println(">> userId: ");
			System.out.println(userId);
			int kkId = Integer.parseInt(request.getParameter("kkId"));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			boolean checkMyBookmark = new UserService().isKKBookmark(userId, kkId);
			System.out.println(">> chkKKBookmarkAction의 boolean");
			System.out.println(checkMyBookmark);
			JsonObject json = new JsonObject();
	        json.addProperty("result", checkMyBookmark);
	        json.addProperty("isLogin", isLogin);
	        request.setAttribute("dataToSend",  gson.toJson(json));
		} else {
			System.out.println("로그인 X 상태!");
			int kkId = Integer.parseInt(request.getParameter("kkId"));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject json = new JsonObject();
	        json.addProperty("isLogin", isLogin);
	        request.setAttribute("dataToSend",  gson.toJson(json));
		}
		
		return new Url("json/data.jsp", Url.FORWARD);
		
		/*String userId = session.getAttribute("userId").toString();
		System.out.println(">> userId: ");
		System.out.println(userId);
		int kkId = Integer.parseInt(request.getParameter("kkId"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		boolean checkMyBookmark = new UserService().isKKBookmark(userId, kkId);
		System.out.println(">> chkKKBookmarkAction의 boolean");
		System.out.println(checkMyBookmark);
		JsonObject json = new JsonObject();
        json.addProperty("result", checkMyBookmark);
        json.addProperty("isLogin", isLogin);
        // request.setAttribute("dataToSend", json.toString());
        request.setAttribute("dataToSend",  gson.toJson(json));
		
//		request.setAttribute("checkMyBookmark", checkMyBookmark);
//		request.setAttribute("dataToSend", checkMyBookmark);
		return new Url("json/data.jsp", Url.FORWARD);*/
	}

}

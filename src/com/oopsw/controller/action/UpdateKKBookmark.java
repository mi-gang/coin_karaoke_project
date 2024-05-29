package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class UpdateKKBookmark implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();
		
		boolean isLogin = session.getAttribute("userId") != null;
		
		if(isLogin) {
			String userId = session.getAttribute("userId").toString();
			int kkId = Integer.parseInt(request.getParameter("kkId"));
			boolean chkMyBookmark = new UserService().isKKBookmark(userId, kkId);
			json.addProperty("isLogin", true);
			json.addProperty("chkMyBookmark", chkMyBookmark);
			if(chkMyBookmark == true) {
				// 북마크 제거하기
				boolean deleteMyBookmark = new UserService().deleteKKBookmark(userId, kkId);

				json.addProperty("result", deleteMyBookmark);
			} else {
				// 북마크 추가하기
				boolean addMyBookmark = new UserService().addKKBookmark(userId, kkId);

				json.addProperty("result", addMyBookmark);
			}
		} else {
			json.addProperty("isLogin", false);
			json.addProperty("result", false);
		}
		
		request.setAttribute("dataToSend",  gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

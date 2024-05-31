package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.MusicService;

public class PlaylistTitle implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		//String userId="test@test.com";
		String userId = (String) request.getSession().getAttribute("userId");
		int playlistId=Integer.parseInt(request.getParameter("playlistId"));
		JsonObject json = new JsonObject();
		//boolean result= true;
		json.addProperty("result", new MusicService().getPlaylistTitle(userId, playlistId));
		request.setAttribute("dataToSend",  gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
	}
}

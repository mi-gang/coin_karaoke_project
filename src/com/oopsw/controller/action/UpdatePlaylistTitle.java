package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.MusicService;

public class UpdatePlaylistTitle implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String playlistTitle=request.getParameter("newTitle") ;
			//String userId ="test@test.com";
			String userId = (String) request.getSession().getAttribute("userId");
			int playlistId=Integer.parseInt(request.getParameter("playlistId")) ;
			JsonObject json = new JsonObject();
			json.addProperty("result", new MusicService().updatePlaylistTitle(playlistTitle, userId, playlistId));
			request.setAttribute("dataToSend",  gson.toJson(json));
			return new Url("json/data.jsp", Url.FORWARD);
	}

}

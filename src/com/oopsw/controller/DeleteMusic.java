package com.oopsw.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.service.MusicService;

public class DeleteMusic implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			int songId=Integer.parseInt(request.getParameter("songId")) ;
			String brand=request.getParameter("brand");
			int playlistId=Integer.parseInt(request.getParameter("playlistId")) ;
			//boolean result=new MusicService().deleteSongInPlaylist(songId, brand, playlistId);
			JsonObject json = new JsonObject();
			json.addProperty("result", new MusicService().deleteSongInPlaylist(songId, brand, playlistId));
			request.setAttribute("dataToSend",  gson.toJson(json));
			return new Url("json/data.jsp", Url.FORWARD);
	}

}

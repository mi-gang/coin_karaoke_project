package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.MusicService;



public class AddMusic implements Action {

	public Url execute(HttpServletRequest request) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		int songId=Integer.parseInt(request.getParameter("songId")) ;
		String brand=request.getParameter("brand");
		String title=request.getParameter("title");
		String singer=request.getParameter("singer");
		System.out.println(singer);
		int playlistId=Integer.parseInt(request.getParameter("playlistId")) ;
		//boolean result=new MusicService().addMusic(songId, brand, title, singer, playlistId);
		//System.out.println(result);
		JsonObject json = new JsonObject();
		json.addProperty("result", new MusicService().addMusic(songId, brand, title, singer, playlistId));
		request.setAttribute("dataToSend",  gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
	}

}
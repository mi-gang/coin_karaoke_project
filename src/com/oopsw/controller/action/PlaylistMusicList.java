package com.oopsw.controller.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.SongVO;
import com.oopsw.service.MusicService;

public class PlaylistMusicList implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			HttpSession session = request.getSession();
			int playlistId=Integer.parseInt(request.getParameter("playlistId"));
			String brand=request.getParameter("entInput");
			Collection<SongVO> list = new MusicService().playlistMusicList(playlistId, brand);
			request.setAttribute("dataToSend", gson.toJson(list));
			System.out.println(list);
			return new Url("json/data.jsp", Url.FORWARD);
		}
	}
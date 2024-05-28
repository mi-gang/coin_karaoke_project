package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.MusicService;

public class PlaylistMusicNumber implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int playlistId=Integer.parseInt(request.getParameter("playListId"));
		int tjNum=new MusicService().getPlaylistSongCount(playlistId, "TJ");
		int KYNum=new MusicService().getPlaylistSongCount(playlistId, "TJ");
		int total=tjNum+KYNum;
		request.setAttribute("totalNum", total);
		Url url=new Url("jsp/PlaylistMusicNumber.jsp", Url.FORWARD);
		return url;
		
	}

}

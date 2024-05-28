package com.oopsw.controller.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.service.MusicService;
import com.oopsw.service.MusicServiceVO;

public class MyPlaylist implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		Object userId = session.getAttribute("userId");
		String userId="test@test.com";
//		Url url= new Url("html/loginUI.html", Url.FORWARD);
		Collection<PlaylistVO> list=new MusicService().getPlaylistList(userId); //userId.toString()濡� 援먯껜�삁�젙
//		if(session.getAttribute("userId") != null){
			request.setAttribute("playLists", list);
			System.out.println(list);
		 Url url=new Url("jsp/myplaylist.jsp", Url.FORWARD);
//		}
		return url;

	}
}
//Collection<PlaylistVO> getPlaylistList(String userId)
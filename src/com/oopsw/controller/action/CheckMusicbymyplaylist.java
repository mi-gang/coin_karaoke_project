package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.MusicService;
import com.oopsw.service.MusicServiceVO;



public class CheckMusicbymyplaylist implements Action {

	//비동기임
	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");
		String brand=request.getParameter("brand");
		int songId=Integer.parseInt(request.getParameter("songId")) ;
		String page="login";
		Collection<MusicServiceVO> list=new MusicService().myPlaylistCheckMusic(userId.toString(), songId, brand);
		if(list.size()!=0){
			request.setAttribute("playLists", list);
		}
		return null;

		//		}
		//
		//			if(new MusicService().MyPlaylistCheckMusic(userId, songId, brand) != null){
		//				page="ajax JSP 작성 예정";			
		//
		//		return new Url("controller?cmd="+page,Url.REDIRECT);
	}
}

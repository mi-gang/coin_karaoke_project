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
//일단 폴더에 내 음악이 담겼는지 표시하지 않고, 그냥 일단 내 플레이리스트를 출력하는 Action
	//비동기임
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		Object userId = session.getAttribute("userId");
		//String userId="test@test.com";
		String userId = (String) request.getSession().getAttribute("userId");
		//boolean isLogin = session.getAttribute("userId") != null;
		//String userId = (String)session.getAttribute("userId");  // 로그인 상태가 아닐때는 null일것. ->.toString()은 나중에
		//System.out.println(userId);
		String brand=request.getParameter("brand");
		int songId=Integer.parseInt(request.getParameter("songId")) ;
//		Url url= new Url("html/loginUI.html", Url.FORWARD);
		Collection<MusicServiceVO> list=new MusicService().myPlaylistCheckMusic(userId, songId, brand); //userId.toString()로 교체예정
//		if(session.getAttribute("userId") != null){
		//System.out.println("list"+list);
			request.setAttribute("playLists", list);
			System.out.println(list);
		 Url url=new Url("jsp/checkMusicbymyplaylist.jsp", Url.FORWARD);
//		}
		return url;

		//		}
		//
		//			if(new MusicService().MyPlaylistCheckMusic(userId, songId, brand) != null){
		//				page="ajax JSP 작성 예정";			
		//
		//		return new Url("controller?cmd="+page,Url.REDIRECT);
	}
}

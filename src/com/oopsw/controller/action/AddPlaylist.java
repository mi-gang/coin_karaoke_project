package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.MusicService;

public class AddPlaylist implements Action {
//userId 연동해야함.session으로 받아서 집어넣음
	@Override
	public Url execute(HttpServletRequest request) {
		String userId="test@test.com";
		String newTitle=request.getParameter("newTitle");
		boolean result=new MusicService().addPlaylist(newTitle, userId);
		request.setAttribute("addReslut", result);
		// TODO Auto-generated method stub
		Url url=new Url("jsp/addPlaylist.jsp", Url.FORWARD);
		return url;
	}

}

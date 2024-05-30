package com.oopsw.controller.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.service.MusicService;

public class DeletePlaylist implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//String userId="test@test.com";
		String userId = (String) request.getSession().getAttribute("userId");
		int playlistId=Integer.parseInt(request.getParameter("playlistId"));
		boolean result=new MusicService().deletePlaylist(userId, playlistId);
			request.setAttribute("result", result);
		 Url url=new Url("jsp/deletePlaylist.jsp", Url.FORWARD);
//		}
		return url;

	}
}
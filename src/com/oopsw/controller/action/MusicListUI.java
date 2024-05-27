package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class MusicListUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Url url= new Url("jsp/musicSearchUI.jsp",Url.FORWARD);
		return url;
	}

}

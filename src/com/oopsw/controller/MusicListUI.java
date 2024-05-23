package com.oopsw.controller;

import javax.servlet.http.HttpServletRequest;

public class MusicListUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Url url= new Url("jsp/musicSearchUI.jsp",Url.FORWARD);
		return url;
	}

}

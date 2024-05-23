package com.oopsw.controller;

import javax.servlet.http.HttpServletRequest;



public class AddMusic implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		int songId=Integer.parseInt(request.getParameter("musicId")) ;
		String brand=request.getParameter("brand");
		String title=request.getParameter("title");
		String singer=request.getParameter("singer");
		int playlistId=Integer.parseInt(request.getParameter("playlistId")) ;
		return null;
	}

}
//String id=request.getParameter("id");
//String pw=request.getParameter("pw");
//String name=request.getParameter("name");
////new AddCustomerService().addCustomer(id,pw,name);
////회원가입 여부에 따라 페이지 이동
//String page="addCustomerUI"; //사용자는 그자리에 있는줄알지만 F12로 확인시 요청하였다는걸 확인할수 있다.
//if(new AddCustomerService().addCustomer(id, pw, name)){
//	page="loginUI";
//}
//return new URLModel("controller?cmd="+page,true);
//}

//int songId, String brand,String title, String singer,int playlistId
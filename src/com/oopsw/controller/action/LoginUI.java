package com.oopsw.controller.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class LoginUI implements Action{

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("jsp/loginUI.jsp", Url.FORWARD);
		HttpSession session =request.getSession();
		// *
		// 로그인 유도 페이지로부터 로그인 UI에 접속하여 로그인 성공 -> 다시 로그인 유도 페이지로 돌아가야함.
		// (예: 노래방 상세페이지-리뷰 _ '로그인하고 리얼 리뷰 보기' 버튼 클릭으로 로그인 -> 노래방 상세페이지로 돌아가야함)
		// String prevURL = request.getParameter("prevURL");
		/*
		System.out.println("@@ ~~ LoginUI Action 파일");
		System.out.println("@@ ~~ prevURL: ");
		// System.out.println(prevURL);
		String extraURL = request.getParameter("clickedKKId");
		System.out.println(extraURL);
		String encodedPrevURL = request.getParameter("prevURL");
		System.out.println(encodedPrevURL);
		String prevURL = null;
		try {
			prevURL = URLDecoder.decode(encodedPrevURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// prevURL = encodedPrevURL;
		System.out.println("prevURL: ");
		System.out.println(prevURL);
		request.setAttribute("prevURL", prevURL);
		*/
		
		
		/*Object prevURL1 = session.getAttribute("prevURL");
		System.out.println("prevURL1 :: ");
		System.out.println(prevURL1);
		String prevURLDecode = (String)prevURL1;
		System.out.println(prevURLDecode);
		Object prevURL2 = request.getSession().getAttribute("prevURL");
		System.out.println("prevURL2 :: ");
		System.out.println(prevURL2);*/
		/*String prevURL = request.getParameter("prevURL");
		System.out.println("prevURL :: ");
		System.out.println(prevURL);*/
		
		String encodedPrevURL = request.getParameter("prevURL");
		System.out.println("@@ ~~ LoginUI Action 파일");
		System.out.println("@@ ~~ encodedPrevURL :: ");
		System.out.println(encodedPrevURL);
		String prevURL = null;
		try {
			prevURL = URLDecoder.decode(encodedPrevURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// prevURL = encodedPrevURL;
		System.out.println("@@ ~~ prevURL :: ");
		System.out.println(prevURL);
		request.setAttribute("prevURL", prevURL);
		
		
		if(session.getAttribute("userId") != null){
			System.out.println("LoginUI:이미 로그인된 상태: " + session.getAttribute("userId"));
			url.setUrl("controller?cmd=mainUI");
			url.setFlag(Url.REDIRECT);
		} /*else {
			System.out.println("@!@#!@@! LoginUI.Action의 url 출력");
			url.setUrl("controller"+prevURL);
			System.out.println(url.getUrl());
			url.setFlag(Url.REDIRECT);
		}*/
		return url;
	}
	
	
}

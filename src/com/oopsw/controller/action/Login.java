package com.oopsw.controller.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class Login implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		// Url url = new Url("controller?cmd=loginUI", Url.REDIRECT);
		// Url url = null;
		Url url = new Url("controller", Url.REDIRECT);
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		boolean loginSuccess = false;
		loginSuccess = new UserService().login(userId, password);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();
		// *
		// 로그인 유도 페이지로부터 로그인 UI에 접속하여 로그인 성공 -> 다시 로그인 유도 페이지로 돌아가야함.
		// (예: 노래방 상세페이지-리뷰 _ '로그인하고 리얼 리뷰 보기' 버튼 클릭으로 로그인 -> 노래방 상세페이지로 돌아가야함)
		String prevURL = "?cmd=mainUI";		// 임시 처리
		System.out.println("!! ~~ 로그인 Action 파일");
		System.out.println("!! ~~ prevURL: ");
		System.out.println(prevURL);
		String dPrevURL = "?cmd=mainUI";
		try {
			dPrevURL = prevURL = URLDecoder.decode(prevURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("!! ~~ dPrevURL: ");
		System.out.println(dPrevURL);
		
		
		if(loginSuccess){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			System.out.println("O 로그인 성공: " + userId + " : LoginAction");
			
			// *
			if(prevURL != null) {
				System.out.println("!= null 여기??????????");
				String tmp = url.getUrl() + dPrevURL;
				url.setUrl(tmp);
				// url.setUrl("controller"+dPrevURL);
				// url.setFlag(Url.REDIRECT);
			} else if (prevURL == "") {
				System.out.println(" == '' : 여기??????????");
				url.setUrl("controller?cmd=mainUI");
				url.setFlag(Url.FORWARD);
				return url;
			} else {
				System.out.println("여기??????????");
				url.setUrl("controller?cmd=mainUI");
				return url;
				// url.setFlag(Url.REDIRECT);
			}
			/*url.setUrl("controller?cmd=mainUI");
			url.setFlag(Url.REDIRECT);*/
			// return url;
		} else{
			System.out.println("X 로그인 실패: " + userId + " : LoginAction");
			if(dPrevURL != null) {
				System.out.println("!= null 여기??????????");
				System.out.println("==> dPrevURL: ");
				System.out.println(dPrevURL);
				String tmp = url.getUrl() + "?cmd=loginUI&prevURL="+dPrevURL;
				url.setUrl(tmp);
				// url.setUrl("controller?cmd=loginUI&prevURL="+dPrevURL);
				url.setFlag(Url.FORWARD);
			} else if(dPrevURL == "") {
				System.out.println(" == '' 여기>?????");
				url.setUrl("controller?cmd=loginUI");
				url.setFlag(Url.FORWARD);
				return url;
			} else if (dPrevURL == null){
				System.out.println("여기??????????");
				url.setUrl("controller?cmd=loginUI");
				url.setFlag(Url.FORWARD);
				return url;
			}
			
			// return url;
		}
		
		json.addProperty("loginSuccess", loginSuccess);
		json.addProperty("prevURL", dPrevURL);
		
		// return url;
		request.setAttribute("dataToSend", gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

package com.oopsw.controller.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.UserVO;
import com.oopsw.service.UserService;

public class Login implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
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
		String prevURL = request.getParameter("prevURL");
		String dPrevURL = null;
		try {
			dPrevURL = prevURL = URLDecoder.decode(prevURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(loginSuccess){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			System.out.println("O 로그인 성공: " + userId + " : LoginAction");
			
			if(prevURL != null) {
				String tmp = url.getUrl() + dPrevURL;
				url.setUrl(tmp);
			} else {
				url.setUrl("controller?cmd=mainUI");
			}
		} else{
			System.out.println("X 로그인 실패: " + userId + " : LoginAction");
			if(dPrevURL != null) {
				String tmp = url.getUrl() + "?cmd=loginUI&prevURL="+dPrevURL;
				url.setUrl(tmp);
				url.setFlag(Url.FORWARD);
			} else if (dPrevURL == null){
				url.setUrl("controller?cmd=loginUI");
				url.setFlag(Url.FORWARD);
			}
		}
		
		json.addProperty("loginSuccess", loginSuccess);
		json.addProperty("prevURL", dPrevURL);

		request.setAttribute("dataToSend", gson.toJson(json));
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

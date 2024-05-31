package com.oopsw.controller.action;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class SendValidationNumber implements Action {
	private final int MAX_VNUMBER = 9999;//인증번호 범위: 1 ~ MAX_VNUMBER
	private final int EXPIRED_MINUTE = 5; //인증번호 유효시간: 5분 

	@Override
	public Url execute(HttpServletRequest request) {
		UserService service = new UserService();
		String userId = request.getParameter("userId");
		boolean isExistEmail =service.isExistEmail(userId);
		if(isExistEmail){
			HttpSession session = request.getSession();
			int vNumber = (int)(1 + Math.random() * MAX_VNUMBER);
			session.setAttribute("vNumber", Integer.toString(vNumber));
			session.setAttribute("vExpiredDate", LocalDateTime.now().plusMinutes(EXPIRED_MINUTE));
			System.out.println("인증번호 " + vNumber + "의 유효기간: "+ session.getAttribute("vExpiredDate") + " (사용자는 " + userId + ")");
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("result", isExistEmail);
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp");
	}

}

package com.oopsw.controller.action;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class ResetPassword implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		boolean result = false;
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String actualVNumber = request.getParameter("vNumber");
		
		
		if(isValidVnumber(request, actualVNumber)){
			result = new UserService().resetPassword(userId, password);
		}
		
		if(result){
			System.out.println("O 비밀번호를 reset하였습니다! : resetPassword.java");
		}else{
			System.out.println("X 비밀번호를 reset에 실패하였습니다.: resetPassword.java");
		}
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		request.setAttribute("dataToSend", json.toString());
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

	private boolean isValidVnumber(HttpServletRequest request, String actualVNumber) {
		boolean result = false;
		HttpSession session = request.getSession();
		try{
			
			String expectedVNumber = (String) session.getAttribute("vNumber");
			LocalDateTime vExpiredDate = (LocalDateTime)session.getAttribute("vExpiredDate");
			if(actualVNumber.equals(expectedVNumber) && LocalDateTime.now().isBefore(vExpiredDate)){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}

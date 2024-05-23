package com.oopsw.controller.action;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.UserService;

public class resetPassword implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		boolean result = false;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String password = request.getParameter("password");
		String actualVNumber = request.getParameter("vNumber");
		
		
		if(isValidVnumber(request, actualVNumber)){
			result = new UserService().resetPassword(userId, password);
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

package com.oopsw.controller.action;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.UserVO;
import com.oopsw.service.UserService;

public class AddUser implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String nickname = request.getParameter("nickname");
		LocalDateTime birthDate = string2LDT(request.getParameter("birthDate"));
		String password = request.getParameter("password");
		
		UserVO user = new UserVO(userId, nickname, birthDate, password);
		boolean successAdd = false;
		try {
			successAdd = new UserService().addUser(user);
		} catch (SQLException e) {
			//successAdd = false;
		}
	
		if(successAdd){
			System.out.println("O 회원가입에 성공하였습니다. : addUserAction");
		}else{
			System.out.println("X 회원가입에 실패하였습니다. : addUserAction");
		}
		JsonObject json = new JsonObject();
		json.addProperty("result", successAdd);
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp", Url.FORWARD);
	}
	
	private LocalDateTime string2LDT(String str){
		String [] date = str.split("-");
		
		int year, month, dayOfMonth;
		year       = Integer.parseInt(date[0]);
		month      = Integer.parseInt(date[1]);
		dayOfMonth = Integer.parseInt(date[2]);
		
		return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
		
	}

}

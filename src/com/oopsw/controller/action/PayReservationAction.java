package com.oopsw.controller.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.service.ReservationService;

public class PayReservationAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String userId = (String)request.getSession().getAttribute("userId");
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(request.getParameter("startTime"));
		LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"), formatter);
		LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime")  , formatter);
		
		boolean result = false;
		try{
			result = new ReservationService().addReservation(new ReservationVO(-1,-1,startTime,endTime,userId,-1,"",roomId,""));
		}catch(Exception e){
			
		}
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

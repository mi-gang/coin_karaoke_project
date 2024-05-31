package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.ReservationService;

public class AddableTime implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		
		int addableMinutes = new ReservationService().getAddableMinutes(userId, reservationId);
		JsonObject json = new JsonObject();
		json.addProperty("minute", addableMinutes);
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.ReservationService;

public class CancelReservationAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		boolean result = false;
		String userId = (String) request.getSession().getAttribute("userId");
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		result = new ReservationService().cancelReservation(userId, reservationId);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		request.setAttribute("dataToSend", json.toString());
		return new Url("json/data.jsp");
	}

}

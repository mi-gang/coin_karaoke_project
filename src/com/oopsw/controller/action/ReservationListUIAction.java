package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;

public class ReservationListUIAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("/WebContent/html/reservation.html");

		return url;
	}

}
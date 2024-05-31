package com.oopsw.controller.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.service.ReservationService;

public class UncompletedReservationListAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		ReservationService service = new ReservationService();

		Collection<ReservationVO> reservationVOs = new ArrayList<>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");

		String page = "controller?cmd=login";

		if (userId != null) {
			System.out.println(userId);
			reservationVOs = service.getUncompletedReservationList((String) userId);
			// request.setAttribute("reservationVOs", reservationVOs);
	        request.setAttribute("dataToSend", gson.toJson(reservationVOs));
			page = "json/data.jsp";
		}

		return new Url(page, Url.FORWARD);
	}

}

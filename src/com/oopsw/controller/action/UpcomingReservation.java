package com.oopsw.controller.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.service.ReservationService;

public class UpcomingReservation implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		
		Collection<ReservationVO> reservationVOs = new ArrayList<>();
		ReservationService service = new ReservationService();

		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");

		String page = "controller?cmd=login";

		if (userId != null) {
			reservationVOs = service.getUpcomingReservation((String) userId);
			request.setAttribute("reservationVOs", reservationVOs);
			page = "jsp/UpcomingReservation.jsp";
		}

		return new Url(page, Url.FORWARD);
	}

}

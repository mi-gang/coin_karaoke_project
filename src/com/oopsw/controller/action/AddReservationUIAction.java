package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.service.ReservationService;

public class AddReservationUIAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		
		boolean result = false;
		ReservationService service = new ReservationService();
		ReservationVO vo = (ReservationVO) request.getAttribute("reservationVO");

		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");

		String page = "controller?cmd=login";

		if (userId != null) {
			result = service.addReservation(vo);
			request.setAttribute("result", result);
			page = "jsp/UpcomingReservation.jsp";	// 여기 바꿔야 함
		}

		return new Url(page, Url.FORWARD);
	}

}

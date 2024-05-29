package com.oopsw.controller.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.ReservationService;
import com.oopsw.service.completedReservationVO;

public class CompletedReservationListAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		ReservationService service = new ReservationService();

		Collection<completedReservationVO> completedReservationVOs = new ArrayList<>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");

		String page = "controller?cmd=login";

		if (userId != null) {
			System.out.println(userId);
			completedReservationVOs = service.getCompletedReservationList((String) userId);
			request.setAttribute("dataToSend", gson.toJson(completedReservationVOs));
			page = "json/data.jsp";
		}

		return new Url(page, Url.FORWARD);
	}

}

package com.oopsw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.service.ReservationService;
import com.oopsw.service.myPageVO;

public class MyPageAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");

		ReservationService service = new ReservationService();

		String page = "controller?cmd=login";

		if (userId != null) {
			System.out.println(userId);
			myPageVO myPageVO = service.myPageInfo((String) userId);
			request.setAttribute("dataToSend", gson.toJson(myPageVO));
			page = "json/data.jsp";
		}

		return new Url(page, Url.FORWARD);
	}

}

package com.oopsw.controller.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.ReservationRoomInfoVO;
import com.oopsw.service.ReservationService;

public class RoomReservationStatusListAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		int kkId = Integer.parseInt(request.getParameter("kkId"));
		ReservationService service = new ReservationService();
		Collection<ReservationRoomInfoVO> infoVOs = new ArrayList<>();

		infoVOs = service.getroomReservationStatusList(kkId);
		request.setAttribute("infoVOs", gson.toJson(infoVOs));

		return new Url("json/data.jsp", Url.FORWARD);
	}

}

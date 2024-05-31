package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.KKVO;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.service.KKService;
import com.oopsw.service.ReservationService;

public class MainUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		KKService kkService = new KKService();
		List<KKVO> kks = null;
		try {
			kks = kkService.getNearRecommendKKList("금천구");
			if (kks.size() > 10) {
				kks = kks.subList(0, 11);
			}
			
			String userId = (String) request.getSession().getAttribute("userId");
			ReservationVO upcomingReservation = new ReservationService().getUpcomingReservation(userId);
			
			request.setAttribute("upR", upcomingReservation);
			request.setAttribute("recommendKKList", kks);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Url("jsp/mainUI.jsp", Url.FORWARD);
	}

}

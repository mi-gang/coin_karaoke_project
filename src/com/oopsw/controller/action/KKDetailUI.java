package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.KKVO;
import com.oopsw.service.KKService;

public class KKDetailUI implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("jsp/kkDetailUI.jsp", Url.FORWARD);
		System.out.println("------ KKDetailUI Action -----");
		String requestKkId = request.getParameter("clickedKKId");
		System.out.println(requestKkId);
		
		try {
			KKVO vo = new KKService().getSelectedKKBasicInfo(requestKkId);
			float starRating = new KKService().getStarAvgByKK(Integer.parseInt(requestKkId));
			request.setAttribute("starRating", starRating);
			request.setAttribute("KKVO", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
		// return new Url("jsp/kkDetailUI.jsp", Url.FORWARD);
	}

}

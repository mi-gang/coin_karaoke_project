package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReviewVO;
import com.oopsw.service.ReviewService;

public class AddReviewAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String page = "controller?cmd=reservationListUI";

		String content = (String) request.getParameter("content");
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		float star = Float.parseFloat(request.getParameter("star"));
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		ReviewVO reviewVO = new ReviewVO(content, star, reservationId);
		ReviewService service = new ReviewService();
		boolean result = service.addReview(reviewVO);

		request.setAttribute("dataToSend", gson.toJson(result));

		return new Url("json/data.jsp", Url.FORWARD);
	}

}

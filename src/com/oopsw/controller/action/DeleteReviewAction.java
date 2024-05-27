package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.ReviewService;

public class DeleteReviewAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		ReviewService service = new ReviewService();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		boolean result = service.deleteReview(reviewId);

		request.setAttribute("dataToSend", gson.toJson(result));

		return new Url("json/data.jsp", Url.FORWARD);
	}

}

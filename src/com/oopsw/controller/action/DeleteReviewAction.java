package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.ReviewService;

public class DeleteReviewAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		ReviewService service = new ReviewService();
		String page = null;
		
		boolean result = service.deleteReview(reviewId);
		if (result) {
			page = "json/data.jsp";			
		}
		
		return new Url(page, Url.FORWARD);
	}

}

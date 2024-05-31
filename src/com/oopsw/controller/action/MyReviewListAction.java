package com.oopsw.controller.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReviewVO;
import com.oopsw.service.ReviewService;

public class MyReviewListAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		ReviewService service = new ReviewService();
		
		Collection<ReviewVO> reviewVOs = new ArrayList<ReviewVO>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("userId");
		
		reviewVOs = service.getReviewListByUserId((String) userId);
        request.setAttribute("dataToSend", gson.toJson(reviewVOs));
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

}
//
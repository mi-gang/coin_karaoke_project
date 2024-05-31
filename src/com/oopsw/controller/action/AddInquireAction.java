package com.oopsw.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.InquireVO;
import com.oopsw.service.InquireService;

public class AddInquireAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {

		String page = "controller?cmd=reservationListUI";

		String content = (String) request.getParameter("content");
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		InquireVO inquireVO = new InquireVO(content, reservationId);
		InquireService service = new InquireService();
		boolean result = service.addInquire(inquireVO);

		request.setAttribute("dataToSend", gson.toJson(result));

		return new Url("json/data.jsp", Url.FORWARD);

	}

}

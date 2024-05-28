package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.KKVO;
import com.oopsw.service.KKService;

public class NearRecommendKKList implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String addressGu = request.getParameter("myLocation");
		List<KKVO> kks = null;
		try {
			kks = new KKService().getNearRecommendKKList(addressGu);
		} catch (SQLException e) {
			
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        request.setAttribute("dataToSend", gson.toJson(kks));
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

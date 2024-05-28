package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.service.KKService;

public class AmountStarReviewListByType implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		String kkId = request.getParameter("kkId");
		System.out.println("~~ AmountStar... Action의 kkId");
		System.out.println(kkId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		try {
			ArrayList<String> result = new KKService().getAmountStarReviewByType(kkId);
			for(int i=0; i<result.size(); i++) {
				switch(i) {
				case 0:
					json.addProperty("cnt1", result.get(i));
					break;
				case 1:
					json.addProperty("cnt2", result.get(i));
					break;
				case 2:
					json.addProperty("cnt3", result.get(i));
					break;
				case 3:
					json.addProperty("cnt4", result.get(i));
					break;
				case 4:
					json.addProperty("cnt5", result.get(i));
					break;
				}
			}
			request.setAttribute("dataToSend",  gson.toJson(json));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

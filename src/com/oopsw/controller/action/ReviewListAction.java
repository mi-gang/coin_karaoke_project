package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReviewVO;
import com.oopsw.service.KKService;

public class ReviewListAction implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("userId") != null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		json.addProperty("isLogin", isLogin);
		String userId = (String)session.getAttribute("userId");  // 로그인 상태가 아닐때는 null일것. ->.toString()은 나중에
		System.out.println("##### ReviewListAction 의 userID");
		System.out.println(userId);
		int kkId = Integer.parseInt(request.getParameter("kkId"));  // null이 들어갈 가능성 존재!
		
		if(isLogin) {
			/*String userId = session.getAttribute("userId").toString();
			int kkId = Integer.parseInt(request.getParameter("kkId"));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject json = new JsonObject();
			
			json.addProperty("isLogin", isLogin);*/
			// request.setAttribute("dataToSend",  gson.toJson(json));
			
		}
		
		try {
			System.out.println("~~~~~~~~~~~~~~~ ReviewListAction ~~~~~~~~~~~~~~~~~~~");
			ArrayList<ReviewVO> reviewList= (ArrayList<ReviewVO>)new KKService().getReviewListByKK(kkId);
			System.out.println(">> reviewList.size: " + reviewList.size());
			for(ReviewVO vo : reviewList) {
				JsonObject tmpObj = new JsonObject();
				tmpObj.addProperty("reviewId", vo.getReviewId());
				tmpObj.addProperty("content", vo.getContent());
				tmpObj.addProperty("nickName", vo.getUserNickname());
				System.out.println(tmpObj);
				
				jsonArray.add(tmpObj);
			}
			System.out.println(">> jsonArray size: ");
			System.out.println(jsonArray.size());
			json.add("reviewList", jsonArray);
			request.setAttribute("dataToSend",  gson.toJson(json));
			// json.addProperty("reviewList", jsonArray);
			request.setAttribute("reviewList", reviewList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("dataToSend",  gson.toJson(json));
		
		return new Url("json/data.jsp", Url.FORWARD);
	}

}

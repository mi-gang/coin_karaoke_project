package com.oopsw.controller.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.KKVO;
import com.oopsw.service.KKService;

public class SearchedKKList implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		//받아온 파라미터를 적절한 데이터로 가공...
		String searchGu = request.getParameter("searchGu");
		String[] chkOptions = request.getParameterValues("chkAdditionalOptions"); // {1,0,0,1} 하나의 String으로 들어옴		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"), formatter);
		LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"), formatter);
		int usingTime = Integer.parseInt(request.getParameter("usingTime"));
		int [] keywords = new int[0];
		if(chkOptions != null){
			keywords = new int[chkOptions.length];
			for (int i = 0; i < chkOptions.length; i++) {
				keywords[i] = Integer.parseInt(chkOptions[i]);
			}
		}
		//가공끝
		
		List<KKVO> result = new KKService().getKKList(searchGu, startTime, endTime, usingTime, keywords);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        request.setAttribute("dataToSend", gson.toJson(result));
        return new Url("json/data.jsp", Url.FORWARD);
	}

}

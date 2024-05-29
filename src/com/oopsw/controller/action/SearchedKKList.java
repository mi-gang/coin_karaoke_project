package com.oopsw.controller.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.KKVO;
import com.oopsw.service.KKService;

import jdk.internal.dynalink.beans.StaticClass;

public class SearchedKKList implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		//받아온 파라미터를 적절한 데이터로 가공...
		String searchGu = request.getParameter("searchGu");
		String[] chkOptions = request.getParameterValues("option"); // {1,0,0,1} 하나의 String으로 들어옴		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"), formatter);
		LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"), formatter);
		int usingMinutes = Integer.parseInt(request.getParameter("usingMinutes"));
		int [] keywords = new int[0];
		if(chkOptions != null){
			keywords = new int[chkOptions.length];
			for (int i = 0; i < chkOptions.length; i++) {
				keywords[i] = Integer.parseInt(chkOptions[i]);
			}
		}
		//가공끝
		
		KKService kkService = new KKService();
		ArrayList<String> chkOptionsContents = new ArrayList<String>();
		for (int keywordId : keywords) {
			chkOptionsContents.add(kkService.getKeywordByKeywordId(keywordId));
		}
		List<KKVO> resultList = new KKService().getKKList(searchGu, startTime, endTime, usingMinutes, keywords);
		
        request.setAttribute("chkOptionsContents", chkOptionsContents);
		request.setAttribute("searchGu", searchGu);
		request.setAttribute("resultList", resultList);
		request.setAttribute("usingTime", minutesToTime(usingMinutes));
		request.setAttribute("searchTime", dateTimeRange2TimeRange(startTime, endTime));
        return new Url("jsp/kkSearchResultListUI.jsp", Url.FORWARD);
	}
	private String dateTimeRange2TimeRange(LocalDateTime startTime, LocalDateTime endTime) {
		String start = String.format("%02d", startTime.getHour()) + ":" +String.format("%02d", startTime.getMinute());
		String end = String.format("%02d", endTime.getHour()) + ":" +String.format("%02d", endTime.getMinute());
		return start + " ~ " + end;
	}
	private String minutesToTime(int usingMinutes) {
		int hours = usingMinutes / 60;
		int minutes = usingMinutes % 60;
		String result = "";
		result += hours != 0 ? hours+"시간 " : "";
		
		result += minutes != 0 ? minutes+"분" : "";
		return result;
	}

}

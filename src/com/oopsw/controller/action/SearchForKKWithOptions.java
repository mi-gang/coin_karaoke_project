package com.oopsw.controller.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.KKVO;
import com.oopsw.service.KKService;

/*import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;*/


public class SearchForKKWithOptions implements Action {

	@Override
	public Url execute(HttpServletRequest request) {
		Url url = new Url("jsp/kkSearchResultListUI.jsp", Url.FORWARD);
		int result = 1;
		// KKService service;
		String searchGu = request.getParameter("searchGu");
		String[] chkOptions = request.getParameterValues("chkAdditionalOptions"); // {1,0,0,1} 하나의 String으로 들어옴
		int chkCount = Integer.parseInt(request.getParameter("chkCount"));
		
		System.out.println("XXXX SearchForKKWithOptions Action ---");
		System.out.println("searchGu: ");
		System.out.println(searchGu);
		System.out.println("--> Action chkOptions: ");
		System.out.println(chkOptions);
		System.out.println(chkOptions.length);
		String[] tmpChkOptions = chkOptions[0].split(",");
		for(int i=0; i<chkOptions.length; i++) {
			System.out.print(chkOptions[i] + "=");
		}
//		System.out.println(">> tmpChkOptions: ");
//		System.out.println(tmpChkOptions.length);
//		for(int i=0; i<tmpChkOptions.length; i++) {
//			System.out.println(tmpChkOptions[i]);
//		}
//		System.out.println("--> Action chkOptions 출력 종료");
		// request에 보낼 사용자가 선택한 추가조건 단어
		// String[] chkOptionsContents = new String[4];
		ArrayList chkOptionsContents = new ArrayList();
		if(tmpChkOptions[0] == "1") {
			// chkOptionsContents[0] = "주차 가능";
			chkOptionsContents.add("주차 가능");
		} else if(tmpChkOptions[1] == "1") {
			// chkOptionsContents[1] = "지상층";
			chkOptionsContents.add("지상층");
		} else if(tmpChkOptions[2] == "1") {
			// chkOptionsContents[2] = "냉난방";
			chkOptionsContents.add("냉난방");
		} else if(tmpChkOptions[3] == "1") {
			// chkOptionsContents[3] = "단체 가능";
			chkOptionsContents.add("단체 가능");
		}
		
		try {
			// KKService service = new KKService();
			// List<KKVO> resultList = service.getKKList(chkOptions, chkCount, searchGu);
			List<KKVO> resultList = new KKService().getKKList(tmpChkOptions, chkCount, searchGu);
			System.out.println("!!!!!!!!!"+resultList);
			System.out.println(resultList.size());
			
			
			
			// request에 보낼 내용
			request.setAttribute("chkOptionsContents", chkOptionsContents);
			request.setAttribute("searchGu", searchGu);
			request.setAttribute("resultList", resultList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}

}

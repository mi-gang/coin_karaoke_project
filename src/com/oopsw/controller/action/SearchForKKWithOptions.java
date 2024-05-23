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
		Url url = new Url("jsp/kkFilterUI.jsp", Url.FORWARD);
		int result = 1;
		KKService service;
		String searchGu = request.getParameter("searchGu");
		String[] chkOptions = request.getParameterValues("chkAdditionalOptions");
		int chkCount = Integer.parseInt(request.getParameter("chkCount"));
		
		System.out.println("--- SearchForKKWithOptions Action ---");
		System.out.println("searchGu: ");
		System.out.println(searchGu);
		System.out.println("chkOptions: ");
		System.out.println(chkOptions);
		for(int i=0; i<chkOptions.length; i++) {
			System.out.print(chkOptions[i] + " ");
		}
		
		try {
			service = new KKService();
			service.getKKList(chkOptions, chkCount, searchGu);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}

}

package com.oopsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		
		Action action = new ActionFactory().getAction(cmd);
		Url url = action.execute(request);
		//url에 따라 forward or sendRedirect
		if(url.getFlag() == Url.FORWARD){
			request.getRequestDispatcher("/"+url.getUrl()).forward(request, response);
		}else{
			response.sendRedirect(url.getUrl());
		}
	}


}

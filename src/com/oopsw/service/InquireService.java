package com.oopsw.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oopsw.model.dao.InquireDAO;
import com.oopsw.model.vo.InquireVO;

public class InquireService {
	public Connection conn;

	public InquireService() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 문의/신고 작성하기 */
	public boolean addInquire(InquireVO inquireVO) {

		boolean result = false;

		result = new InquireDAO(conn).addInquire(inquireVO);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}

package com.oopsw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oopsw.model.dao.ReviewDAO;
import com.oopsw.model.vo.ReviewVO;

public class ReviewService {
	public Connection conn;

	public ReviewService() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 리뷰 작성하기 */
	public boolean addReview(ReviewVO reviewVO) {

		boolean result = false;

		result = new ReviewDAO(conn).addReview(reviewVO);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 리뷰 삭제하기 */
	public boolean deleteReview(int reviewId) {

		boolean result = false;

		result = new ReviewDAO(conn).deleteReview(reviewId);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 나의 리뷰 리스트 불러오기 */
	public Collection<ReviewVO> getReviewListByUserId(String userId) {

		Collection<ReviewVO> reviewVOs = new ArrayList<ReviewVO>();

		reviewVOs = new ReviewDAO(conn).getReviewListByUserId(userId);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reviewVOs;
	}

}

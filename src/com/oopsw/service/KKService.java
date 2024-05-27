package com.oopsw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.dao.ReviewDAO;
import com.oopsw.model.vo.KKVO;
import com.oopsw.model.vo.ReviewVO;;

public class KKService {
	
	private Connection getConnection(){
		Connection conn = null;
		Context context;
		try {
			context = new InitialContext();
			System.out.println("Service context");
			DataSource dataSource =
					(DataSource) context.lookup("java:comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
			System.out.println("Service conn");
			conn.setAutoCommit(false);
		} catch (NamingException | SQLException e1) {
			System.err.println("커넥션 풀  실패. 수동으로 전환");
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
				conn = DriverManager.getConnection(url, "hr", "hr");
				conn.setAutoCommit(false);
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("수동 커넥션 생성 실패. 서비스 종료.");
				System.exit(404);
			}
			
		}
		
		return conn;
	}
	
	// 추가 메서드 - 노래방 상세정보 페이지의 기본 정보 불러오기
	public KKVO getSelectedKKBasicInfo(String kkId) throws SQLException {
		Connection conn = getConnection();
		KKVO vo = new KKDAO(conn).getSelectedKKBasicInfo(kkId);
		conn.close();
		return vo;
	}
	
	// 근처 추천 노래방 목록 불러오기 -- KKDAO의 getNearRecommendKKList
	public List<KKVO> getNearRecommendKKList(String addressGu) throws SQLException {
		Connection conn = getConnection();
		List<KKVO> result = new ArrayList<KKVO>();
		List<KKVO> getDataList = new KKDAO(conn).getNearRecommendKKList(addressGu);
		for(int i=0; i<getDataList.size(); i++) {
			int tmpId = getDataList.get(i).getKkId();
			float tmpStarAvg = getStarAvgByKK(tmpId);
			KKVO tmp = new KKVO(tmpId, getDataList.get(i).getName(), getDataList.get(i).getAddress(), tmpStarAvg);
			result.add(tmp);
		}
		
		conn.close();
		
		return result;
	}
	
	// 노래방 별점 불러오기 - reservationDAO의 getStarAvgByKKId
	public float getStarAvgByKK(int KKId) throws SQLException {
		Connection conn = getConnection();
		float stars = 0;
		stars = new ReservationDAO(conn).getStarAvgByKKId(KKId);
		conn.close();
		return stars;
	}
	
	// 노래방 혼잡도 계산하기 (보류)
	public float getKKCongestion(int KKId) {
		float congestion = 0.5f;
		return congestion;
	}
	
	
	// 검색 결과 목록 불러오기 - KKDAO의 getSearchKKList
	public List<KKVO> getKKList(String[] tmp, int countChkOption, String addressGu) throws SQLException {
		Connection conn = getConnection();
		// 구현
		List<KKVO> result = new ArrayList<KKVO>();
		// KKVO temp = new KKVO();
		// dao의 getSearchKKList 메서드 실행해서 검색 결과 테이블 가져오기
		// int[] tmp = {0,0,0,0};  // chkAdditionalOptions로 대체할 것임.
		int kkId = 0; // 위에 메서드 결과 - kk_id
		String name = null;  // 위에 메서드 결과 - kkName
		String address = null;  // 위에 메서드 결과 - kkAddress
		Collection<String> keywordList=new ArrayList<String>(); // 반복문 돌려서 노래방-키워드 목록 만들기
		// keywordList = new ArrayList<String>();
		// float starRating = getStarAvgByKK(kkId);
		float starRating = 0;
		// List<String[]> tmpKKList = new KKDAO(conn).getSearchKKList(tmp, countChkOption, addressGu);
		// System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
		
		// 추가조건을 선택하지 않은 경우
//		if(countChkOption == 0) {
//			System.out.println("추가조건 선택X로 노래방 검색!");
//			List<String[]> tmpKKList = new KKDAO(conn).getSearchKKList(addressGu);
//			System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
//			if(tmpKKList.size() > 1) {
//				System.out.println("if문!");
//				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
//				
//				// 검색 결과 2개 이상 있는 경우
//				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
//				name = tmpKKList.get(0)[1];
//				address = tmpKKList.get(0)[2];
//				starRating = getStarAvgByKK(kkId);
//				keywordList = new ArrayList<String>();
//				keywordList.add(tmpKKList.get(0)[3]);
//				for(int i=1; i<tmpKKList.size(); i++) {
//					if(kkId == Integer.parseInt(tmpKKList.get(i)[0])) {
//						keywordList.add(tmpKKList.get(i)[3]);
//					} else {
//						KKVO tmpVO = new KKVO(kkId, name, address, starRating, keywordList);
//						result.add(tmpVO);
//						kkId = Integer.parseInt(tmpKKList.get(i)[0]);
//						name = tmpKKList.get(i)[1];
//						address = tmpKKList.get(i)[2];
//						starRating = getStarAvgByKK(kkId);
//						keywordList = new ArrayList<String>();
//					}
//				}
//				
//				System.out.println(">> SERVICE의 getKKList 테스트!");
//				System.out.println("result.size(): " + result.size());
//				for(int i=0; i<result.size(); i++) {
//					System.out.println(result.get(i).getName() + " " + result.get(i).getStarRating() + " " + result.get(i).getAddress() + " " + result.get(i).getRepresentativeKeywordList().size());
//					System.out.println("------------------------------------");
//				}
//				conn.close();
//			} else if(tmpKKList.size() == 1) {
//				System.out.println("else if문!");
//				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
//				// 검색 결과 1개만 있는 경우
//				// KKVO(int kkId, String name, String address, float starRating, Collection<String> keywordList)
//				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
//				name = tmpKKList.get(0)[1];
//				address = tmpKKList.get(0)[2];
//				starRating = getStarAvgByKK(kkId);
//				keywordList = new ArrayList<String>();
//				keywordList.add(tmpKKList.get(0)[3]);
//				KKVO tmpVO = new KKVO(kkId, tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating, keywordList);
//				// KKVO tmpVO = new KKVO(Integer.parseInt(tmpKKList.get(0)[0]), tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating)
//				result.add(tmpVO);
//				
//				System.out.println(">> SERVICE의 getKKList 테스트!");
//				for(int i=0; i<result.size(); i++) {
//					System.out.println(result.get(i).getName() + " " + result.get(i).getStarRating() + " " + result.get(i).getAddress() + " " + result.get(i).getRepresentativeKeywordList().size());
//					System.out.println("------------------------------------");
//				}
//				conn.close();
//			} else {
//				System.out.println("else 문!");
//				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
//				System.out.println(">> SERVICE의 getKKList 테스트!");
//				for(int i=0; i<result.size(); i++) {
//					System.out.println(result.get(i).getName() + " " + result.get(i).getStarRating() + " " + result.get(i).getAddress() + " " + result.get(i).getRepresentativeKeywordList().size());
//					System.out.println("------------------------------------");
//				}
//				conn.close();
//				// 검색 결과 없음
//			}
//		} else {
			// 추가조건에 n개 조건 선택한 경우
			//System.out.println("추가조건 선택O로 노래방 검색!");
			List<String[]> tmpKKList = new KKDAO(conn).getSearchKKList(tmp, countChkOption, addressGu);
			if(tmpKKList.size() > 1) {
				System.out.println("if문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				// 검색 결과 2개 이상 있는 경우
				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
				name = tmpKKList.get(0)[1];
				address = tmpKKList.get(0)[2];
				// starRating = getStarAvgByKK(kkId);			
				starRating = new ReservationDAO(conn).getStarAvgByKKId(kkId);
				keywordList = new ArrayList<String>();
				keywordList.add(tmpKKList.get(0)[3]);
				
				for(int i=1; i<tmpKKList.size(); i++) {
					if(kkId == Integer.parseInt(tmpKKList.get(i)[0])) {
						keywordList.add(tmpKKList.get(i)[3]);
					} else {
						KKVO tmpVO = new KKVO(kkId, name, address, starRating, keywordList);
						result.add(tmpVO);
						kkId = Integer.parseInt(tmpKKList.get(i)[0]);
						name = tmpKKList.get(i)[1];
						address = tmpKKList.get(i)[2];
						// starRating = getStarAvgByKK(kkId);
						starRating = new ReservationDAO(conn).getStarAvgByKKId(kkId);
						keywordList = new ArrayList<String>();
					}
				}
				KKVO tmpVO = new KKVO(kkId, name, address, starRating, keywordList);
				result.add(tmpVO);
//				
				/*System.out.println(">> SERVICE의 getKKList 테스트!");
				for(int i=0; i<result.size(); i++) {
					System.out.println(result.get(i).getName() + " " + result.get(i).getStarRating() + " " + result.get(i).getAddress() + " " + result.get(i).getRepresentativeKeywordList().size());
					System.out.println("------------------------------------");
				}*/
				conn.close();
			} else if(tmpKKList.size() == 1) {
				System.out.println("else if문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				// 검색 결과 1개만 있는 경우
				// KKVO(int kkId, String name, String address, float starRating, Collection<String> keywordList)
				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
				// starRating = getStarAvgByKK(kkId);
				starRating = new ReservationDAO(conn).getStarAvgByKKId(kkId);
				keywordList = new ArrayList<String>();
				keywordList.add(tmpKKList.get(0)[3]);
				KKVO tmpVO = new KKVO(kkId, tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating, keywordList);
				// KKVO tmpVO = new KKVO(Integer.parseInt(tmpKKList.get(0)[0]), tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating)
				result.add(tmpVO);
				
				System.out.println(">> SERVICE의 getKKList 테스트!");
				for(int i=0; i<result.size(); i++) {
					System.out.println(result.get(i).getName() + " " + result.get(i).getStarRating() + " " + result.get(i).getAddress() + " " + result.get(i).getRepresentativeKeywordList().size());
					System.out.println("------------------------------------");
				}
				
				conn.close();				
			} else {
				System.out.println("else 문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				System.out.println(">> SERVICE의 getKKList 테스트!");
				for(int i=0; i<result.size(); i++) {
					System.out.println(result.get(i).getName() + " " + result.get(i).getStarRating() + " " + result.get(i).getAddress() + " " + result.get(i).getRepresentativeKeywordList().size());
					System.out.println("------------------------------------");
				}
				conn.close();
				// 검색 결과 없음
			}
//		}
		
		/*KKVO temp = new KKVO(kkId, name, address, starRating, keywordList);
		result.add(temp);
		return result;*/
			
		
		System.out.println("KKService's end..." +result);
		conn.close();
		return result;
	}
	
	// 해당 노래방 리뷰 불러오기 - ReviewDAO의 getReviewListByKKId
	public Collection<ReviewVO> getReviewListByKK(int KKId) throws SQLException {
		Connection conn = getConnection();
		ArrayList<ReviewVO> reviewList =  (ArrayList<ReviewVO>) new ReviewDAO(conn).getReviewListByKKId(KKId);
		
		/*for(int i=0; i<reviewList.size(); i++) {
			System.out.println(reviewList.get(i).getUserNickname());
			System.out.println(reviewList.get(i).getContent());
		}*/
		conn.close();
		return reviewList;
	}

}

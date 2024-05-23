package com.oopsw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.dao.ReviewDAO;
import com.oopsw.model.vo.KKVO;
import com.oopsw.model.vo.ReviewVO;;

public class KKService {
	private Connection conn;
	
	public KKService() throws ClassNotFoundException, SQLException {
	// 1. driver loading
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("1 loading ok");
			
			// 2. driver connection
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "hr", "hr");
			System.out.println("2 connection ok");
	}
	
	// 근처 추천 노래방 목록 불러오기 - KKDAO의 getNearRecommendKKList
	public List<KKVO> getNearRecommendKKList(String addressGu) {
		List<KKVO> result = new ArrayList<KKVO>();
		List<KKVO> getDataList = new KKDAO(conn).getNearRecommendKKList(addressGu);
		for(int i=0; i<getDataList.size(); i++) {
			int tmpId = getDataList.get(i).getKkId();
			float tmpStarAvg = getStarAvgByKK(tmpId);
			KKVO tmp = new KKVO(tmpId, getDataList.get(i).getName(), getDataList.get(i).getAddress(), tmpStarAvg);
			result.add(tmp);
		}
		
		return result;
	}
	
	// 노래방 별점 불러오기 - reservationDAO의 getStarAvgByKKId
	public float getStarAvgByKK(int KKId) {
		float stars = 0;
		stars = new ReservationDAO(conn).getStarAvgByKKId(KKId);
		return stars;
	}
	
	// 노래방 혼잡도 계산하기 (보류)
	public float getKKCongestion(int KKId) {
		float congestion = 0.5f;
		return congestion;
	}
	
	
	// 검색 결과 목록 불러오기 - KKDAO의 getSearchKKList
	public List<KKVO> getKKList(int[] tmp, int countChkOption, String addressGu) {
		// 구현
		List<KKVO> result = new ArrayList<KKVO>();
		// KKVO temp = new KKVO();
		// dao의 getSearchKKList 메서드 실행해서 검색 결과 테이블 가져오기
		// int[] tmp = {0,0,0,0};  // chkAdditionalOptions로 대체할 것임.
		int kkId = 0; // 위에 메서드 결과 - kk_id
		String name = null;  // 위에 메서드 결과 - kkName
		String address = null;  // 위에 메서드 결과 - kkAddress
		Collection<String> keywordList; // 반복문 돌려서 노래방-키워드 목록 만들기
		// keywordList = new ArrayList<String>();
		// float starRating = getStarAvgByKK(kkId);
		float starRating = 0;
		// List<String[]> tmpKKList = new KKDAO(conn).getSearchKKList(tmp, countChkOption, addressGu);
		// System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
		
		// 추가조건을 선택하지 않은 경우
		if(countChkOption == 0) {
			System.out.println("추가조건 선택X로 노래방 검색!");
			List<String[]> tmpKKList = new KKDAO(conn).getSearchKKList(addressGu);
			System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
			if(tmpKKList.size() > 1) {
				System.out.println("if문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				
				// 검색 결과 2개 이상 있는 경우
				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
				name = tmpKKList.get(0)[1];
				address = tmpKKList.get(0)[2];
				starRating = getStarAvgByKK(kkId);
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
						starRating = getStarAvgByKK(kkId);
						keywordList = new ArrayList<String>();
					}
				}
				
				return result;
			} else if(tmpKKList.size() == 1) {
				System.out.println("else if문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				// 검색 결과 1개만 있는 경우
				// KKVO(int kkId, String name, String address, float starRating, Collection<String> keywordList)
				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
				name = tmpKKList.get(0)[1];
				address = tmpKKList.get(0)[2];
				starRating = getStarAvgByKK(kkId);
				keywordList = new ArrayList<String>();
				keywordList.add(tmpKKList.get(0)[3]);
				KKVO tmpVO = new KKVO(kkId, tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating, keywordList);
				// KKVO tmpVO = new KKVO(Integer.parseInt(tmpKKList.get(0)[0]), tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating)
				result.add(tmpVO);
				
				return result;
			} else {
				System.out.println("else 문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());

				// 검색 결과 없음
				return result;
			}
		} else {
			// 추가조건에 n개 조건 선택한 경우
			System.out.println("추가조건 선택O로 노래방 검색!");
			List<String[]> tmpKKList = new KKDAO(conn).getSearchKKList(tmp, countChkOption, addressGu);
			if(tmpKKList.size() > 1) {
				System.out.println("if문!!!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				// 검색 결과 2개 이상 있는 경우
				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
				name = tmpKKList.get(0)[1];
				address = tmpKKList.get(0)[2];
				starRating = getStarAvgByKK(kkId);
				keywordList = new ArrayList<String>();
				keywordList.add(tmpKKList.get(0)[3]);
				for(int i=1; i<tmpKKList.size(); i++) {
					if(kkId == Integer.parseInt(tmpKKList.get(i)[0])) {
						if(i == tmpKKList.size()-1) {
							KKVO tmpVO = new KKVO(kkId, name, address, starRating, keywordList);
							result.add(tmpVO);
						} else {
							keywordList.add(tmpKKList.get(i)[3]);
						}
					} else {
						KKVO tmpVO = new KKVO(kkId, name, address, starRating, keywordList);
						result.add(tmpVO);
						kkId = Integer.parseInt(tmpKKList.get(i)[0]);
						name = tmpKKList.get(i)[1];
						address = tmpKKList.get(i)[2];
						starRating = getStarAvgByKK(kkId);
						keywordList = new ArrayList<String>();
					}
				}
				
				return result;
			} else if(tmpKKList.size() == 1) {
				System.out.println("else if문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				// 검색 결과 1개만 있는 경우
				// KKVO(int kkId, String name, String address, float starRating, Collection<String> keywordList)
				kkId = Integer.parseInt(tmpKKList.get(0)[0]);
				starRating = getStarAvgByKK(kkId);
				keywordList = new ArrayList<String>();
				keywordList.add(tmpKKList.get(0)[3]);
				KKVO tmpVO = new KKVO(kkId, tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating, keywordList);
				// KKVO tmpVO = new KKVO(Integer.parseInt(tmpKKList.get(0)[0]), tmpKKList.get(0)[1], tmpKKList.get(0)[2], starRating)
				result.add(tmpVO);
				
				return result;
			} else {
				System.out.println("else 문!");
				System.out.println(">> tmpKKList.size(): " + tmpKKList.size());
				// 검색 결과 없음
				return result;
			}
		}
	}
	
	// 해당 노래방 리뷰 불러오기 - ReviewDAO의 getReviewListByKKId
	public Collection<ReviewVO> getReviewListByKK(int KKId) {
		ArrayList<ReviewVO> reviewList =  (ArrayList<ReviewVO>) new ReviewDAO(conn).getReviewListByKKId(KKId);

		return reviewList;
	}

}

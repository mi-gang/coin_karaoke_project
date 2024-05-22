package com.oopsw.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.dao.ReviewDAO;
import com.oopsw.model.vo.KKVO;

public class KKService {
	
	// 근처 추천 노래방 목록 불러오기 - KKDAO의 getNearRecommendKKList
	// 해당 파일의 getKKList 메서드 재활용하면 될 듯함!
	
	// 노래방 별점 불러오기 - reservationDAO의 getStarAvgByKKId
	
	
	// 노래방 혼잡도 계산하기 (보류)
	
	// 검색 결과 목록 불러오기 - KKDAO의 getSearchKKList
	public List<KKVO> getKKList(int[] chkAdditionalOptions, int countChkOption, String addressGu) {
		// 구현
		ArrayList<KKVO> result = new ArrayList<KKVO>();
		// KKVO temp = new KKVO();
		// dao의 getSearchKKList 메서드 실행해서 검색 결과 테이블 가져오기
		int kkId = 0; // 위에 메서드 결과 - kk_id
		String name = null;  // 위에 메서드 결과 - kkName
		String address = null;  // 위에 메서드 결과 - kkAddress
		Collection<String> keywordList; // // 반복문 돌려서 노래방-키워드 목록 만들기
		keywordList = new ArrayList<String>();
		float starRating = 0;
		KKVO temp = new KKVO(kkId, name, address, starRating, keywordList);
		result.add(temp);
		return result;
	}
	
	// 해당 노래방 리뷰 불러오기 - ReviewDAO의 getReviewListByKKId

}

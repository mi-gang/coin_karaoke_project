package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.oopsw.model.vo.KKVO;

public class KKDAO {
	private Connection conn;
	
	public KKDAO(Connection conn) {
		this.conn = conn;
	}
	
	//작성해야하는 메서드
	// 완료 - 근처 추천 노래방 목록 불러오기   - getNearRecommendKKList
	public List<KKVO> getNearRecommendKKList(String addressGu) {
		String sql = "SELECT kk_id, name, address FROM KKs k1 WHERE trim(substr(address, 4, 3)) = ?";
		List<KKVO> recommendKKList = new ArrayList<KKVO>();
		KKVO vo = null;
		System.out.println("근처 추천 노래방 목록 불러오기");
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addressGu);
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			
			// 상호명, 주소, 대표 키워드
			if(rs.next()) {
				while(rs.next()) {
					vo = new KKVO(rs.getInt(1), rs.getString(2), rs.getString(3));
					recommendKKList.add(vo);
				}
			} else {
				System.out.println("검색 결과 없음"); // recommendKKList가 null이 아닌 size 0이다!
			}
			
			
		System.out.println("------------------------");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return recommendKKList;
	}
	// 검색 결과 목록 불러오기   - getSearchKKList
	// 추가조건X인 경우
	public List<KKVO> searchResultKKList (String addressGu) {
		String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content "
				+ "FROM kks, keywords kw, kk_keywords kkw "
				+ "WHERE trim(substr(kks.address, 4, 3)) = ? "
				+ "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
				+ "AND (kks.note not in('금일휴업', '임시휴무')) "
				+ "ORDER BY kks.kk_id";
		List<KKVO> resultKKList = new ArrayList<KKVO>();
		KKVO vo = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addressGu);
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			// int tmpKKId = 0;
			// String tmpKKName = null;
			// String tmpKKAddress = null;
			// Collection<String> tmpKeywords = new ArrayList<String>();
						
			while(rs.next()) {
				// vo = new KKVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getObject(4));	
				// recommendKKList.add(vo);
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultKKList;
	}
	// 추가조건O인 경우
	public List<KKVO> searchResultKKList (String addressGu, List<String>additionalConditionList) {
		String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content "
				+ "FROM kks, keywords kw, kk_keywords kkw "
				+ "WHERE trim(substr(kks.address, 4, 3)) = ? "
				+ "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
				+ "AND (kks.note not in('금일휴업', '임시휴무'))"
				+ "AND (kw.keyword_id=1 or kw.keyword_id=3 or kw.keyword_id=5) "
				+ "GROUP BY kks.kk_id "
				+ "HAVING count(kks.kk_id)=3";
		List<KKVO> resultKKList = new ArrayList<KKVO>();
		return resultKKList;
	}
	
	
	/*
	SELECT kks.kk_id, kks.name, kks.address, kw.content
	FROM kks, keywords kw, kk_keywords kkw
	WHERE trim(substr(kks.address, 4, 3)) = '금천구'
	  AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id)
	  AND (kks.note not in('금일휴업', '임시휴무'))
	  AND (kw.content IN ('단체', '단체 가능', '단체 이용 가능'));
	*/
	
	// 완료 - 해당 노래방 즐겨찾기(북마크) 여부   - isKKBookmark
	public boolean isKKBookmark(String userId, int kkId) {
		boolean result = false;
		String sql = "SELECT kk_id FROM kk_bookmarks WHERE user_id=? and kk_id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, kkId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("result: " + result);
		
		return result;
	}
	
	
	// 완료 - 해당 노래방 즐겨찾기(북마크) On   - addKKBookmark
	public boolean addKKBookmark(String userId, int kkId) {
		boolean result = false;
		String sql = "INSERT INTO kk_bookmarks (user_id, KK_id) VALUES (?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, kkId);
			System.out.println(pstmt);
			if(pstmt.executeUpdate() >= 1){
				result = true;
			} else {
				result = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 완료 - 해당 노래방 즐겨찾기(북마크) Off   - deleteKKBookmark
	public boolean deleteKKBookmark(String userId, int kkId) {
		boolean result = false;
		String sql = "DELETE FROM kk_bookmarks WHERE user_id=? AND kk_id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, kkId);
			if(pstmt.executeUpdate() >= 1){
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// 해당 노래방의 방  불러오기   - getRoomInfoList
	public Map<Integer, String> getRoomInfoList (int kkId) {
		Map<Integer, String> roomInfoList = new HashMap<Integer, String>();
		String sql = "SELECT room_id, name FROM room_infos WHERE kk_id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kkId);
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			
			while(rs.next()) {
				System.out.println("count");
				roomInfoList.put(Integer.valueOf(rs.getInt(1)), rs.getString(2));
			}
			
			System.out.println("해당 노래방의 방 정보 불러오기");
			for(Entry<Integer, String> entrySet : roomInfoList.entrySet()) {
				System.out.println(entrySet.getKey() + " - " + entrySet.getValue());
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return roomInfoList;
	}
	
}
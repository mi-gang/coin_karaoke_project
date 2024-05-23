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

	// 완료 - 근처 추천 노래방 목록 불러오기 - getNearRecommendKKList
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

			// if (rs.next()) {
				while (rs.next()) {
					System.out.println("while rs.next()");
					vo = new KKVO(rs.getInt(1), rs.getString(2), rs.getString(3));
					recommendKKList.add(vo);
					System.out.println("recommendKKList.add 완");
				}
			/*} else {
				System.out.println("검색 결과 없음"); // recommendKKList가 null이 아닌 size 0이다!
			}*/

			System.out.println("recommendKKList.size(): " + recommendKKList.size());
			for(int i=0; i<recommendKKList.size(); i++) {
				System.out.println(recommendKKList.get(i).getName() + " " + recommendKKList.get(i).getAddress() + " " + recommendKKList.get(i).getStarRating());
			}
			System.out.println("------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recommendKKList;
	}

	// 검색 결과 목록 불러오기 - getSearchKKList
	// 완료 - 추가조건X인 경우
	public List<String[]> getSearchKKList(String addressGu) {
		String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content "
				+ "FROM kks, keywords kw, kk_keywords kkw "
				+ "WHERE trim(substr(kks.address, 4, 3)) = ? "
				+ "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
				+ "AND (kks.note not in('금일휴업', '임시휴무')) "
				+ "ORDER BY kks.kk_id";
		List<String[]> resultList = new ArrayList<String[]>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addressGu);
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				String[] tmp = new String[4];
				tmp[0] = Integer.toString(rs.getInt(1));
				tmp[1] = rs.getString(2);
				tmp[2] = rs.getString(3);
				tmp[3] = rs.getString(4);
				resultList.add(tmp);
			}

			for (int i = 0; i < resultList.size(); i++) {
				for (int j = 0; j < resultList.get(0).length; j++) {
					System.out.println(resultList.get(i)[j]);
				}
				System.out.println("----------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}
	/*
	 * public List<KKVO> getSearchKKList (String addressGu) {
	 * String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content "
	 * + "FROM kks, keywords kw, kk_keywords kkw "
	 * + "WHERE trim(substr(kks.address, 4, 3)) = ? "
	 * + "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
	 * + "AND (kks.note not in('금일휴업', '임시휴무')) "
	 * + "ORDER BY kks.kk_id";
	 * List<KKVO> resultKKList = new ArrayList<KKVO>();
	 * KKVO vo = null;
	 * 
	 * try {
	 * PreparedStatement pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, addressGu);
	 * System.out.println("pstmt");
	 * ResultSet rs = pstmt.executeQuery();
	 * System.out.println(rs);
	 * int tmpKKId = 0;
	 * String tmpKKName = null;
	 * String tmpKKAddress = null;
	 * Collection<String> tmpKeywords = new ArrayList<String>();
	 * // Collection<String> keywordList = new ArrayList<String>();
	 * 
	 * /*
	 * do {
	 * tmpKKId = rs.getInt(1);
	 * tmpKKName = rs.getString(2);
	 * tmpKKAddress = rs.getString(3);
	 * tmpKeywords.add(rs.getString(4));
	 * } while(rs.next()) {
	 * if(tmpKKId != rs.getInt(1)) {
	 * Collection<String> keywordList = new ArrayList<String>();
	 * for(String k : tmpKeywords) {
	 * keywordList.add(k);
	 * }
	 * tmpKeywords.clear();
	 * vo = new KKVO(tmpKKId, tmpKKName, tmpKKAddress, keywordList);
	 * resultKKList.add(vo);
	 * keywordList.clear();
	 * tmpKKId = rs.getInt(1);
	 * } else {
	 * tmpKKId = rs.getInt(1);
	 * tmpKKName = rs.getString(2);
	 * tmpKKAddress = rs.getString(3);
	 * tmpKeywords.add(rs.getString(4));
	 * }
	 * // vo = new KKVO(rs.getInt(1), rs.getString(2), rs.getString(3),
	 * rs.getObject(4));
	 * // recommendKKList.add(vo);
	 * }
	 * ///
	 * 
	 * System.out.println("추가조건X 노래방 검색결과 출력");
	 * for(int i=0; i<resultKKList.size(); i++) {
	 * System.out.println(resultKKList.get(i).getKkId());
	 * System.out.println(resultKKList.get(i).getName());
	 * System.out.println(resultKKList.get(i).getAddress());
	 * Collection<String> tmp = resultKKList.get(i).getRepresentativeKeywordList();
	 * for(String k : tmp) {
	 * System.out.print(k + ", ");
	 * }
	 * System.out.println("------------------------");
	 * }
	 * 
	 * } catch(SQLException e) {
	 * e.printStackTrace();
	 * }
	 * 
	 * return resultKKList;
	 * }
	 */

	// 완료 - 추가조건O인 경우
	public List<String[]> getSearchKKList(int[] chkAdditionalOptions, int countChkOption, String addressGu) {
		String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content "
				+ "FROM kks, keywords kw, kk_keywords kkw "
				+ "WHERE kks.kk_id IN ("
				+ "SELECT kk_id "
				+ "FROM kk_keywords "
				+ "WHERE ((keyword_id=3 AND 1 = ?) "
				+ "OR (keyword_id=5 AND 1 = ?) "
				+ "OR (keyword_id=6 AND 1 = ?) "
				+ "OR (keyword_id=1 AND 1 = ?)) "
				+ "GROUP BY kk_id "
				+ "HAVING COUNT(kk_id)=?) "
				+ "AND trim(substr(kks.address, 4, 3)) = ? "
				+ "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
				+ "AND (kks.note NOT IN('금일휴업', '임시휴무')) "
				+ "ORDER BY kks.kk_id";
		List<String[]> resultList = new ArrayList<String[]>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chkAdditionalOptions[0]);
			pstmt.setInt(2, chkAdditionalOptions[1]);
			pstmt.setInt(3, chkAdditionalOptions[2]);
			pstmt.setInt(4, chkAdditionalOptions[3]);
			pstmt.setInt(5, countChkOption);
			pstmt.setString(6, addressGu);
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				System.out.println("while문");
				String[] tmp = new String[4];
				tmp[0] = Integer.toString(rs.getInt(1));
				tmp[1] = rs.getString(2);
				tmp[2] = rs.getString(3);
				tmp[3] = rs.getString(4);
				resultList.add(tmp);
			}

			for (int i = 0; i < resultList.size(); i++) {
				for (int j = 0; j < resultList.get(0).length; j++) {
					System.out.println(resultList.get(i)[j]);
				}
				System.out.println("----------------");
			}
			System.out.println("KKDAO 메서드 종료");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

		return resultList;
	}
	/*
	 * public List<String[]> getSearchKKList (int[] chkAdditionalOptions, int
	 * countChkOption, String addressGu) {
	 * String sql =
	 * "SELECT kks.kk_id, kks.name, kks.address, kw.content, kw.keyword_id "
	 * + "FROM kks, keywords kw, kk_keywords kkw "
	 * + "WHERE kks.kk_id IN ("
	 * + "SELECT kk_id "
	 * + "FROM kk_keywords "
	 * + "WHERE ((keyword_id=3 AND 1 = ?) "
	 * + "OR (keyword_id=5 AND 1 = ?) "
	 * + "OR (keyword_id=6 AND 1 = ?) "
	 * + "OR (keyword_id=1 AND 1 = ?)) "
	 * + "GROUP BY kk_id "
	 * + "HAVING COUNT(kk_id)=?) "
	 * + "AND trim(substr(kks.address, 4, 3)) = ? "
	 * + "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
	 * + "AND (kks.note NOT IN('금일휴업', '임시휴무')) "
	 * + "ORDER BY kks.kk_id";
	 * List<String[]> resultList = new ArrayList<String[]>();
	 * try {
	 * PreparedStatement pstmt = conn.prepareStatement(sql);
	 * pstmt.setInt(1, chkAdditionalOptions[0]);
	 * pstmt.setInt(2, chkAdditionalOptions[1]);
	 * pstmt.setInt(3, chkAdditionalOptions[2]);
	 * pstmt.setInt(4, chkAdditionalOptions[3]);
	 * pstmt.setInt(5, countChkOption);
	 * pstmt.setString(6, addressGu);
	 * System.out.println("pstmt");
	 * ResultSet rs = pstmt.executeQuery();
	 * System.out.println(rs);
	 * 
	 * while (rs.next()) {
	 * System.out.println("while문");
	 * String[] tmp = new String[3];
	 * tmp[0] = Integer.toString(rs.getInt(1));
	 * tmp[1] = rs.getString(2);
	 * tmp[2] = rs.getString(3);
	 * resultList.add(tmp);
	 * }
	 * 
	 * for (int i = 0; i < resultList.size(); i++) {
	 * for (int j = 0; j < resultList.get(0).length; j++) {
	 * System.out.println(resultList.get(i)[j]);
	 * }
	 * System.out.println("----------------");
	 * }
	 * 
	 * } catch (SQLException e) {
	 * e.printStackTrace();
	 * }
	 * 
	 * return resultList;
	 * }
	 */

	// 완료 - 해당 노래방 즐겨찾기(북마크) 여부 - isKKBookmark
	public boolean isKKBookmark(String userId, int kkId) {
		boolean result = false;
		String sql = "SELECT kk_id FROM kk_bookmarks WHERE user_id=? and kk_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, kkId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("result: " + result);

		return result;
	}

	// 완료 - 해당 노래방 즐겨찾기(북마크) On - addKKBookmark
	public boolean addKKBookmark(String userId, int kkId) {
		boolean result = false;
		String sql = "INSERT INTO kk_bookmarks (user_id, KK_id) VALUES (?, ?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, kkId);
			System.out.println(pstmt);
			if (pstmt.executeUpdate() >= 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 완료 - 해당 노래방 즐겨찾기(북마크) Off - deleteKKBookmark
	public boolean deleteKKBookmark(String userId, int kkId) {
		boolean result = false;
		String sql = "DELETE FROM kk_bookmarks WHERE user_id=? AND kk_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, kkId);
			if (pstmt.executeUpdate() >= 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 완료 - 해당 노래방의 방 불러오기 - getRoomInfoList
	public Map<Integer, String> getRoomInfoList(int kkId) {
		Map<Integer, String> roomInfoList = new HashMap<Integer, String>();
		String sql = "SELECT room_id, name FROM room_infos WHERE kk_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kkId);
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				System.out.println("count");
				roomInfoList.put(Integer.valueOf(rs.getInt(1)), rs.getString(2));
			}

			System.out.println("해당 노래방의 방 정보 불러오기");
			for (Entry<Integer, String> entrySet : roomInfoList.entrySet()) {
				System.out.println(entrySet.getKey() + " - " + entrySet.getValue());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roomInfoList;
	}

}
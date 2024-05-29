package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
// import java.text.SimpleDateFormat;
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
	
	// 추가 메서드 - 노래방 상세정보 페이지의 기본 정보 불러오기
	public KKVO getSelectedKKBasicInfo(String kkId) {
		String sql = "SELECT kk_id, name, opening_hour, closing_hour, note, address "
				+ "FROM kks WHERE kk_id=?";
		String sql2 = "SELECT k.kk_id, kw.content FROM kks k, keywords kw, kk_keywords kkw "
				+ "WHERE k.kk_id=? AND k.kk_id = kkw.kk_id "
				+ "AND kw.keyword_id=kkw.keyword_id ORDER BY k.kk_id";
		KKVO vo = null;
		Collection<String> tmpKeywordList = new ArrayList<String>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(kkId));
			System.out.println("pstmt");
			ResultSet rs = pstmt.executeQuery();
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, Integer.parseInt(kkId));
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println(rs);

			// kkId, name, opHour, clHour, note, address + keywords
			if (rs.next()) {
				Date opHour = rs.getDate(3);
				LocalDate localOpHour = opHour.toLocalDate();
				Date csHour = rs.getDate(4);
				LocalDate localCsHour = csHour.toLocalDate();
				LocalTime localTime = LocalTime.of(0, 0,0);
				LocalDateTime opDateTime = localOpHour.atTime(localTime);
				LocalDateTime csDateTime = localCsHour.atTime(localTime);
				/*//
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				String formattedOpeningHour = formatter.format(opDateTime);
				String formattedClosingHour = formatter.format(csDateTime);	*/			
				
				vo = new KKVO(rs.getInt(1), rs.getString(2), opDateTime, csDateTime,
						rs.getString(5), rs.getString(6));
				while(rs2.next()) {
					tmpKeywordList.add(rs2.getString(2));					
				}
				vo.setRepresentativeKeywordList(tmpKeywordList);
			} else {
				System.out.println("검색 결과 없음"); // recommendKKList가 null이 아닌 size 0이다!
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
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
	public List<String[]> getSearchKKList(String[] chkAdditionalOptions, int countChkOption, String addressGu) {
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
			// System.out.println("추가조건 배열 크기: " + chkAdditionalOptions.length);
			System.out.println("===== KKDAO chkAdditionalOptions =====");
			 System.out.println(chkAdditionalOptions.length);
			// String[] tmpArr = chkAdditionalOptions[0].split(",");
			if(chkAdditionalOptions.length == 1) {
				System.out.println("chkOptions 길이 1 if문");
				System.out.println(">> chkOptions[0]");
				System.out.println(chkAdditionalOptions[0]);
				String[] tmpArr = chkAdditionalOptions[0].split(",");
				for(int i=0; i < tmpArr.length; i++) {
					// System.out.println(chkAdditionalOptions[i]);
					System.out.println(tmpArr[i]);
					/*pstmt.setString(1, tmpArr[0]);
					pstmt.setString(2, tmpArr[1]);
					pstmt.setString(3, tmpArr[2]);
					pstmt.setString(4, tmpArr[3]);*/
					pstmt.setInt(1, Integer.parseInt(tmpArr[0]));
					/*pstmt.setInt(2, Integer.parseInt(tmpArr[1]));
					pstmt.setInt(3, Integer.parseInt(tmpArr[2]));
					pstmt.setInt(4, Integer.parseInt(tmpArr[3]));*/
				}
			} else {
				System.out.println("chkOptions 길이 1 아님 else 문");
				System.out.println(chkAdditionalOptions.length);
				for(int i=0; i<chkAdditionalOptions.length; i++) {
					System.out.println(chkAdditionalOptions[i]);
				}
				System.out.println("------------");
				/*pstmt.setString(1, chkAdditionalOptions[0]);
				pstmt.setString(2, chkAdditionalOptions[1]);
				pstmt.setString(3, chkAdditionalOptions[2]);
				pstmt.setString(4, chkAdditionalOptions[3]);*/
				pstmt.setInt(1, Integer.parseInt(chkAdditionalOptions[0]));
				pstmt.setInt(2, Integer.parseInt(chkAdditionalOptions[1]));
				pstmt.setInt(3, Integer.parseInt(chkAdditionalOptions[2]));
				pstmt.setInt(4, Integer.parseInt(chkAdditionalOptions[3]));
			}
			
			/*System.out.println("tmpArr 크기: " + tmpArr.length);
			for(int i=0; i < tmpArr.length; i++) {
				System.out.println(tmpArr[i]);
			}
			chkAdditionalOptions[0] = tmpArr[0];
			/*
			
			// System.out.println("===== chkAdditionalOptions 0 1 2 3 출력 =====");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i=0; i<tmpArr.length; i++) {
				pstmt.setInt((i+1), Integer.parseInt(tmpArr[i]));
			}
			
			/*pstmt.setString(1, tmpArr[0]);
			pstmt.setString(2, tmpArr[1]);
			pstmt.setString(3, tmpArr[2]);
			pstmt.setString(4, tmpArr[3]);*/
			
			/*System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(chkAdditionalOptions[0]);
			System.out.println(chkAdditionalOptions[1]);
			System.out.println(chkAdditionalOptions[2]);
			System.out.println(chkAdditionalOptions[3]);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			pstmt.setInt(1, Integer.parseInt(chkAdditionalOptions[0]));
			pstmt.setInt(2, Integer.parseInt(chkAdditionalOptions[1]));
			pstmt.setInt(3, Integer.parseInt(chkAdditionalOptions[2]));
			pstmt.setInt(4, Integer.parseInt(chkAdditionalOptions[3]));*/
			/*pstmt.setInt(1, Integer.parseInt(tmpArr[0]));
			pstmt.setInt(2, Integer.parseInt(tmpArr[1]));
			pstmt.setInt(3, Integer.parseInt(tmpArr[2]));
			pstmt.setInt(4, Integer.parseInt(tmpArr[3]));*/
			pstmt.setInt(5, countChkOption);
			pstmt.setString(6, addressGu);
			ResultSet rs = pstmt.executeQuery();
			/*System.out.println("pstmt");
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);*/

			while (rs.next()) {
				System.out.println("while문");
				String[] tmp = new String[4];
				tmp[0] = Integer.toString(rs.getInt(1));
				tmp[1] = rs.getString(2);
				tmp[2] = rs.getString(3);
				tmp[3] = rs.getString(4);
				resultList.add(tmp);
			}
			
			/*for (int i = 0; i < resultList.size(); i++) {
				for (int j = 0; j < resultList.get(0).length; j++) {
					System.out.println(resultList.get(i)[j]);
				}
				System.out.println("----------------");
			}
			System.out.println("KKDAO 메서드 종료");*/

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
	
	/** 저장한 노래방 개수 한개 불러오기 */
	public List<String[]> getMypageBookmarkKK(String userId) {
		String sql = "select kks.kk_id, kks.name, kks.address, kws.content "
				+ "from kk_bookmarks bm "
				+ "JOIN kks kks ON kks.kk_id = bm.kk_id "
				+ "JOIN kk_keywords kw ON kks.kk_id = kw.kk_id "
				+ "JOIN keywords kws ON kws.keyword_id = kw.keyword_id "
				+ "where kks.kk_id = (select kk_id from ( "
				+ "select kks.kk_id, kks.name, kks.address, kws.content "
				+ "from kk_bookmarks bm "
				+ "JOIN kks kks ON kks.kk_id = bm.kk_id "
				+ "JOIN kk_keywords kw ON kks.kk_id = kw.kk_id "
				+ "JOIN keywords kws ON kws.keyword_id = kw.keyword_id "
				+ "where bm.user_id=? "
				+ "ORDER BY kks.kk_id) where rownum = 1)";
		
		List<String[]> resultList = new ArrayList<String[]>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String[] tmp = new String[4];
				tmp[0] = Integer.toString(rs.getInt(1));
				tmp[1] = rs.getString(2);
				tmp[2] = rs.getString(3);
				tmp[3] = rs.getString(4);
				resultList.add(tmp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}
	
	/** 저장한 노래방 개수 불러오기 */
	public int getBookmarkCount(String userId) {

		String sql = "select count(kk_id) "
				+ "from KK_BOOKMARKS "
				+ "where user_id=?";

		int result = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
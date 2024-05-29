package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.oopsw.model.vo.KKVO;

public class KKDAO {
	private Connection conn;

	public KKDAO(Connection conn) {
		this.conn = conn;
	}

	// 추가 메서드 - 노래방 별점 종류(1~5)별 개수 정보 가져오기
	public ArrayList<String> getAmountStarReviewByType(String kkId) {
		String sql = "SELECT COUNT(DISTINCT r.review_id) "
				+ "FROM (SELECT * FROM reviews rev, reservations res, KKs k, room_infos ri "
				+ "WHERE (k.kk_id=?) AND (k.kk_id=ri.kk_id AND ri.room_id=res.room_id AND res.reservation_id=rev.reservation_id) "
				+ "ORDER BY res.END_TIME DESC), kks, room_infos ri, reviews r, reservations rv, users u "
				+ "WHERE ROWNUM >= 1 and ROWNUM <= 10 " + "AND(kks.kk_id=ri.kk_id AND ri.room_id=rv.room_id "
				+ "AND rv.reservation_id=r.reservation_id AND u.user_id=rv.user_id) " + "AND r.star=?";
		ArrayList<String> starAmountList = new ArrayList<String>();

		try {
			PreparedStatement pstmt1;
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, Integer.parseInt(kkId));
			pstmt1.setInt(2, 1);
			ResultSet rs1 = pstmt1.executeQuery();

			if (rs1.next()) {
				starAmountList.add(rs1.getString(1));
			}
			//
			PreparedStatement pstmt2;
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, Integer.parseInt(kkId));
			pstmt2.setInt(2, 2);
			ResultSet rs2 = pstmt2.executeQuery();

			if (rs2.next()) {
				starAmountList.add(rs2.getString(1));
			}
			//
			PreparedStatement pstmt3;
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, Integer.parseInt(kkId));
			pstmt3.setInt(2, 3);
			ResultSet rs3 = pstmt3.executeQuery();

			if (rs3.next()) {
				starAmountList.add(rs3.getString(1));
			}
			//
			PreparedStatement pstmt4;
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setInt(1, Integer.parseInt(kkId));
			pstmt4.setInt(2, 4);
			ResultSet rs4 = pstmt4.executeQuery();

			if (rs4.next()) {
				starAmountList.add(rs4.getString(1));
			}
			//
			PreparedStatement pstmt5;
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, Integer.parseInt(kkId));
			pstmt5.setInt(2, 5);
			ResultSet rs5 = pstmt5.executeQuery();

			if (rs5.next()) {
				starAmountList.add(rs5.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return starAmountList;
	}

	// 추가 메서드 - 노래방 상세정보 페이지의 기본 정보 불러오기
	public KKVO getSelectedKKBasicInfo(String kkId) {
		String sql = "SELECT kk_id, name, opening_hour, closing_hour, note, address " + "FROM kks WHERE kk_id=?";
		String sql2 = "SELECT k.kk_id, kw.content FROM kks k, keywords kw, kk_keywords kkw "
				+ "WHERE k.kk_id=? AND k.kk_id = kkw.kk_id " + "AND kw.keyword_id=kkw.keyword_id ORDER BY k.kk_id";
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
				LocalTime localTime = LocalTime.of(0, 0, 0);
				LocalDateTime opDateTime = localOpHour.atTime(localTime);
				LocalDateTime csDateTime = localCsHour.atTime(localTime);
				/*
				 * // SimpleDateFormat formatter = new
				 * SimpleDateFormat("HH:mm"); String formattedOpeningHour =
				 * formatter.format(opDateTime); String formattedClosingHour =
				 * formatter.format(csDateTime);
				 */

				vo = new KKVO(rs.getInt(1), rs.getString(2), opDateTime, csDateTime, rs.getString(5), rs.getString(6));
				while (rs2.next()) {
					tmpKeywordList.add(rs2.getString(2));
				}
				vo.setRepresentativeKeywordList(tmpKeywordList);
			} else {
				System.out.println("검색 결과 없음"); // recommendKKList가 null이 아닌
												// size 0이다!
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
			/*
			 * } else { System.out.println("검색 결과 없음"); // recommendKKList가
			 * null이 아닌 size 0이다! }
			 */

			System.out.println("recommendKKList.size(): " + recommendKKList.size());
			for (int i = 0; i < recommendKKList.size(); i++) {
				System.out.println(recommendKKList.get(i).getName() + " " + recommendKKList.get(i).getAddress() + " "
						+ recommendKKList.get(i).getStarRating());
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
		String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content " + "FROM kks, keywords kw, kk_keywords kkw "
				+ "WHERE trim(substr(kks.address, 4, 3)) = ? "
				+ "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
				+ "AND (kks.note not in('금일휴업', '임시휴무')) " + "ORDER BY kks.kk_id";
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
	 * public List<KKVO> getSearchKKList (String addressGu) { String sql =
	 * "SELECT kks.kk_id, kks.name, kks.address, kw.content " +
	 * "FROM kks, keywords kw, kk_keywords kkw " +
	 * "WHERE trim(substr(kks.address, 4, 3)) = ? " +
	 * "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) " +
	 * "AND (kks.note not in('금일휴업', '임시휴무')) " + "ORDER BY kks.kk_id";
	 * List<KKVO> resultKKList = new ArrayList<KKVO>(); KKVO vo = null;
	 * 
	 * try { PreparedStatement pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, addressGu); System.out.println("pstmt"); ResultSet rs
	 * = pstmt.executeQuery(); System.out.println(rs); int tmpKKId = 0; String
	 * tmpKKName = null; String tmpKKAddress = null; Collection<String>
	 * tmpKeywords = new ArrayList<String>(); // Collection<String> keywordList
	 * = new ArrayList<String>();
	 * 
	 * /* do { tmpKKId = rs.getInt(1); tmpKKName = rs.getString(2); tmpKKAddress
	 * = rs.getString(3); tmpKeywords.add(rs.getString(4)); } while(rs.next()) {
	 * if(tmpKKId != rs.getInt(1)) { Collection<String> keywordList = new
	 * ArrayList<String>(); for(String k : tmpKeywords) { keywordList.add(k); }
	 * tmpKeywords.clear(); vo = new KKVO(tmpKKId, tmpKKName, tmpKKAddress,
	 * keywordList); resultKKList.add(vo); keywordList.clear(); tmpKKId =
	 * rs.getInt(1); } else { tmpKKId = rs.getInt(1); tmpKKName =
	 * rs.getString(2); tmpKKAddress = rs.getString(3);
	 * tmpKeywords.add(rs.getString(4)); } // vo = new KKVO(rs.getInt(1),
	 * rs.getString(2), rs.getString(3), rs.getObject(4)); //
	 * recommendKKList.add(vo); } ///
	 * 
	 * System.out.println("추가조건X 노래방 검색결과 출력"); for(int i=0;
	 * i<resultKKList.size(); i++) {
	 * System.out.println(resultKKList.get(i).getKkId());
	 * System.out.println(resultKKList.get(i).getName());
	 * System.out.println(resultKKList.get(i).getAddress()); Collection<String>
	 * tmp = resultKKList.get(i).getRepresentativeKeywordList(); for(String k :
	 * tmp) { System.out.print(k + ", "); }
	 * System.out.println("------------------------"); }
	 * 
	 * } catch(SQLException e) { e.printStackTrace(); }
	 * 
	 * return resultKKList; }
	 */

	// 완료 - 추가조건O인 경우
	public List<String[]> getSearchKKList(String[] chkAdditionalOptions, int countChkOption, String addressGu) {
		String sql = "SELECT kks.kk_id, kks.name, kks.address, kw.content " + "FROM kks, keywords kw, kk_keywords kkw "
				+ "WHERE kks.kk_id IN (" + "SELECT kk_id " + "FROM kk_keywords " + "WHERE ((keyword_id=3 AND 1 = ?) "
				+ "OR (keyword_id=5 AND 1 = ?) " + "OR (keyword_id=6 AND 1 = ?) " + "OR (keyword_id=1 AND 1 = ?)) "
				+ "GROUP BY kk_id " + "HAVING COUNT(kk_id)=?) " + "AND trim(substr(kks.address, 4, 3)) = ? "
				+ "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) "
				+ "AND (kks.note NOT IN('금일휴업', '임시휴무')) " + "ORDER BY kks.kk_id";
		List<String[]> resultList = new ArrayList<String[]>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// System.out.println("추가조건 배열 크기: " + chkAdditionalOptions.length);
			System.out.println("===== KKDAO chkAdditionalOptions =====");
			System.out.println(chkAdditionalOptions.length);
			// String[] tmpArr = chkAdditionalOptions[0].split(",");
			if (chkAdditionalOptions.length == 1) {
				System.out.println("chkOptions 길이 1 if문");
				System.out.println(">> chkOptions[0]");
				System.out.println(chkAdditionalOptions[0]);
				String[] tmpArr = chkAdditionalOptions[0].split(",");
				for (int i = 0; i < tmpArr.length; i++) {
					// System.out.println(chkAdditionalOptions[i]);
					System.out.println(tmpArr[i]);
					/*
					 * pstmt.setString(1, tmpArr[0]); pstmt.setString(2,
					 * tmpArr[1]); pstmt.setString(3, tmpArr[2]);
					 * pstmt.setString(4, tmpArr[3]);
					 */
					pstmt.setInt(1, Integer.parseInt(tmpArr[0]));
					/*
					 * pstmt.setInt(2, Integer.parseInt(tmpArr[1]));
					 * pstmt.setInt(3, Integer.parseInt(tmpArr[2]));
					 * pstmt.setInt(4, Integer.parseInt(tmpArr[3]));
					 */
				}
			} else {
				System.out.println("chkOptions 길이 1 아님 else 문");
				System.out.println(chkAdditionalOptions.length);
				for (int i = 0; i < chkAdditionalOptions.length; i++) {
					System.out.println(chkAdditionalOptions[i]);
				}
				System.out.println("------------");
				/*
				 * pstmt.setString(1, chkAdditionalOptions[0]);
				 * pstmt.setString(2, chkAdditionalOptions[1]);
				 * pstmt.setString(3, chkAdditionalOptions[2]);
				 * pstmt.setString(4, chkAdditionalOptions[3]);
				 */
				pstmt.setInt(1, Integer.parseInt(chkAdditionalOptions[0]));
				pstmt.setInt(2, Integer.parseInt(chkAdditionalOptions[1]));
				pstmt.setInt(3, Integer.parseInt(chkAdditionalOptions[2]));
				pstmt.setInt(4, Integer.parseInt(chkAdditionalOptions[3]));
			}

			/*
			 * System.out.println("tmpArr 크기: " + tmpArr.length); for(int i=0; i
			 * < tmpArr.length; i++) { System.out.println(tmpArr[i]); }
			 * chkAdditionalOptions[0] = tmpArr[0]; /*
			 * 
			 * // System.out.println(
			 * "===== chkAdditionalOptions 0 1 2 3 출력 ====="); PreparedStatement
			 * pstmt = conn.prepareStatement(sql); for(int i=0; i<tmpArr.length;
			 * i++) { pstmt.setInt((i+1), Integer.parseInt(tmpArr[i])); }
			 * 
			 * /*pstmt.setString(1, tmpArr[0]); pstmt.setString(2, tmpArr[1]);
			 * pstmt.setString(3, tmpArr[2]); pstmt.setString(4, tmpArr[3]);
			 */

			/*
			 * System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
			 * System.out.println(chkAdditionalOptions[0]);
			 * System.out.println(chkAdditionalOptions[1]);
			 * System.out.println(chkAdditionalOptions[2]);
			 * System.out.println(chkAdditionalOptions[3]);
			 * System.out.println("~~~~~~~~~~~~~~~~~~~~~~"); pstmt.setInt(1,
			 * Integer.parseInt(chkAdditionalOptions[0])); pstmt.setInt(2,
			 * Integer.parseInt(chkAdditionalOptions[1])); pstmt.setInt(3,
			 * Integer.parseInt(chkAdditionalOptions[2])); pstmt.setInt(4,
			 * Integer.parseInt(chkAdditionalOptions[3]));
			 */
			/*
			 * pstmt.setInt(1, Integer.parseInt(tmpArr[0])); pstmt.setInt(2,
			 * Integer.parseInt(tmpArr[1])); pstmt.setInt(3,
			 * Integer.parseInt(tmpArr[2])); pstmt.setInt(4,
			 * Integer.parseInt(tmpArr[3]));
			 */
			pstmt.setInt(5, countChkOption);
			pstmt.setString(6, addressGu);
			ResultSet rs = pstmt.executeQuery();
			/*
			 * System.out.println("pstmt"); System.out.println(pstmt); ResultSet
			 * rs = pstmt.executeQuery(); System.out.println(rs);
			 */

			while (rs.next()) {
				System.out.println("while문");
				String[] tmp = new String[4];
				tmp[0] = Integer.toString(rs.getInt(1));
				tmp[1] = rs.getString(2);
				tmp[2] = rs.getString(3);
				tmp[3] = rs.getString(4);
				resultList.add(tmp);
			}

			/*
			 * for (int i = 0; i < resultList.size(); i++) { for (int j = 0; j <
			 * resultList.get(0).length; j++) {
			 * System.out.println(resultList.get(i)[j]); }
			 * System.out.println("----------------"); } System.out.println(
			 * "KKDAO 메서드 종료");
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}
	/*
	 * public List<String[]> getSearchKKList (int[] chkAdditionalOptions, int
	 * countChkOption, String addressGu) { String sql =
	 * "SELECT kks.kk_id, kks.name, kks.address, kw.content, kw.keyword_id " +
	 * "FROM kks, keywords kw, kk_keywords kkw " + "WHERE kks.kk_id IN (" +
	 * "SELECT kk_id " + "FROM kk_keywords " +
	 * "WHERE ((keyword_id=3 AND 1 = ?) " + "OR (keyword_id=5 AND 1 = ?) " +
	 * "OR (keyword_id=6 AND 1 = ?) " + "OR (keyword_id=1 AND 1 = ?)) " +
	 * "GROUP BY kk_id " + "HAVING COUNT(kk_id)=?) " +
	 * "AND trim(substr(kks.address, 4, 3)) = ? " +
	 * "AND (kks.kk_id = kkw.kk_id AND kkw.keyword_id = kw.keyword_id) " +
	 * "AND (kks.note NOT IN('금일휴업', '임시휴무')) " + "ORDER BY kks.kk_id";
	 * List<String[]> resultList = new ArrayList<String[]>(); try {
	 * PreparedStatement pstmt = conn.prepareStatement(sql); pstmt.setInt(1,
	 * chkAdditionalOptions[0]); pstmt.setInt(2, chkAdditionalOptions[1]);
	 * pstmt.setInt(3, chkAdditionalOptions[2]); pstmt.setInt(4,
	 * chkAdditionalOptions[3]); pstmt.setInt(5, countChkOption);
	 * pstmt.setString(6, addressGu); System.out.println("pstmt"); ResultSet rs
	 * = pstmt.executeQuery(); System.out.println(rs);
	 * 
	 * while (rs.next()) { System.out.println("while문"); String[] tmp = new
	 * String[3]; tmp[0] = Integer.toString(rs.getInt(1)); tmp[1] =
	 * rs.getString(2); tmp[2] = rs.getString(3); resultList.add(tmp); }
	 * 
	 * for (int i = 0; i < resultList.size(); i++) { for (int j = 0; j <
	 * resultList.get(0).length; j++) {
	 * System.out.println(resultList.get(i)[j]); }
	 * System.out.println("----------------"); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return resultList; }
	 */

	// 이 SQL문에서는 시간대 사이에 예약이 1건 끼어있는 경우에 대해서만 걸러주기 때문에, 짧은 예약이 여러 건 들어있는 경우는
	// 서비스에서 다시 검사를 요청해야한다.
	public List<KKVO> getCandidateKKListWithoutKeywords(String addressGu, LocalDateTime startTime,
			LocalDateTime endTime, int usingTime) {
		String sql = "select distinct kks.kk_id, kks.NAME, kks.OPENING_HOUR, kks.CLOSING_HOUR, kks.NOTE from kks, kks.ADDRESS"
				+ "Join room_infos rooms ON kks.kk_id = rooms.kk_id"
				+ "JOIN reservations r ON rooms.room_id = r.room_id" + "where kks.address like '%' || ? || '%'" // 1
				+ "and (" + "    --검색종료시간 < 예약시작시간 || 예약종료시간 < 검색시작시간" + "    --예약종료시간     <=      검색시작시간"
				+ "    r.end_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')" // 2
				+ "    -- 검색종료시간                       					  <= 예약시작시간"
				+ "    or TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.start_time" // 3
				+ "    -- 검색시작시간 < 예약시작시간 < 검색종료시간 < 예약시작시간"
				+ "    or (TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.start_time -- 검색시작시간 < 예약시작시간" // 4
				+ "    and r.start_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')   -- 예약시작시간 < 검색종료시간" // 5
				+ "    and TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.end_time    -- 검색종료시간 < 예약종료시간" // 6
				+ "    and round((r.start_time - TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))*24*60,2) >= ?) --예약시작시간 - 검색시작시간 > 이용시간(분단위)" // 7,
																																	// 8
				+ "    --예약시작시간 < 검색시작시간 < 예약종료시간 < 검색종료시간"
				+ "    or (r.start_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')-- 예약시작시간 < 검색시작시간" // 9
				+ "    and TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.end_time    -- 검색시작시간 < 예약종료시간" // 10
				+ "    and r.end_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')    -- 예약종료시간 < 검색종료시간 " // 11
				+ "    and round((TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') - r.end_time)*24*60,2) >= ?)" // 12,
																									// 13
				+ ");";
		List<KKVO> result = new ArrayList<KKVO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addressGu);
			pstmt.setString(2, LDT2D(startTime));
			pstmt.setString(3, LDT2D(endTime));
			pstmt.setString(4, LDT2D(startTime));
			pstmt.setString(5, LDT2D(endTime));
			pstmt.setString(6, LDT2D(endTime));
			pstmt.setString(7, LDT2D(startTime));
			pstmt.setInt(8, usingTime);
			pstmt.setString(9, LDT2D(startTime));
			pstmt.setString(10, LDT2D(startTime));
			pstmt.setString(11, LDT2D(endTime));
			pstmt.setString(12, LDT2D(endTime));
			pstmt.setInt(13, usingTime);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(new KKVO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(),
						rs.getTimestamp(4).toLocalDateTime(), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 키워드가 있는 경우
	public List<KKVO> getCandidateKKListWithKeywords(String addressGu, LocalDateTime startTime, LocalDateTime endTime,
			int usingTime, int[] keywords) {
		String sql = "select distinct kks.kk_id, kks.NAME, kks.OPENING_HOUR, kks.CLOSING_HOUR, kks.NOTE from kks, kks.ADDRESS"
				+ "Join room_infos rooms ON kks.kk_id = rooms.kk_id"
				+ "JOIN reservations r ON rooms.room_id = r.room_id where kks.address like '%' || ? || '%'" // 1
				+ "and kks.KK_ID in (select KKK.KK_ID from kk_keywords kkk INNER JOIN kks ON kkk.kk_id = kks.kk_id"
				+ "WHERE kks.address like '%' || ? || '%' and kkk.keyword_id in ? group by kkk.kk_id" // 2,3
				+ "having COUNT(KKK.KK_ID) >= ?) and (     --검색종료시간 < 예약시작시간 || 예약종료시간 < 검색시작시간" //4
				+ "    --예약종료시간     <=      검색시작시간" + "    r.end_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')" 
				+ "    -- 검색종료시간                       					  <= 예약시작시간"
				+ "    or TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.start_time" 
				+ "    -- 검색시작시간 < 예약시작시간 < 검색종료시간 < 예약시작시간"
				+ "    or (TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.start_time -- 검색시작시간 < 예약시작시간"
				+ "    and r.start_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')   -- 예약시작시간 < 검색종료시간"
				+ "    and TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.end_time    -- 검색종료시간 < 예약종료시간" 
				+ "    and round((r.start_time - TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))*24*60,2) >= ?) --예약시작시간 - 검색시작시간 > 이용시간(분단위)" 
				+ "    --예약시작시간 < 검색시작시간 < 예약종료시간 < 검색종료시간"
				+ "    or (r.start_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')-- 예약시작시간 < 검색시작시간"
				+ "    and TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') <= r.end_time    -- 검색시작시간 < 예약종료시간"
				+ "    and r.end_time <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')    -- 예약종료시간 < 검색종료시간 "
				+ "    and round((TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') - r.end_time)*24*60,2) >= ?)" 
				+ ");";
		List<KKVO> result = new ArrayList<KKVO>();
		String keywordsString = "(";
		for (int i = 0; i < keywords.length; i++) {
			if(i == 0){
				keywordsString += keywords[i];
			}else{
				keywordsString += ", " + keywords[i]; 
			}
		}
		keywordsString += ")";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addressGu);
			pstmt.setString(2, addressGu);
			pstmt.setString(3, keywordsString);
			pstmt.setInt(4, keywords.length);
			pstmt.setString(5, LDT2D(startTime));
			pstmt.setString(6, LDT2D(endTime));
			pstmt.setString(7, LDT2D(startTime));
			pstmt.setString(8, LDT2D(endTime));
			pstmt.setString(9, LDT2D(endTime));
			pstmt.setString(10, LDT2D(startTime));
			pstmt.setInt(11, usingTime);
			pstmt.setString(12, LDT2D(startTime));
			pstmt.setString(13, LDT2D(startTime));
			pstmt.setString(14, LDT2D(endTime));
			pstmt.setString(15, LDT2D(endTime));
			pstmt.setInt(16, usingTime);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(new KKVO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(),
						rs.getTimestamp(4).toLocalDateTime(), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// LocalDateTime을 "YYYY-MM-DD HH24:MI:SS" 형식의 문자열로 포맷해준다.
	private String LDT2D(LocalDateTime ldt) {
		String result = ldt.toString();
		result = result.split("\\.")[0];
		result = result.replace('T', ' ');

		return result;
	}

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
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				roomInfoList.put(Integer.valueOf(rs.getInt(1)), rs.getString(2));
			}

			// System.out.println("해당 노래방의 방 정보 불러오기");
			// for (Entry<Integer, String> entrySet : roomInfoList.entrySet()) {
			// System.out.println(entrySet.getKey() + " - " +
			// entrySet.getValue());
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roomInfoList;
	}

}
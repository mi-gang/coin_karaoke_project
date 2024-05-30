package com.oopsw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.PlaylistDAO;
import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.dao.ReviewDAO;
import com.oopsw.model.dao.UserDAO;
import com.oopsw.model.vo.KKVO;
import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.model.vo.ReviewVO;

public class ReservationService {
	public Connection conn;

	public ReservationService() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ReservationDAO reservationDAO = null;
	KKDAO kkDAO = null;
	UserDAO dao = new UserDAO(conn);

	/** 사용자의 가장 최근 예약 일정 불러오기 */
	public ReservationVO getUpcomingReservation(String userId) {

		ReservationVO reservationVO = new ReservationDAO(conn).getUpcomingReservation(userId);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVO;
	}

	/** 해당 노래방의 방, 예약 현황 정보 불러오기 */
	public List<ReservationRoomInfoVO> getRoomReservationStatusList(int kkId) {

		List<ReservationRoomInfoVO> reservationRoomInfoVOs = new ArrayList<ReservationRoomInfoVO>();
		List<ReservationVO> reservationVOs = null;

		Map<Integer, String> roomInfoList = new HashMap<Integer, String>();
		roomInfoList = new KKDAO(conn).getRoomInfoList(kkId);
		// 리턴값 : room_id, name(방 이름)

		for (Entry<Integer, String> entrySet : roomInfoList.entrySet()) {

			int roomId = entrySet.getKey();
			String roomName = entrySet.getValue();

			// roomId별 예약 현황 정보 불러오기
			reservationVOs = new ReservationDAO(conn).getReservationListByRoomId(roomId);
			reservationRoomInfoVOs.add(new ReservationRoomInfoVO(roomId, roomName, reservationVOs));
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationRoomInfoVOs;
	}

	/** 예약 가능 시간인지 검증하기 */
	public boolean isValidTimeForReservation(LocalDateTime startTime, LocalDateTime endTime, int roomId) {

		boolean result = false;

		result = new ReservationDAO(conn).isValidTimeForReservation(startTime, endTime, roomId);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 예약내역 중 이용 중/예정 내역 불러오기 */
	public Collection<ReservationVO> getUncompletedReservationList(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		reservationVOs = new ReservationDAO(conn).getUncompletedReservationList(userId);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVOs;
	}

	/** 예약내역 중 이용 완료 내역 불러오기 */
	public Collection<completedReservationVO> getCompletedReservationList(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		Collection<completedReservationVO> completedReservationVOs = new ArrayList<>();

		reservationVOs = new ReservationDAO(conn).getCompletedReservationList(userId);

		// 리뷰 작성 여부 / 작성 가능 여부 불러오기
		for (ReservationVO reservationVO : reservationVOs) {

			boolean result = new ReservationDAO(conn).isReviewWritten(userId, reservationVO.getReservationId());

			System.out.println(result);
			if (result) {
				completedReservationVOs.add(new completedReservationVO(reservationVO, result));
			} else {
				completedReservationVOs.add(new completedReservationVO(reservationVO, false));
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return completedReservationVOs;
	}

	/** 예약내역 중 취소 내역 불러오기 */
	public Collection<ReservationVO> getCanceledReservationList(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		reservationVOs = new ReservationDAO(conn).getCanceledReservationList(userId);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVOs;
	}

	/** 기존 이용 시간, 추가 가능 시간 불러오기 */
	public AdditionalTimeInfoVO getAdditionalTimeInfo(String userId, int reservationId) {

		ReservationVO reservationVO = null;
		ReservationVO reservationVO2 = null;

		reservationVO = new ReservationDAO(conn).getOriginalReservationTime(userId, reservationId);

		LocalDateTime startTime = reservationVO.getStartTime();
		LocalDateTime endTime = reservationVO.getEndTime();
		int roomId = reservationVO.getRoomId();
		// 리턴 : start_time, end_time, roomId

		reservationVO2 = new ReservationDAO(conn).getAvailableExtraUsingTime(roomId, endTime);
		// 리턴 : reservation_id, start_time -> starttime만 씀

		// 불러온 시작 시건에서 기존 에약의 end date를 빼서 남은 시간 게산해서 보내기
		// int availableTime = reservationVO2.getStartTime();
		Duration diff = Duration.between(reservationVO2.getStartTime(), endTime);
		int availableTime = (int) diff.toMinutes();
		int availableHour = availableTime / 60;
		int availableMinute = availableTime % 60;

		// 기존 이용 시간을 보내야하는가 ????? ㄴㄴ

		// 성인 유무 같이 보내기
		boolean isAdult = false;
		try {
			isAdult = new UserDAO(conn).isAdult(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// UserDAO dao = new UserDAO();
		// start_time, end_time, availableTime, 성인유무 을 보내야 함
		AdditionalTimeInfoVO aTimeInfoVO = new AdditionalTimeInfoVO(startTime, endTime, availableHour, availableMinute,
				isAdult);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aTimeInfoVO;
	}

	/** 추가가능시간 분 단위로 알려주기 */
	public int getAddableMinutes(String userId, int reservationId) {
		ReservationDAO rDao = new ReservationDAO(conn);
		ReservationVO reservationVO = null;
		reservationVO = rDao.getOriginalReservationTime(userId, reservationId);
		LocalDateTime endTime = reservationVO.getEndTime();
		int availableMinutes = 60 * 24; //아무 예약이 없으면 24시간까지 추가 가능
		
		LocalDateTime upcomingReservationTime = rDao.getUpcomingReservationByReservationId(reservationId);
		if(upcomingReservationTime != null){
			// 불러온 시작 시건에서 기존 에약의 end date를 빼서 남은 시간 게산해서 보내기
			Duration diff = Duration.between(endTime, upcomingReservationTime);
			availableMinutes = (int) diff.toMinutes() - 15;// TODO: 청소시간 15분 상수로 바꾸기
		}

		// 성인 유무 같이 보내기
		boolean isAdult = false;
		try {
			isAdult = new UserDAO(conn).isAdult(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LocalDateTime maxEndTime = endTime.plusMinutes(availableMinutes);
		LocalDateTime limitTime = LocalDateTime.of(endTime.toLocalDate(), LocalTime.of(22, 0));
		// TODO: 청소년 이용불가시간: 22~08시. 상수로 바꿀것.
		// 청소년인 경우 22시까지만 놀 수 있는 추가시간으로 재설정
		if (!isAdult && maxEndTime.isAfter(limitTime)) {

			availableMinutes = (int) Duration.between(endTime, limitTime).toMinutes();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return availableMinutes;
	}

	/** 예약하기 */
	public boolean addReservation(ReservationVO reservationVO) {

		boolean result = false;

		result = new ReservationDAO(conn).addReservation(reservationVO);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 기존 예약에 시간 추가하기 */
	public boolean payAdditionalTime(LocalDateTime endTime, int reservationId) {

		boolean result = false;

		result = new ReservationDAO(conn).updateReservation(endTime, reservationId);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/** 예약 취소하기 */
	public boolean cancelReservation(String userId, int reservationId) {

		boolean result = false;

		result = new ReservationDAO(conn).cancelReservation(userId, reservationId);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean addAdditionalReservation(String userId, int reservationId, int additionalTime) {
		boolean result = false;
		int realAddableMinutes = getAddableMinutes(userId, reservationId);
		// 유효성검사 보류
		if (additionalTime > realAddableMinutes) {
			return false;
		}
		LocalDateTime endTime = new ReservationDAO(conn).getOriginalReservationTime(userId, reservationId).getEndTime();
		result = new ReservationDAO(conn).updateReservation(endTime.plusMinutes(additionalTime), reservationId);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
<<<<<<< HEAD
=======

>>>>>>> mypageBE
	/** 마이페이지 */
	public myPageVO myPageInfo(String userId) {

		// 닉네임
		String nickname = null;
		try {
			nickname = new UserDAO(conn).getNickname(userId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// 저장된 노래방
		Collection<String> keywordList = new ArrayList<String>();
		List<String[]> tmpKKList = new KKDAO(conn).getMypageBookmarkKK(userId);

		System.out.println(tmpKKList);

		int kkId = Integer.parseInt(tmpKKList.get(0)[0]);
		String name = tmpKKList.get(0)[1];
		String address = tmpKKList.get(0)[2];
		float starRating = new ReservationDAO(conn).getStarAvgByKKId(kkId);

		if (tmpKKList.size() > 1) {
			keywordList = new ArrayList<String>();
			keywordList.add(tmpKKList.get(0)[3]);

			for (int i = 1; i < tmpKKList.size(); i++) {
				keywordList.add(tmpKKList.get(i)[3]);
			}
		}
		KKVO kkVO = new KKVO(kkId, name, address, starRating, keywordList);
		int bookmarkCount = new KKDAO(conn).getBookmarkCount(userId);

		// 플레이리스트
		Collection<PlaylistVO> playlistVOs = new PlaylistDAO(conn).getmypagePlaylist(userId);
		int playlistCount = new PlaylistDAO(conn).getPlaylistCount(userId);

		// 리뷰
		ReviewVO reviewVO = new ReviewDAO(conn).getReviewByUserId(userId);
		int reviewCount = new ReviewDAO(conn).getReviewCount(userId);

		myPageVO myPageVO = new myPageVO(nickname, kkVO, bookmarkCount, reviewVO, reviewCount, playlistVOs,
				playlistCount);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myPageVO;
	}

}

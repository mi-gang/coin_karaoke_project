package com.oopsw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.dao.UserDAO;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.model.vo.RoomInfoVO;

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
	UserDAO dao = new UserDAO();
	
	

	/** 사용자의 가장 최근 예약 일정 불러오기 */
	public Collection<ReservationVO> getUpcomingReservation(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();
		reservationVOs = new ReservationDAO(conn).getUpcomingReservation(userId);
		return reservationVOs;
	}

	/** 해당 노래방의 방, 예약 현황 정보 불러오기 */
	public Collection<ReservationRoomInfoVO> getroomReservationStatusList(int kkId) {

		Collection<ReservationRoomInfoVO> reservationRoomInfoVOs = new ArrayList<>();
		RoomInfoVO roomInfoVO = null;
		// Map<Integer, String>

		// roomInfoVO = new KKDAO(conn).getRoomInfoList(kkId);
		// 리턴값 : room_id, name(방 이름)
		// reservationVOs = new ReservationDAO(conn).getReservationListByRoomId(roomId);
		// 리턴값 : s.reservation_id, s.start_time, s.end_time
		// 돌려줄 때 reservationVO + 방 이름 VO가 필요함

		// 정보 받아서 병합

		// reservationVOs = reservationDAO(conn).getUpcomingReservation(userId);

		return reservationRoomInfoVOs;
	}

	/** 예약 가능 시간인지 검증하기 */
	public boolean isValidTimeForReservation(ReservationVO reservationVO) {

		boolean result = false;

		result = new ReservationDAO(conn).isValidTimeForReservation(reservationVO);

		return result;
	}

	/** 예약내역 중 이용 중/예정 내역 불러오기 */
	public Collection<ReservationVO> getUncompletedReservationList(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		reservationVOs = new ReservationDAO(conn).getUncompletedReservationList(userId);

		return reservationVOs;
	}

	/** 예약내역 중 이용 완료 내역 불러오기 */
	public Collection<ReservationVO> getCompletedReservationList(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		reservationVOs = new ReservationDAO(conn).getCompletedReservationList(userId);

		return reservationVOs;
	}

	/** 예약내역 중 취소 내역 불러오기 */
	public Collection<ReservationVO> getCanceledReservationList(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		reservationVOs = new ReservationDAO(conn).getCanceledReservationList(userId);

		return reservationVOs;
	}

	/** 기존 이용 시간, 추가 가능 시간 불러오기 */
	public ReservationVO additionalTimeStatus(String userId, int reservationId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();
		
		ReservationVO reservationVO = null;
		ReservationVO reservationVO2 = null;
		
		reservationVO = new ReservationDAO(conn).getOriginalReservationTime(userId, reservationId);
		// 리턴 : start_time, end_time
		reservationVO2 = new ReservationDAO(conn).getAvailableExtraUsingTime(reservationVO.getRoomId(), reservationVO.getEndTime());
		// 리턴 : reservation_id, start_time
		
		// 불러온 시작 시건에서 기존 에약의 end date를 빼서 남은 시간 게산해서 보내기
		// int availableTime = reservationVO2.getStartTime();
		
		
		// 기존 이용 시간을 보내야하는가 ?????
		
		// 성인 유무 같이 보내기 
		boolean isAdult = false;
		try {
			isAdult = new UserDAO(conn).isAdult(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// UserDAO dao = new UserDAO();
		// reservationId, start_time, end_time, availableTime, 성인유무 을 보내야 함

		return reservationVO2;
	}
	
	/** 기존 예약에 시간 추가하기 */
	public boolean payAdditionalTime(LocalDateTime endTime, int reservationId) {

		boolean result = false;

		result = new ReservationDAO(conn).updateReservation(endTime, reservationId);

		return result;
	}
	
	
	/** 예약 취소하기 */
	public boolean cancelReservation(int reservationId) {

		boolean result = false;

		result = new ReservationDAO(conn).cancelReservation(reservationId);

		return result;
	}

}

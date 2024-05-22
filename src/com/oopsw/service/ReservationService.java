package com.oopsw.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.model.vo.RoomInfoVO;

public class ReservationService {
	
	public Connection conn;
	
	public ReservationService(){
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

	/** 사용자의 가장 최근 예약 일정 불러오기 */
	public Collection<ReservationVO> getUpcomingReservation(String userId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();
		reservationVOs = reservationDAO.getUpcomingReservation(userId);
		return reservationVOs;
	}
	
	/** 해당 노래방의 방, 예약 현황 정보 불러오기 */
	public Collection<ReservationVO> getroomReservationStatusList(int kkId) {

		Collection<ReservationVO> reservationVOs = new ArrayList<>();
		RoomInfoVO roomInfoVO = null;
//		Map<Integer, String>
		
		// roomInfoVO = new KKDAO(conn).getRoomInfoList(kkId);
		// 리턴값 : room_id, name(방 이름)
		reservationVOs = new ReservationDAO(conn).getReservationListByRoomId(roomId);
		// 리턴값 : s.reservation_id, s.start_time, s.end_time
		// 돌려줄 때 reservationVO + 방 이름 VO가 필요함
		
		reservationVOs = reservationDAO(conn).getUpcomingReservation(userId);

		return reservationVOs;
	}

	public Collection<ReservationVO> getUncompletedReservationList() {
		return null;
	}

}

package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.oopsw.model.vo.ReservationVO;
import com.oopsw.model.vo.RoomInfoVO;

public class ReservationDAO {

	private Connection conn;

	public ReservationDAO(Connection conn) {
		this.conn = conn;
	}

	// 메인페이지
	/** 현재 시각 기준(1시간) 이용 중인 예약 정보 불러오기 */
	public Collection<ReservationVO> getOccupiedReservationList(RoomInfoVO roomInfoVO) {

		// 나중에 수정해야함
		// 보내는 거 KK_id, start_time, end_time
		// 받는 거 reservation_id, start_time, end_time, room_id
		String sql = "SELECT r.reservation_id, r.start_time, r.end_time, r.room_id FROM reservations r "
				+ "JOIN room_infos ri ON r.room_id = ri.room_id JOIN KKs k ON ri.KK_id = k.KK_id "
				+ "WHERE (k.KK_id = ? AND r.start_time BETWEEN TO_DATE('2024-04-20 19:00', 'YYYY-MM-DD HH24:MI') "
				+ "AND TO_DATE('2024-04-20 20:00', 'YYYY-MM-DD HH24:MI')) OR (k.KK_id = ? "
				+ "AND r.end_time BETWEEN TO_DATE('2024-04-20 19:00', 'YYYY-MM-DD HH24:MI') "
				+ "AND TO_DATE('2024-04-20 20:00', 'YYYY-MM-DD HH24:MI'))";

		Collection<ReservationVO> reservationVOs = new ArrayList<ReservationVO>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, roomInfoVO.getRoomId());
			pstmt.setInt(2, roomInfoVO.getKKId());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					reservationVOs.add(new ReservationVO(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(),
							rs.getTimestamp(3).toLocalDateTime(), roomInfoVO.getRoomId()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationVOs;
	}

	/** 사용자의 가장 최근 예약 일정 불러오기 */
	public Collection<ReservationVO> getUpcomingReservation(String userId) {

		String sql = "select reservation_id, is_cancel, start_time, end_time, k.KK_id, k.name from (select * from RESERVATIONS where "
				+ "user_id = ? and start_time > sysdate and is_cancel = 0 order by start_time asc) "
				+ "temp, room_infos r, KKs k where temp.room_id = r.room_id and r.KK_id = k.KK_id and rownum = 1";

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reservationVOs.add(new ReservationVO(rs.getInt(1), rs.getInt(2),
							rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(), userId,
							rs.getInt(5), rs.getInt(6), rs.getString(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVOs;
	}

	// 예약하기
	/** 예약 현황 정보 불러오기 */
	public Collection<ReservationVO> getReservationListByRoomId(RoomInfoVO roomInfoVO) {

		String sql = "select s.reservation_id, s.start_time, s.end_time from reservations s, room_infos r "
				+ "where s.room_id=? AND r.KK_id=? AND s.room_id=r.room_id AND IS_CANCEL = 0 "
				+ "and s.end_time > sysdate order by s.start_time";

		Collection<ReservationVO> reservationVOs = new ArrayList<ReservationVO>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, roomInfoVO.getRoomId());
			pstmt.setInt(2, roomInfoVO.getKKId());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					reservationVOs.add(new ReservationVO(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(),
							rs.getTimestamp(3).toLocalDateTime(), roomInfoVO.getRoomId()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationVOs;
	}

	/** 예약 가능 시간 검증하기 */
	public boolean isValidTimeForReservation(ReservationVO reservationVO) {

		String sql = "select count(*) from reservations  where not ( "
				+ "start_time >= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') or "
				+ "end_time <= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')) and room_id=?";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setTimestamp(1, Timestamp.valueOf(reservationVO.getEndTime()));
			pstmt.setTimestamp(2, Timestamp.valueOf(reservationVO.getStartTime()));
			pstmt.setInt(3, reservationVO.getRoomId());

			int num = pstmt.executeUpdate();
			if (num > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 예약내역 중 이용 중/예정 내역 불러오기 */
	public Collection<ReservationVO> getUncompletedReservationList(String userId) {

		String sql = "select r.reservation_id, r.is_cancel, r.start_time, r.end_time, k.KK_id, k.name "
				+ "from reservations r, room_infos ro, KKs k where r.room_id = ro.room_id "
				+ "and ro.KK_id = k.KK_id and user_id=? and is_cancel = 1;";

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reservationVOs.add(new ReservationVO(rs.getInt(1), rs.getInt(2),
							rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(), userId,
							rs.getInt(5), rs.getInt(6), rs.getString(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVOs;
	}

	/** 예약내역 중 이용 완료 내역 불러오기 */
	public Collection<ReservationVO> getCompletedReservationList(String userId) {

		String sql = "select reservation_id, is_cancel, start_time, end_time, room_Id, k.KK_id, k.name "
				+ "from RESERVATIONS where user_id=? and END_TIME < sysdate and IS_CANCEL = 0";

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reservationVOs.add(new ReservationVO(rs.getInt(1), rs.getInt(2),
							rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(), userId,
							rs.getInt(5), rs.getInt(6), rs.getString(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVOs;
	}

	/** 예약내역 중 취소 내역 불러오기 */
	public Collection<ReservationVO> getCanceledReservationList(String userId) {

		String sql = "select reservation_id, is_cancel, start_time, end_time, room_Id, k.KK_id, k.name "
				+ "from RESERVATIONS where user_id=? and IS_CANCEL = 1";

		Collection<ReservationVO> reservationVOs = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reservationVOs.add(new ReservationVO(rs.getInt(1), rs.getInt(2),
							rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(), userId,
							rs.getInt(5), rs.getInt(6), rs.getString(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVOs;
	}

	/** 기존 이용 시간 불러오기 */
	public ReservationVO getOriginalReservationTime(String userId, int reservationId) {

		String sql = "SELECT start_time, end_time FROM reservations r " + "WHERE r.user_id=? AND r.reservation_id=?";

		ReservationVO reservationVO = null;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, reservationId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					reservationVO = new ReservationVO(rs.getTimestamp(1).toLocalDateTime(),
							rs.getTimestamp(2).toLocalDateTime());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationVO;
	}

	// 15분 처리 -> SERVICE 에서
	/** 추가 가능 시간 불러오기 */
	public ReservationVO getAvailableExtraUsingTime(ReservationVO reservationVO) {

		String sql = "SELECT reservation_id, start_time FROM (select * FROM reservations r, room_infos ri "
				+ "WHERE r.room_id = ri.room_id AND r.room_id = ? "
				+ "AND r.start_time > TO_DATE(?,'YYYY-MM-DD HH24:MI') AND r.IS_CANCEL = 0"
				+ "ORDER BY r.start_time) WHERE rownum = 1";

		ReservationVO newReservationVO = null;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reservationVO.getRoomId());
			pstmt.setTimestamp(2, Timestamp.valueOf(reservationVO.getEndTime()));
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					newReservationVO = new ReservationVO(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newReservationVO;
	}

	/** 예약하기 */
	public boolean addReservation(ReservationVO reservationVO) {

		String sql = "Insert into RESERVATIONS (reservation_id, is_cancel, start_time, end_time, "
				+ "user_id, room_id) values (reservation_id_seq.nextval, 0, " + "TO_DATE(?,'YYYY-MM-DD HH24:MI'), "
				+ "TO_DATE(?,'YYYY-MM-DD HH24:MI'), ?, ?)";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setTimestamp(1, Timestamp.valueOf(reservationVO.getStartTime()));
			pstmt.setTimestamp(2, Timestamp.valueOf(reservationVO.getEndTime()));
			pstmt.setString(3, reservationVO.getUserId());
			pstmt.setInt(4, reservationVO.getRoomId());

			int num = pstmt.executeUpdate();
			if (num == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 기존 예약에 시간 추가하기 */
	public boolean updateReservation(ReservationVO reservationVO) {

		String sql = "update reservations set end_time = TO_DATE(?,'YYYY-MM-DD HH24:MI') " + "where reservation_id = ?";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setTimestamp(1, Timestamp.valueOf(reservationVO.getEndTime()));
			pstmt.setInt(2, reservationVO.getReservationId());

			int num = pstmt.executeUpdate();
			if (num == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 예약 취소하기 */
	public boolean cancelReservation(int reservationId) {

		String sql = "update reservations set is_cancel=1 where reservation_id=?";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reservationId);

			int num = pstmt.executeUpdate();
			if (num == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

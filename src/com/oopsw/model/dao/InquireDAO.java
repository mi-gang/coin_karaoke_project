package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.oopsw.model.vo.InquireVO;

public class InquireDAO {

	private Connection conn;

	public InquireDAO(Connection conn) {
		this.conn = conn;
	}

	/** 문의/신고 작성하기 */
	public boolean addInquire(InquireVO inquireVO) {

		String sql = "INSERT into inquires (inquire_id, content, reservation_id, "
				+ "inquire_date) values (inquire_id_seq.nextval, ?, ?, sysdate)";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, inquireVO.getContent());
			pstmt.setInt(2, inquireVO.getReservationId());

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

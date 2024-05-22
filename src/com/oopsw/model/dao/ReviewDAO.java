package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.oopsw.model.vo.ReservationVO;
import com.oopsw.model.vo.ReviewVO;

public class ReviewDAO {
    	private Connection conn;

	public ReviewDAO(Connection conn) {
		this.conn = conn;
	}

    // 리뷰애 출력할 정보를 다시 확인할 필요가 있음 ..
    public Collection<ReviewVO> getReviewListByKKId(int KKId){
        
        String sql = "select review_id, content, star, start_time, end_time, name from "
        + "(SELECT * from reviews rev, reservations res, KKs k where rev.RESERVATION_ID = res.RESERVATION_ID "
        + "and k.KK_ID = ? order by res.END_TIME desc) where rownum >= 1 and rownum <= 10";

        Collection<ReviewVO> reviewVOs = new ArrayList<ReviewVO>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, KKId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reviewVOs.add(new ReservationVO(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(),
							rs.getTimestamp(3).toLocalDateTime(), roomInfoVO.getRoomId()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewVOs;
    }

	/** 리뷰 작성하기 */
	public boolean addReview(ReviewVO reviewVO) {

		String sql = "INSERT into reviews (review_id, content, star, reservation_id, review_date) "
		+ "values (review_id_seq.nextval, ?, ?, ?, sysdate)";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, reviewVO.getContent());
			pstmt.setFloat(2, reviewVO.getStar());
			pstmt.setInt(3, reviewVO.getReservationId());

			int num = pstmt.executeUpdate();
			if (num == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**리뷰 삭제하기 */
	public boolean addReview(int reviewId) {

		String sql = "delete from reviews where review_id = ?";

		boolean result = false;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reviewId);

			int num = pstmt.executeUpdate();
			if (num == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 나의 리뷰 리스트 불러오기 */
	public Collection<ReviewVO> getReviewListByUserId(int userId){
        
        String sql = "select review_id, content, star, start_time, end_time, name from "
			+ "(SELECT * from reviews rev, reservations res, KKs k where rev.RESERVATION_ID = res.RESERVATION_ID "
			+ "and res.USER_ID = ? order by rev.review_date desc) where rownum > 0 and rownum <= 10";

        Collection<ReviewVO> reviewVOs = new ArrayList<ReviewVO>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reviewVOs.add(new ReservationVO(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(),
							rs.getTimestamp(3).toLocalDateTime(), roomInfoVO.getRoomId()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewVOs;
    }
}
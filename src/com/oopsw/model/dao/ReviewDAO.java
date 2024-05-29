package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.oopsw.model.vo.ReviewVO;

public class ReviewDAO {
	private Connection conn;

	public ReviewDAO(Connection conn) {
		this.conn = conn;
	}

	// sql 체크 필요
	/** 해당 노래방 리뷰 불러오기 */
	public Collection<ReviewVO> getReviewListByKKId(int KKId) {

		String sql = "select distinct r.review_id, r.content, u.nickname "
				+ "from (SELECT * from reviews rev, reservations res, KKs k, room_infos ri "
				+ "where (k.kk_id=?) and (k.kk_id=ri.kk_id AND ri.room_id=res.room_id AND res.reservation_id=rev.reservation_id) "
				+ "order by res.END_TIME desc), kks, room_infos ri, reviews r, reservations rv, users u "
				+ "where rownum >= 1 and rownum <= 10 "
				+ "AND(kks.kk_id=ri.kk_id AND ri.room_id=rv.room_id AND rv.reservation_id=r.reservation_id AND u.user_id=rv.user_id)";

		Collection<ReviewVO> reviewVOs = new ArrayList<ReviewVO>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, KKId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				reviewVOs.add(new ReviewVO(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			System.out.println(">>>> ReviewDAO의 reviewVOs size()");
			System.out.println(reviewVOs.size());
			/*try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reviewVOs.add(new ReviewVO(rs.getInt(1), rs.getString(2), rs.getString(3)));
				}
				System.out.println(">>>> ReviewDAO의 reviewVOs size()");
				System.out.println(reviewVOs.size());
			}*/
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

	/** 리뷰 삭제하기 */
	public boolean deleteReview(int reviewId) {

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
	public Collection<ReviewVO> getReviewListByUserId(String userId) {

		String sql = "SELECT review_id, content, star, start_time, end_time, name "
				+ "FROM (SELECT rev.review_id, rev.content, rev.star, res.start_time, "
				+ "res.end_time, k.name, ROWNUM rnum "
				+ "FROM reviews rev "
				+ "JOIN reservations res ON rev.RESERVATION_ID = res.RESERVATION_ID "
				+ "JOIN room_infos ri ON res.ROOM_ID = ri.ROOM_ID "
				+ "JOIN KKs k ON ri.KK_ID = k.KK_ID "
				+ "WHERE res.USER_ID = ? "
				+ "ORDER BY rev.review_date DESC) "
				+ "WHERE rnum > 0 AND rnum <= 10";

		Collection<ReviewVO> reviewVOs = new ArrayList<ReviewVO>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					reviewVOs.add(new ReviewVO(rs.getInt(1), rs.getString(2), rs.getFloat(3),
							rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime(),
							rs.getString(6)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewVOs;
	}
}
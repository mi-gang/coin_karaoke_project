package com.oopsw.model.dao;

import java.sql.Connection;
import java.util.Collection;

import com.oopsw.model.vo.ReviewVO;

public class ReviewDAO {
    	private Connection conn;

	public ReviewDAO(Connection conn) {
		this.conn = conn;
	}

    public Collection<ReviewVO> getReviewListByKKId(int KKId){
        
        String sql = "select review_id, content, star, start_time, end_time, name from "
        + "(SELECT * from reviews rev, reservations res, KKs k where rev.RESERVATION_ID = res.RESERVATION_ID "
        + "and k.KK_ID = 1 order by res.END_TIME desc) where rownum >= 1 and rownum <= 10";
        
        return null;
    }
	
}

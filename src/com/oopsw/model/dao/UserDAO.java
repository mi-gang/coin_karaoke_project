package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.function.IntPredicate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oopsw.model.vo.UserVO;

public class UserDAO {
	private Connection conn;

	public UserDAO() {
		Context context;
		try {
			context = new InitialContext();
			DataSource dataSource =
					(DataSource) context.lookup("java:comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

	}

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean login(String userId, String encryptedPassword) throws SQLException {
		boolean result = false;
		String sql = "SELECT nickname from users where user_id = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, encryptedPassword);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			result = true;
		return result;
	}
	
	public boolean addUser(UserVO user) throws SQLException{
		boolean result = false;
		String sql = "INSERT INTO users (user_id, nickname, birth_date, password) values (?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		pstmt.setString(i++, user.getUserId());
		pstmt.setString(i++, user.getNickname());
		pstmt.setDate(i++, Date.valueOf(user.getBirthDate().toLocalDate()));
		pstmt.setString(i++, user.getPassword());
		int rs = pstmt.executeUpdate();
		if(rs == 1)
			result = true;
		return result;
	}
	
	public UserVO getUser(String userId) throws SQLException{
		UserVO user = null;
		String sql = "SELECT nickname, birth_date from users where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		pstmt.setString(i++, userId);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			user = new UserVO(userId, rs.getString(1), rs.getTimestamp(2).toLocalDateTime() ,null);
		return user;
	}

	public boolean isExistEmailTest(String userId) throws SQLException {
		boolean result = false;
		String sql = "SELECT nickname from users where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			result = true;
		return result;
	}

	public boolean updatePassword(String userId, String newEncryptedPassword) throws SQLException {
		boolean result = false;
		String sql = "UPDATE users set password=? where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		pstmt.setString(i++, newEncryptedPassword);
		pstmt.setString(i++, userId);
		
		int rs = pstmt.executeUpdate();
		if(rs == 1)
			result = true;
		return result;
	}

	public boolean updateNickname(String userId, String newNickname) throws SQLException {
		boolean result = false;
		String sql = "UPDATE users set nickname=? where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		pstmt.setString(i++, newNickname);
		pstmt.setString(i++, userId);
		
		int rs = pstmt.executeUpdate();
		if(rs == 1)
			result = true;
		return result;
	}
	
	public String getNickname(String userId) throws SQLException{
		String nickname = null;
		String sql = "SELECT nickname from users where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		pstmt.setString(i++, userId);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			nickname = rs.getString(1);
		return nickname;
	}
}

package com.oopsw.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.UserDAO;
import com.oopsw.model.vo.UserVO;

public class UserService {
	
	private Connection getConnection(){
		Connection conn = null;
		Context context;
		try {
			context = new InitialContext();
			DataSource dataSource =
					(DataSource) context.lookup("java:comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException | SQLException e1) {
			System.err.println("커넥션 풀  실패. 수동으로 전환");
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
				conn = DriverManager.getConnection(url, "hr", "hr");
				conn.setAutoCommit(false);
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("수동 커넥션 생성 실패. 서비스 종료.");
				System.exit(404);
			}
			
		}
		
		return conn;
	}

	public boolean isKKBookmark(String userId, int KK_ID) {
		Connection conn = getConnection();
		boolean result = new KKDAO(conn).isKKBookmark(userId, KK_ID);
		try {
			conn.close();
		} catch (SQLException e) {
		}
		return result;
	}

	public boolean addKKBookmark(String userId, int KK_ID) {
		Connection conn = getConnection();
		boolean result = new KKDAO(conn).addKKBookmark(userId, KK_ID);
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// 서버-DB 네트워크 통신 문제로 커밋 실패한경우.
			// 커넥션 연결 실패도 포함될듯?
		}
		return result;

	}

	public boolean deleteKKBookmark(String userId, int KK_ID) {
		Connection conn = getConnection();
		boolean result = new KKDAO(conn).deleteKKBookmark(userId, KK_ID);
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// 서버-DB 네트워크 통신 문제로 커밋 실패한경우.
			// 커넥션 연결 실패도 포함될듯?
		}
		return result;

	}

	public boolean login(String userId, String password) {
		Connection conn = getConnection();
		boolean result = false;
		try {
			String encryptedPassword = getEncryptedPassword(userId, password);
			result = new UserDAO(conn).login(userId, encryptedPassword);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getEncryptedPassword(String userId, String password) {
		MessageDigest md = null;
		String saltedPassword = userId + password + "salted";
		
		String encryptedPassword = bytesToHex(saltedPassword.getBytes());
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(saltedPassword.getBytes("UTF-8"));
			encryptedPassword = bytesToHex(md.digest());
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		System.out.println(encryptedPassword);
//		encryptedPassword = password;
		return encryptedPassword;
	}

	private String bytesToHex(byte[] bytes) {

		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	public boolean isExistEmail(String userId) {
		Connection conn = getConnection();
		boolean result = false;
		try {
			result = new UserDAO(conn).isExistEmail(userId);
			conn.close();
		} catch (SQLException e) {
			// result 기본값 false
			e.printStackTrace();
		}

		return result;
	}

	public boolean addUser(UserVO user) throws SQLException {
		Connection conn = getConnection();
		boolean result = false;
		user.setPassword(getEncryptedPassword(user.getEmail(), user.getPassword()));
		result = new UserDAO(conn).addUser(user);
		conn.commit();
		conn.close();
		return result;

	}

	public UserVO getUser(String userId) throws SQLException {
		Connection conn = getConnection();
		UserVO user = null;
		user = new UserDAO(conn).getUser(userId);
		conn.close();
		return user;

	}

	public String getNickname(String userId) throws SQLException {
		Connection conn = getConnection();
		String result = new UserDAO(conn).getNickname(userId);
		conn.close();
		return result;

	}

	public boolean updateNickname(String userId, String newNickname) {
		Connection conn = getConnection();
		boolean result = false;
		try {
			result = new UserDAO(conn).updateNickname(userId, newNickname);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// result 기본값 default
		}
		return result;
	}

	public boolean updatePassword(String userId, String oldPassword, String newPassword) {
		Connection conn = getConnection();
		boolean result = false;
		UserDAO dao = new UserDAO(conn);
		try {
			String encryptedOldPassword = getEncryptedPassword(userId, oldPassword);
			String encryptedNewPassword = getEncryptedPassword(userId, newPassword);
			if (dao.login(userId, encryptedOldPassword)) {
				result = dao.updatePassword(userId, encryptedNewPassword);
			}
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// result 기본값 default
		}
		return result;
	}

	public boolean resetPassword(String userId, String password) {
		Connection conn = getConnection();
		boolean result = false;
		UserDAO dao = new UserDAO(conn);
		try {
			String encryptedPassword = getEncryptedPassword(userId, password);
			result = dao.updatePassword(userId, encryptedPassword);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// result 기본값 default
		}
		return result;
	}

	public boolean isAdult(String userId) throws SQLException {
		Connection conn = getConnection();
		boolean result = false;
		result = new UserDAO(conn).isAdult(userId);
		conn.commit();
		conn.close();
		return result;
	}
}

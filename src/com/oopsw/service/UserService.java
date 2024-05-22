package com.oopsw.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.dao.UserDAO;
import com.oopsw.model.vo.UserVO;

public class UserService {
	Connection conn;

	public UserService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "hr", "hr");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	boolean isKKBookmark(String userId, int KK_ID) {
		return new KKDAO(conn).isKKBookmark(userId, KK_ID);
	}

	boolean addKKBookmark(String userId, int KK_ID) {
		return new KKDAO(conn).addKKBookmark(userId, KK_ID);

	}

	boolean deleteKKBookmark(String userId, int KK_ID) {
		return new KKDAO(conn).deleteKKBookmark(userId, KK_ID);

	}

	boolean login(String userId, String password) {
		boolean result = false;
		try {
			String encryptedPassword = getEncryptedPassword(userId, password);
			result = new UserDAO(conn).login(userId, encryptedPassword);
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
		
		// XXX: DB 샘플데이터 활용을 위해 평문비밀번호 사용중. 서비스 시 아래 줄 삭제.
		encryptedPassword = password;
		return encryptedPassword;
	}

	private String bytesToHex(byte[] bytes) {

		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	boolean isExistEmail(String userId) {
		boolean result = false;
		try {
			result = new UserDAO(conn).isExistEmailTest(userId);
		} catch (SQLException e) {
			// result 기본값 false
		}

		return result;
	}

	boolean addUser(UserVO user) throws SQLException {
		boolean result = false;
		user.setPassword(getEncryptedPassword(user.getEmail(), user.getPassword()));
		result = new UserDAO(conn).addUser(user);

		return result;

	}

	UserVO getUser(String userId) throws SQLException {
		UserVO user = null;
		user = new UserDAO(conn).getUser(userId);
		return user;

	}

	String getNickname(String userId) throws SQLException {
		return new UserDAO(conn).getNickname(userId);

	}

	boolean updateNickname(String userId, String newNickname) {
		boolean result = false;
		try {
			result = new UserDAO(conn).updateNickname(userId, newNickname);
		} catch (SQLException e) {
			// result 기본값 default
		}
		return result;
	}

	boolean updatePassword(String userId, String newPassword) {
		boolean result = false;
		try {
			String encryptedPassword = getEncryptedPassword(userId, newPassword);
			result = new UserDAO(conn).updatePassword(userId, encryptedPassword);
		} catch (SQLException e) {
			// result 기본값 default
		}
		return result;
	}
}

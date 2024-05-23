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
	private Connection conn;

	public UserService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "hr", "hr");
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isKKBookmark(String userId, int KK_ID) {
		boolean result = new KKDAO(conn).isKKBookmark(userId, KK_ID);
		try {
			conn.close();
		} catch (SQLException e) {
			// 서버-DB 네트워크 통신 문제로 커밋 실패한경우.
			//커넥션 연결 실패도 포함될듯?
		}
		return result;
	}

	public boolean addKKBookmark(String userId, int KK_ID) {
		boolean result = new KKDAO(conn).addKKBookmark(userId, KK_ID);
		System.out.println(1);
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// 서버-DB 네트워크 통신 문제로 커밋 실패한경우.
			//커넥션 연결 실패도 포함될듯?
		}
		return result;

	}

	public boolean deleteKKBookmark(String userId, int KK_ID) {
		boolean result = new KKDAO(conn).deleteKKBookmark(userId, KK_ID);
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// 서버-DB 네트워크 통신 문제로 커밋 실패한경우.
			//커넥션 연결 실패도 포함될듯?
		}
		return result;

	}

	public boolean login(String userId, String password) {
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

	public boolean isExistEmail(String userId) {
		boolean result = false;
		try {
			result = new UserDAO(conn).isExistEmailTest(userId);
			conn.close();
		} catch (SQLException e) {
			// result 기본값 false
		}

		return result;
	}

	public boolean addUser(UserVO user) throws SQLException {
		boolean result = false;
		user.setPassword(getEncryptedPassword(user.getEmail(), user.getPassword()));
		result = new UserDAO(conn).addUser(user);
		conn.close();
		return result;

	}

	public UserVO getUser(String userId) throws SQLException {
		UserVO user = null;
		user = new UserDAO(conn).getUser(userId);
		conn.close();
		return user;

	}

	public String getNickname(String userId) throws SQLException {
		String result = new UserDAO(conn).getNickname(userId);
		conn.close();
		return result;

	}

	public boolean updateNickname(String userId, String newNickname) {
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
		boolean result = false;
		UserDAO dao = new UserDAO(conn);
		try {
			String encryptedOldPassword = getEncryptedPassword(userId, oldPassword);
			String encryptedNewPassword = getEncryptedPassword(userId, newPassword);
			if(dao.login(userId, encryptedOldPassword)){
				result = dao.updatePassword(userId, encryptedNewPassword);
			}
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// result 기본값 default
		}
		return result;
	}
}

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
		try {
			new UserDAO(conn).login(userId, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String encrypt(String text) throws NoSuchAlgorithmException {
		  
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try {
			md.update(text.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return bytesToHex(md.digest());
        
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
		return result;

	}

	boolean addUser(String userId, UserVO user) {
		boolean result = false;
		return result;

	}

	UserVO getUser(String userId) {
		return null;

	}

	String getNickname(String userId) {
		return userId;

	}

	boolean updateNickname(String userId, String newNickname) {
		boolean result = false;
		return result;

	}

	boolean updatePassword(String userId, String newPassword) {
		boolean result = false;
		return result;

	}
}

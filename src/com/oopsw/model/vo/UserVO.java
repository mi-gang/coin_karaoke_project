package com.oopsw.model.vo;

import java.time.LocalDateTime;

public class UserVO {
	String userId;
	String nickname;
	LocalDateTime birthDate;
	String password;
	
	
	public UserVO(String userId, String nickname, LocalDateTime birthDate, String password) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", 닉네임=" + nickname + ", 생년월일=" + birthDate
				+ ", 비밀번호=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return getUserId();
	}
	public void setEmail(String Email) {
		setUserId(Email);
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

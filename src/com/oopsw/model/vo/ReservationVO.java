package com.oopsw.model.vo;

import java.time.LocalDateTime;

public class ReservationVO {
	private int reservationId;
	private int isCancel;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String userId;
	private int roomId;
	private int KKId;
	private String name;

	public ReservationVO(int reservationId, int isCancel, LocalDateTime startTime, LocalDateTime endTime,
			String userId, int roomId, int KKId, String name) {
		setReservationId(reservationId);
		setIsCancel(isCancel);
		setStartTime(startTime);
		setEndTime(endTime);
		setUserId(userId);
		setRoomId(roomId);
		setKKId(KKId);
		setName(name);
	}
	
	// 이용 시간 확인용
	public ReservationVO(LocalDateTime startTime, LocalDateTime endTime) {
		setStartTime(startTime);
		setEndTime(endTime);
	}
	
	// 추가 시간 확인용
	public ReservationVO(LocalDateTime endTime, int roomId) {
		setEndTime(endTime);
		setRoomId(roomId);
	}
	public ReservationVO(int reservationId, LocalDateTime startTime) {
		setReservationId(reservationId);
		setStartTime(startTime);
	}
	
	public ReservationVO(LocalDateTime endTime, int reservationId) {
		setEndTime(endTime);
		setReservationId(reservationId);
	}
	
	// 예약 현황 정보용
	public ReservationVO(int reservationId, LocalDateTime startTime, LocalDateTime endTime, int roomId) {
		setReservationId(reservationId);
		setStartTime(startTime);
		setEndTime(endTime);
		setRoomId(roomId);
	}

	
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getKKId() {
		return KKId;
	}

	public void setKKId(int kKId) {
		KKId = kKId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + KKId;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + isCancel;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reservationId;
		result = prime * result + roomId;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		ReservationVO other = (ReservationVO) obj;
		if (KKId != other.KKId)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (isCancel != other.isCancel)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reservationId != other.reservationId)
			return false;
		if (roomId != other.roomId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservationVO [reservationId=" + reservationId + ", isCancel=" + isCancel + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", userId=" + userId + ", roomId=" + roomId + ", KKId=" + KKId + ", name="
				+ name + "]";
	}
	


}

package com.oopsw.service;

import java.util.Collection;

import com.oopsw.model.vo.ReservationVO;

public class ReservationRoomInfoVO {
	private int roomId;
	private String roomName;
	private Collection<ReservationVO> reservationVOs;
	
	
	public ReservationRoomInfoVO(int roomId, String roomName, Collection<ReservationVO> reservationVOs) {
		setRoomId(roomId);
		setRoomName(roomName);
		setReservationVOs(reservationVOs);
	}
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Collection<ReservationVO> getReservationVOs() {
		return reservationVOs;
	}
	public void setReservationVOs(Collection<ReservationVO> reservationVOs) {
		this.reservationVOs = reservationVOs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservationVOs == null) ? 0 : reservationVOs.hashCode());
		result = prime * result + roomId;
		result = prime * result + ((roomName == null) ? 0 : roomName.hashCode());
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
		ReservationRoomInfoVO other = (ReservationRoomInfoVO) obj;
		if (reservationVOs == null) {
			if (other.reservationVOs != null)
				return false;
		} else if (!reservationVOs.equals(other.reservationVOs))
			return false;
		if (roomId != other.roomId)
			return false;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReservationRoomInfoVO [roomId=" + roomId + ", roomName=" + roomName + ", reservationVOs="
				+ reservationVOs + "]";
	}
	
	
}

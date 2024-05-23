package com.oopsw.model.vo;

public class RoomInfoVO {
	private int roomId;
	private String name;
	private int headcount;
	private int KKId;

	public RoomInfoVO(int roomId, String name, int headcount, int kKId) {
		setRoomId(roomId);
		setName(name);
		setHeadcount(headcount);
		setKKId(kKId);
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeadcount() {
		return headcount;
	}

	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}

	public int getKKId() {
		return KKId;
	}

	public void setKKId(int kKId) {
		KKId = kKId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + KKId;
		result = prime * result + headcount;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + roomId;
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
		RoomInfoVO other = (RoomInfoVO) obj;
		if (KKId != other.KKId)
			return false;
		if (headcount != other.headcount)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roomId != other.roomId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomInfoVO [roomId=" + roomId + ", name=" + name + ", headcount=" + headcount + ", KKId=" + KKId + "]";
	}

}

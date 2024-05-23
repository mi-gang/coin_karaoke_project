package com.oopsw.model.vo;

public class InquireVO {
	private int inquireId;
	private String content;
	private int reservationId;

	public InquireVO(int inquireId, String content, int reservationId) {
		setInquireId(inquireId);
		setContent(content);
		setReservationId(reservationId);
	}

	public int getInquireId() {
		return inquireId;
	}

	public void setInquireId(int inquireId) {
		this.inquireId = inquireId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + inquireId;
		result = prime * result + reservationId;
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
		InquireVO other = (InquireVO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (inquireId != other.inquireId)
			return false;
		if (reservationId != other.reservationId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InquireVO [inquireId=" + inquireId + ", content=" + content + ", reservationId=" + reservationId + "]";
	}

}

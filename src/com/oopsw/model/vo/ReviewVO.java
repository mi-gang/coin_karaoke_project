package com.oopsw.model.vo;

import java.time.LocalDateTime;

public class ReviewVO {
	private int reviewId;
	private String content;
	private float star;
	private int reservationId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String KKname;
	private String userNickname;
	
	public ReviewVO(int reviewId, String content, String userNickname){
		setReviewId(reviewId);
		setContent(content);
		setUserNickname(userNickname);
	}

	public ReviewVO(int reviewId, String content, float star, LocalDateTime startTime, LocalDateTime endTime,
			String KKname) {
		setReviewId(reviewId);
		setContent(content);
		setStar(star);
		setReservationId(reservationId);
		setStartTime(startTime);
		setEndTime(endTime);
		setKKname(KKname);
	}
	
	public ReviewVO(String content, float star, int reservationId) {
		setContent(content);
		setStar(star);
		setReservationId(reservationId);
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getStar() {
		return star;
	}

	public void setStar(float star) {
		this.star = star;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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

	public String getKKname() {
		return KKname;
	}

	public void setKKname(String kKname) {
		KKname = kKname;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((KKname == null) ? 0 : KKname.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + reservationId;
		result = prime * result + reviewId;
		result = prime * result + Float.floatToIntBits(star);
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((userNickname == null) ? 0 : userNickname.hashCode());
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
		ReviewVO other = (ReviewVO) obj;
		if (KKname == null) {
			if (other.KKname != null)
				return false;
		} else if (!KKname.equals(other.KKname))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (reservationId != other.reservationId)
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (Float.floatToIntBits(star) != Float.floatToIntBits(other.star))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (userNickname == null) {
			if (other.userNickname != null)
				return false;
		} else if (!userNickname.equals(other.userNickname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReviewVO [reviewId=" + reviewId + ", content=" + content + ", star=" + star + ", reservationId="
				+ reservationId + ", startTime=" + startTime + ", endTime=" + endTime + ", KKname=" + KKname
				+ ", userNickname=" + userNickname + "]";
	}
	//

}

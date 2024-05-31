package com.oopsw.service;

import com.oopsw.model.vo.ReservationVO;

public class completedReservationVO {

	private ReservationVO reservationVO;
	private boolean isReviewWritten;

	public completedReservationVO(ReservationVO reservationVO, boolean isReviewWritten) {
		setReservationVO(reservationVO);
		setIsReviewWritten(isReviewWritten);
	}

	public ReservationVO getReservationVO() {
		return reservationVO;
	}

	public void setReservationVO(ReservationVO reservationVO) {
		this.reservationVO = reservationVO;
	}

	public boolean getIs_review_written() {
		return isReviewWritten;
	}

	public void setIsReviewWritten(boolean isReviewWritten) {
		this.isReviewWritten = isReviewWritten;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isReviewWritten ? 1231 : 1237);
		result = prime * result + ((reservationVO == null) ? 0 : reservationVO.hashCode());
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
		completedReservationVO other = (completedReservationVO) obj;
		if (isReviewWritten != other.isReviewWritten)
			return false;
		if (reservationVO == null) {
			if (other.reservationVO != null)
				return false;
		} else if (!reservationVO.equals(other.reservationVO))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "completedReservationVO [reservationVO=" + reservationVO + ", isReviewWritten=" + isReviewWritten + "]";
	}

}

package com.oopsw.service;

import java.time.LocalDateTime;

public class AdditionalTimeInfoVO {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int availableHour;
	private int availableMinute;
	private boolean isAdult;

	public AdditionalTimeInfoVO(LocalDateTime startTime, LocalDateTime endTime, int availableHour, int availableMinute,
			boolean isAdult) {
		setStartTime(startTime);
		setEndTime(endTime);
		setAvailableHour(availableHour);
		setAvailableMinute(availableMinute);
		setAdult(isAdult);
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

	public int getAvailableHour() {
		return availableHour;
	}

	public void setAvailableHour(int availableHour) {
		this.availableHour = availableHour;
	}

	public int getAvailableMinute() {
		return availableMinute;
	}

	public void setAvailableMinute(int availableMinute) {
		this.availableMinute = availableMinute;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availableHour;
		result = prime * result + availableMinute;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + (isAdult ? 1231 : 1237);
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		AdditionalTimeInfoVO other = (AdditionalTimeInfoVO) obj;
		if (availableHour != other.availableHour)
			return false;
		if (availableMinute != other.availableMinute)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (isAdult != other.isAdult)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdditionalTimeInfoVO [startTime=" + startTime + ", endTime=" + endTime + ", availableHour="
				+ availableHour + ", availableMinute=" + availableMinute + ", isAdult=" + isAdult + "]";
	}

}

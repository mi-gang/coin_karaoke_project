package com.oopsw.model.vo;

import java.time.LocalDateTime;
import java.util.Collection;

public class KKVO {
	private int kkId;
	private String name;
	private LocalDateTime openingHour;
	private LocalDateTime closingHour;
	private String note;
	private String address;
	private Collection<String> representativeKeywordList;
	private float starRating;
	
	public KKVO() {
		
	}
	
	public KKVO(int kkId, String name, String address, float starRating, Collection<String>representativeKeywordList) {
		setKkId(kkId);
		setName(name);
		setAddress(address);
		setStarRating(starRating);
		setRepresentativeKeywordList(representativeKeywordList);
	}
	
	public KKVO(int kkId, String name, String address, float starRating) {
		setKkId(kkId);
		setName(name);
		setAddress(address);
		setStarRating(starRating);
	}
	
	public KKVO(int kkId, String name, String address, Collection<String>representativeKeywordList) {
		setKkId(kkId);
		setName(name);
		setAddress(address);
		setRepresentativeKeywordList(representativeKeywordList);
	}
	
	public KKVO(int kkId, String name, String address) {
		setKkId(kkId);
		setName(name);
		setAddress(address);
	}
	
	public KKVO(int kkId, String name, LocalDateTime openingHour, LocalDateTime closingHour, String note,
			String address) {
		super();
		setKkId(kkId);
		setName(name);
		setOpeningHour(openingHour);
		setClosingHour(closingHour);
		setNote(note);
		setAddress(address);
	}
	
	public float getStarRating() {
		return starRating;
	}

	public void setStarRating(float starRating) {
		this.starRating = starRating;
	}

	public int getKkId() {
		return kkId;
	}
	public void setKkId(int kkId) {
		this.kkId = kkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getOpeningHour() {
		return openingHour;
	}
	public void setOpeningHour(LocalDateTime openingHour) {
		this.openingHour = openingHour;
	}
	public LocalDateTime getClosingHour() {
		return closingHour;
	}
	public void setClosingHour(LocalDateTime closingHour) {
		this.closingHour = closingHour;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Collection<String> getRepresentativeKeywordList() {
		return representativeKeywordList;
	}

	public void setRepresentativeKeywordList(Collection<String> representativeKeywordList) {
		this.representativeKeywordList = representativeKeywordList;
	}

	@Override
	public String toString() {
		return "KKVO [kkId=" + kkId + ", name=" + name + ", openingHour=" + openingHour + ", closingHour=" + closingHour
				+ ", note=" + note + ", address=" + address + ", representativeKeywordList=" + representativeKeywordList
				+ ", starRating=" + starRating + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kkId;
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
		KKVO other = (KKVO) obj;
		if (kkId != other.kkId)
			return false;
		return true;
	}


	
}

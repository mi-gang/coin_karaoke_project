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
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((closingHour == null) ? 0 : closingHour.hashCode());
		result = prime * result + kkId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((openingHour == null) ? 0 : openingHour.hashCode());
		result = prime * result + ((representativeKeywordList == null) ? 0 : representativeKeywordList.hashCode());
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (closingHour == null) {
			if (other.closingHour != null)
				return false;
		} else if (!closingHour.equals(other.closingHour))
			return false;
		if (kkId != other.kkId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (openingHour == null) {
			if (other.openingHour != null)
				return false;
		} else if (!openingHour.equals(other.openingHour))
			return false;
		if (representativeKeywordList == null) {
			if (other.representativeKeywordList != null)
				return false;
		} else if (!representativeKeywordList.equals(other.representativeKeywordList))
			return false;
		return true;
	}
	
	
	
}

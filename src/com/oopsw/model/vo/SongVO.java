package com.oopsw.model.vo;

public class SongVO {
	private int songId;
	private String brand;
	private String title;
	private String singer;
	
	public SongVO(){}
	public SongVO(int songId, String brand, String title,String singer){
		setSongId(songId);
		setBrand(brand);
		setTitle(title);
		setSinger(singer);
	}
	
	public SongVO(String title, String singer){
		setTitle(title);
		setSinger(singer);
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		result = prime * result + songId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		SongVO other = (SongVO) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		if (songId != other.songId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SongVO [songId=" + songId + ", brand=" + brand + ", title=" + title + ", singer=" + singer + "]";
	}
}

package com.oopsw.service;

public class MusicServiceVO {
	private int playListId;
	private String playListTitle;
	private int songId;
	private String brand;
	private boolean isMusic;
	
	
	
	public MusicServiceVO(int playListId, String playListTitle, int songId, String brand, boolean isMusic) {
		super();
		setPlayListId(playListId);
		setPlayListTitle(playListTitle);
		setSongId(songId);
		setBrand(brand);
//		this.isMusic = isMusic;
		setMusic(isMusic);
	}
	public MusicServiceVO(int playListId,String playListTitle){
		setPlayListId(playListId);
		setPlayListTitle(playListTitle);
	}
	public int getPlayListId() {
		return playListId;
	}
	public void setPlayListId(int playListId) {
		this.playListId = playListId;
	}
	public String getPlayListTitle() {
		return playListTitle;
	}
	public void setPlayListTitle(String playListTitle) {
		this.playListTitle = playListTitle;
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
	public boolean isMusic() {
		return isMusic;
	}
	public void setMusic(boolean isMusic) {
		this.isMusic = isMusic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + (isMusic ? 1231 : 1237);
		result = prime * result + playListId;
		result = prime * result + ((playListTitle == null) ? 0 : playListTitle.hashCode());
		result = prime * result + songId;
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
		MusicServiceVO other = (MusicServiceVO) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (isMusic != other.isMusic)
			return false;
		if (playListId != other.playListId)
			return false;
		if (playListTitle == null) {
			if (other.playListTitle != null)
				return false;
		} else if (!playListTitle.equals(other.playListTitle))
			return false;
		if (songId != other.songId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MusicServiceVO [playListId=" + playListId + ", playListTitle=" + playListTitle + ", songId=" + songId
				+ ", brand=" + brand + ", isMusic=" + isMusic + "]";
	}
}

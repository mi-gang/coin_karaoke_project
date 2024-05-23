package com.oopsw.model.vo;

import java.util.List;

public class PlaylistVO {
	private int playListId;
	private String playListTitle;
	private String userId;	
//	private List<SongVO> songList; 
	private int songId;
//	private List<String> brand;
	private String brand;   
	
	public PlaylistVO(){}
	public PlaylistVO(int playListId, String playListTitle, String userId,int songId,String brand){
		setPlayListId(playListId);
		setPlayListTitle(playListTitle);
		setUserId(userId);
		setSongId(songId);
		setBrand(brand);
	}
	public PlaylistVO(int playListId, String playListTitle, String userId) {
		super();
		setPlayListId(playListId);
		setPlayListTitle(playListTitle);
		setUserId(userId);
	}
	public PlaylistVO(int playListId,String playListTitle){
		setPlayListId(playListId);
		setPlayListTitle(playListTitle);
	}
	
	public PlaylistVO(String brand,int songId){
		setBrand(brand);
		setSongId(songId);
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + playListId;
		result = prime * result + ((playListTitle == null) ? 0 : playListTitle.hashCode());
		result = prime * result + songId;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		PlaylistVO other = (PlaylistVO) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "playlistVO [playListId=" + playListId + ", playListTitle=" + playListTitle + ", userId=" + userId
				+ ", songId=" + songId + ", brand=" + brand + "]";
	}
	
	
	
}

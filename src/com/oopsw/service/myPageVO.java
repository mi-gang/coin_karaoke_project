package com.oopsw.service;

import java.util.Collection;

import com.oopsw.model.vo.KKVO;
import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.model.vo.ReviewVO;

public class myPageVO {

	private String nickname;		// 얘 추가
	private KKVO kkVO;
	private int bookmarkCount;
	private ReviewVO reviewVO;
	private int reviewCount;
	private Collection<PlaylistVO> playlistVOs;
	private int playlistCount;
	
	
	
	public myPageVO(String nickname, KKVO kkVO, int bookmarkCount, ReviewVO reviewVO, int reviewCount,
			Collection<PlaylistVO> playlistVOs, int playlistCount) {
		setNickname(nickname);
		setKkVO(kkVO);
		setBookmarkCount(bookmarkCount);
		setReviewVO(reviewVO);
		setReviewCount(reviewCount);
		setPlaylistVOs(playlistVOs);
		setPlaylistCount(playlistCount);
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public KKVO getKkVO() {
		return kkVO;
	}
	public void setKkVO(KKVO kkVO) {
		this.kkVO = kkVO;
	}
	public int getBookmarkCount() {
		return bookmarkCount;
	}
	public void setBookmarkCount(int bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}
	public ReviewVO getReviewVO() {
		return reviewVO;
	}
	public void setReviewVO(ReviewVO reviewVO) {
		this.reviewVO = reviewVO;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public Collection<PlaylistVO> getPlaylistVOs() {
		return playlistVOs;
	}
	public void setPlaylistVOs(Collection<PlaylistVO> playlistVOs) {
		this.playlistVOs = playlistVOs;
	}
	public int getPlaylistCount() {
		return playlistCount;
	}
	public void setPlaylistCount(int playlistCount) {
		this.playlistCount = playlistCount;
	}
	
	

}

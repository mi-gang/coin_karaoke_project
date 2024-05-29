package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.model.vo.SongVO;

public class PlaylistDAO {
	private Connection conn;
	public PlaylistDAO(Connection conn){ 
		this.conn=conn;
	}
	
	public Collection<PlaylistVO> getPlaylistList(String userId){
		String sql="Select playlist_id, playlist_title from playlists where user_id=?";
		Collection<PlaylistVO> list=new ArrayList<>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
				list.add(new PlaylistVO(rs.getInt(1),rs.getString(2)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean addSongToPlaylist(int songId, String brand, int playlistId){
		String sql="INSERT INTO SONGS_PLAYLISTS (song_id, brand, playlist_id) values (?, ?, ?)";
		boolean result=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, songId);
			pstmt.setString(2, brand);
			pstmt.setInt(3, playlistId);
			int num=pstmt.executeUpdate();
			if(num==1){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean isSongInPlaylist(int songId,String brand,int playlistId){
		String sql="SELECT song_id from SONGS_PLAYLISTS where song_id = ? and brand = ? and playlist_id = ?";
		int id=0;
		boolean result=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, songId);
			pstmt.setString(2, brand);
			pstmt.setInt(3, playlistId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				id=rs.getInt(1);
			}if(id!=0){
				result=true;
			}}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteSongInPlaylist(int songId,String brand,int playlistId){
		String sql="DELETE from SONGS_PLAYLISTS where song_id = ? and brand = ? and playlist_id = ?";
		boolean result=false;	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, songId);
			pstmt.setString(2, brand);
			pstmt.setInt(3, playlistId);
			int num=pstmt.executeUpdate();
			if(num==1){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean addPlaylist (String playlistTitle,String userId){
		String sql="INSERT INTO playlists (playlist_id, playlist_title, user_id) values (playlist_id_seq.nextval, ?, ?)";
		boolean result=false;	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, playlistTitle);
			pstmt.setString(2, userId);
			int num=pstmt.executeUpdate();
			if(num==1){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean deletePlaylist (int playlistId){
		String sql="DELETE from playlists where playlist_id=?";
		boolean result=false;	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, playlistId);
			int num=pstmt.executeUpdate();
			if(num==1){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String getPlaylistTitle(String userId,int playlistId){
		String sql="select playlist_title from playlists where playlist_id=? and user_id =?";
		String playlistTitle="";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, playlistId);
			pstmt.setString(2, userId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
				playlistTitle=rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playlistTitle;		
	}
	public int getPlaylistSongCount(int playlistId,String brand){
		String sql="select count(song_id) from songs_playlists where playlist_id = ? and brand = ?";
		int songNum=0;
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, playlistId);
			pstmt.setString(2, brand);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
				songNum=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songNum;		
	}
	public Collection<PlaylistVO> getSongListInPlaylist(int playlistId, String brand){
		String sql="SELECT brand, song_id from SONGS_PLAYLISTS where playlist_id = ? AND brand =?";
		Collection<PlaylistVO> list=new ArrayList<>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,playlistId);
			pstmt.setString(2,brand);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
				list.add(new PlaylistVO(rs.getString(1),rs.getInt(2)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public SongVO getSongInfo(int songId, String brand){
		String sql="SELECT song_Id,brand,title,singer FROM songs  WHERE song_id=? AND brand=? order by song_Id desc";
		SongVO vo=new SongVO();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,songId);
			pstmt.setString(2, brand);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
				vo=new SongVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	public boolean updatePlaylistTitle(String playlistTitle, String userId,int playlistId){
		String sql="update playlists set playlist_title=? where user_id=? and playlist_id=?";
		boolean result=false;	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, playlistTitle);
			pstmt.setString(2, userId);
			pstmt.setInt(3, playlistId);
			int num=pstmt.executeUpdate();
			if(num==1){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean deletePlayList(int playlistId){
		String sql="DELETE from playlists where playlist_id=?";
		boolean result=false;	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, playlistId);
			int num=pstmt.executeUpdate();
			if(num==1){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// 플레이리스트 3개 불러오기
	public Collection<PlaylistVO> getmypagePlaylist(String userId){
		String sql="select * from "
				+ "(select playlist_id, playlist_title "
				+ "from playlists "
				+ "where user_id=? "
				+ "order by playlist_title asc) "
				+ "where rownum >= 1 and  rownum <= 3";
		Collection<PlaylistVO> list=new ArrayList<>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
				list.add(new PlaylistVO(rs.getInt(1),rs.getString(2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/** 플레이리스트 개수 불러오기 */
	public int getPlaylistCount(String userId) {

		String sql = "select count(review_id) "
				+ "from reviews r "
				+ "JOIN reservations re ON re.reservation_id = r.reservation_id "
				+ "where re.user_id=?";

		int result = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
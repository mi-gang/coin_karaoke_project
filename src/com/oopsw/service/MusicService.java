package com.oopsw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oopsw.model.dao.PlaylistDAO;
import com.oopsw.model.dao.SongDAO;
import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.model.vo.SongVO;


public class MusicService {
	private static Connection conn;

	public MusicService(){
//		Context context;
//		try {
//			context = new InitialContext();
//			DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc/myoracle");
//			conn=dataSource.getConnection();
//			conn.setAutoCommit(false);
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  //Tomcat이 뜨지않으면 활성화 되지 않는다.
//		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "hr", "hr");
			conn.setAutoCommit(false);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

//Song VO로 아래 변수 대체 가능

	public boolean addMusic(int songId, String brand,String title, String singer,int playlistId){
		boolean result=false;
		result=new SongDAO(conn).isSongInDB(songId,brand);//API Data
		//DAO에 있는 method를 통해서 
		if(result==false){
			result=new SongDAO(conn).addSong(songId, brand, title, singer);//API Data
		}
		if(result==true){
			result=new PlaylistDAO(conn).addSongToPlaylist(songId, brand, playlistId);//넘어오는 API Data
		}
		
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;


	}

	public Collection<MusicServiceVO> myPlaylistCheckMusic(String userId, int songId, String brand){
		Collection<MusicServiceVO> list=new ArrayList<>();
		Collection<PlaylistVO> list2=new ArrayList<>();//PlaylistVO를 고치고 다시 보기
		list2=new PlaylistDAO(conn).getPlaylistList(userId);
		for(PlaylistVO vo :list2 ){
			boolean isMusic = new PlaylistDAO(conn).isSongInPlaylist(songId, brand, vo.getPlayListId());
			list.add(new MusicServiceVO(vo.getPlayListId(), vo.getPlayListTitle(), songId, brand, isMusic));
		}
//		try {
////			conn.commit();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return list;
	}
	public boolean deleteSongInPlaylist(int songId,String brand,int playlistId){
		boolean result=false;
		result=new PlaylistDAO(conn).deleteSongInPlaylist(songId, brand, playlistId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean addPlaylist (String playlistTitle,String userId){
		boolean result=false;
		result=new PlaylistDAO(conn).addPlaylist(playlistTitle, userId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	public Collection<PlaylistVO> getPlaylistList(String userId){
		Collection<PlaylistVO> list=new ArrayList<>();
		list=new PlaylistDAO(conn).getPlaylistList(userId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public boolean deletePlaylist(String userId,int playlistId){
		boolean result=false;
		result=new PlaylistDAO(conn).deletePlaylist(playlistId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String getPlaylistTitle(String userId,int playlistId){
		String title="";
		title=new PlaylistDAO(conn).getPlaylistTitle(userId, playlistId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}
	public int getPlaylistSongCount(int playlistId,String brand){
		int songNum=0;
		songNum=new PlaylistDAO(conn).getPlaylistSongCount(playlistId, brand);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songNum;
	}
	//다시 보기
	public Collection<SongVO> playlistMusicList(int playlistId,String brand){
		Collection<SongVO> list=new ArrayList<>();
		Collection<PlaylistVO> list2=new ArrayList<>();
		list2=new PlaylistDAO(conn).getSongListInPlaylist(playlistId, brand);
		System.out.println(list2);
		for(PlaylistVO vo :list2 ){
			SongVO temp = new PlaylistDAO(conn).getSongInfo(vo.getSongId(), brand);
			list.add(temp);
			
		}
		System.out.println(list);
//		try {
////			conn.commit();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return list;
	}	
	public boolean updatePlaylistTitle(String playlistTitle,String userId,int playlistId){
		boolean result=false;
		result=new PlaylistDAO(conn).updatePlaylistTitle(playlistTitle, userId, playlistId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean deletePlayList(int playlistId){
		boolean result=false;
		result=new PlaylistDAO(conn).deletePlayList(playlistId);
		try {
//			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
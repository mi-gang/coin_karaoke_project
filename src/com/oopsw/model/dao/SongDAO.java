package com.oopsw.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongDAO {
	private Connection conn;
	public SongDAO(Connection conn){ 
		this.conn=conn;
	}

	public boolean addSong(int songId, String brand,String title, String singer){
		String sql="Insert into SongS (song_id, brand, title, singer) values (?,?,?,?)";
		boolean result=false;	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, songId);
			pstmt.setString(2, brand);
			pstmt.setString(3, title);
			pstmt.setString(4, singer);
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
	public boolean isSongInDB(int songId, String brand){
		String sql="SELECT song_id from SONGS where song_id = ? and brand = ?";
		int id=0;
		boolean result=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, songId);
			pstmt.setString(2, brand);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				id=rs.getInt(1);
			}if(id!=0){
				result=true;
			}}	 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
}
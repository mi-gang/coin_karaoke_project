package test.com.oopsw;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.vo.KKVO;

import com.oopsw.model.dao.PlaylistDAO;
import com.oopsw.model.dao.SongDAO;
import com.oopsw.model.vo.PlaylistVO;


public class DAOTest {
	private static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 1. driver loading
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("1 loading ok");
		
		// 2. driver connection
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "hr", "hr");
		System.out.println("2 connection ok");		
		conn.setAutoCommit(false);
	}

	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {
	// 	Class.forName("oracle.jdbc.OracleDriver");
	// 	//2.connection
	// 	String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	// 	conn=DriverManager.getConnection(url, "hr", "hr");
	// }

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.close();
	}
	
	@Test
	public void getSearchKKList() {
		int[] tmp = {1,0,0,1};
		System.out.println("test 1");
		assertNotNull(new KKDAO(conn).getSearchKKList("금천구"));
		System.out.println("test 2");
		assertNotNull(new KKDAO(conn).getSearchKKList(tmp, 2, "금천구"));
		System.out.println("test 3");
		assertNotNull(new KKDAO(conn).getSearchKKList("강남구"));
	}

	
	@Test
	public void getNearRecommendKKList() {
		assertNotNull(new KKDAO(conn).getNearRecommendKKList("용산구"));
		// assertThat(new KKDAO(conn).getNearRecommendKKList("용산구"), is(nullValue()));
		assertNotNull(new KKDAO(conn).getNearRecommendKKList("금천구"));
	}
	
	
	@Test 
	public void isKKBookmark() {
		assertTrue(new KKDAO(conn).isKKBookmark("test@test.com", 1));
		assertFalse(new KKDAO(conn).isKKBookmark("test@test.com", 10));
		assertFalse(new KKDAO(conn).addKKBookmark("test2@naver.com", 1));
	}
	
	/*@Test
	public void addKKBookmark() {
		assertTrue(new KKDAO(conn).addKKBookmark("test@test.com", 4));
		assertFalse(new KKDAO(conn).addKKBookmark("test@test.com", 4));
	}
	*/
	@Test 
	public void deleteKKBookmark() {
		assertTrue(new KKDAO(conn).deleteKKBookmark("test@test.com", 4));
		// assertFalse(new KKDAO(conn).deleteKKBookmark("test@test.com", 4));
	}
	
	
	/*@Test
	public void getRoomInfoList() {
		assertNotNull(new KKDAO(conn).getRoomInfoList(1));
	}*/
	//

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	//@Test
	public void getPlaylistList(){
		assertNotNull(new PlaylistDAO(conn).getPlaylistList("test@test.com"));
		System.out.println(new PlaylistDAO(conn).getPlaylistList("test@test.com"));
		
	}
	//@Test
	public void addSongToPlaylist(){
		assertTrue(new PlaylistDAO(conn).addSongToPlaylist(112, "TJ", 1));
		
	}
	//@Test
	public void isSongInPlaylist(){
		assertTrue(new PlaylistDAO(conn).isSongInPlaylist(11111, "TJ", 1));
		
	}
	//@Test
	public void deleteSongInPlaylist(){
		assertTrue(new PlaylistDAO(conn).deleteSongInPlaylist(11112, "TJ", 1));
	}
	//@Test
	public void addPlaylist(){
		assertTrue(new PlaylistDAO(conn).addPlaylist("에스파는 나야","test@test.com" ));
	}
	//@Test
	public void  deletePlaylist(){
		assertTrue(new PlaylistDAO(conn).deletePlaylist(21));
	}
	//@Test
	public void getPlaylistTitle(){
		assertNotNull(new PlaylistDAO(conn).getPlaylistSongCount(1,"TJ"));
		System.out.println(new PlaylistDAO(conn).getPlaylistSongCount(1,"TJ"));
	}
	//@Test
	public void getSongListInPlaylist(){
		assertNotNull(new PlaylistDAO(conn).getSongListInPlaylist(1));
		System.out.println(new PlaylistDAO(conn).getSongListInPlaylist(1));
	}
	//@Test
	public void getSongInfo(){
		assertNotNull(new PlaylistDAO(conn).getSongInfo(11112, "TJ"));
	}
	//@Test
	public void updatePlaylistTitle(){
		assertTrue(new PlaylistDAO(conn).updatePlaylistTitle("에스파는 나야", "test@test.com", 1));
	}
	//@Test
	public void deletePlayList(){
		assertTrue(new PlaylistDAO(conn).deletePlayList(2));
	}
	//@Test
	public void addSong(){
		assertTrue(new SongDAO(conn).addSong(79385, "TJ", "The MasterPlan", "Oasis"));
	}
	@Test
	public void isSongInDB(){
		assertTrue(new SongDAO(conn).isSongInDB(79337, "TJ"));
	}
	//
}

package test.com.oopsw;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.dao.PlaylistDAO;
import com.oopsw.model.dao.SongDAO;
import com.oopsw.model.vo.PlaylistVO;

public class DAOTest {
	private static Connection conn;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		//2.connection
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn=DriverManager.getConnection(url, "hr", "hr");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.close();
	}

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
}

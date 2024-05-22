package test.com.oopsw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.dao.PlaylistDAO;
import com.oopsw.model.vo.PlaylistVO;
import com.oopsw.model.vo.SongVO;
import com.oopsw.service.MusicService;

public class MusicServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Test
	public void getPlaylistSongCount() {
		MusicService service;
		try {
			service = new MusicService();
			assertThat(service.getPlaylistSongCount(1, "TJ")).isEqualTo(1);
			assertThat(service.getPlaylistSongCount(999999999, "TJ")).isNotEqualTo(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void getPlaylistTitle(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.getPlaylistTitle("test@test.com", 1)).isNotEmpty();
			assertThat(service.getPlaylistTitle("test@test.com", 5)).isEmpty();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	@Test
	public void addMusic(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.addMusic(79351, "TJ", "Little By Little", "Oasis", 1)).isTrue();
			assertThat(service.addMusic(79351, "TJ", "Little By Little", "Oasis", 1)).isFalse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
//	@Test
	public void MyPlaylistCheckMusic(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.MyPlaylistCheckMusic("test@test.com", 79351, "TJ")).isNotEmpty();
			System.out.println("MyPlaylistCheckMusic test : "+service.MyPlaylistCheckMusic("test@test.com",1111, "TJ"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
//	@Test
	public void deleteSongInPlaylist(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.deleteSongInPlaylist(79351, "TJ", 1)).isEqualTo(true);
			assertThat(service.deleteSongInPlaylist(1793331, "TJ", 1)).isEqualTo(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
//	@Test
	public void addPlaylist(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.addPlaylist("회식", "test@test.com")).isEqualTo(true);
			assertThat(service.addPlaylist("회식", "teddddst@test.com")).isEqualTo(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
//	@Test
	public void getPlaylistList(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.getPlaylistList("test@test.com")).isNotEmpty();
//			System.out.println(service.getPlaylistList("test@test.com"));
			assertThat(service.getPlaylistList("tesdddt@test.com")).isEmpty();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
//	@Test
	public void deletePlaylist(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.deletePlaylist("test@test.com", 2)).isNotEqualTo(true);
			assertThat(service.deletePlaylist("test@test.com", 100)).isEqualTo(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void playlistMusicList(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.playlistMusicList(1, "TJ")).isNotNull();
//			System.out.println(service.playlistMusicList(1, "TJ"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void  updatePlaylistTitle(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.updatePlaylistTitle("야홍", "test@test.com", 1)).isEqualTo(true);
//			assertThat(service.updatePlaylistTitle("야홍", "test@test.com", 100)).isEqualTo(false);
//			System.out.println(service.playlistMusicList(1, "TJ"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void deletePlayList(){
		MusicService service;
		try {
			service=new MusicService();
			assertThat(service.deletePlayList(1)).isEqualTo(true);
			assertThat(service.deletePlayList(100)).isEqualTo(false);
//			System.out.println(service.playlistMusicList(1, "TJ"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

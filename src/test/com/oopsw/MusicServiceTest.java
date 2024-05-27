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
		service = new MusicService();
		assertThat(service.getPlaylistSongCount(1, "TJ")).isEqualTo(1);
		assertThat(service.getPlaylistSongCount(999999999, "TJ")).isNotEqualTo(1);
	}
//	@Test
	public void getPlaylistTitle(){
		MusicService service;
		service=new MusicService();
		assertThat(service.getPlaylistTitle("test@test.com", 1)).isNotEmpty();
		assertThat(service.getPlaylistTitle("test@test.com", 5)).isEmpty();
		
	}
	//@Test
	public void addMusic(){
		MusicService service;
		service=new MusicService();
		assertThat(service.addMusic(79351, "TJ", "Little By Little", "Oasis", 1)).isTrue();
		assertThat(service.addMusic(79351, "TJ", "Little By Little", "Oasis", 1)).isFalse();	
	}
	@Test
	public void MyPlaylistCheckMusic(){
		MusicService service;
		service=new MusicService();
		assertThat(service.myPlaylistCheckMusic("test@test.com", 79351, "TJ")).isNotEmpty();
		System.out.println("MyPlaylistCheckMusic test : "+service.myPlaylistCheckMusic("test@test.com",79351, "TJ"));	
	}
//	@Test
	public void deleteSongInPlaylist(){
		MusicService service;
		service=new MusicService();
		assertThat(service.deleteSongInPlaylist(79351, "TJ", 1)).isEqualTo(true);
		assertThat(service.deleteSongInPlaylist(1793331, "TJ", 1)).isEqualTo(false);	
	}
//	@Test
	public void addPlaylist(){
		MusicService service;
		service=new MusicService();
		assertThat(service.addPlaylist("회식", "test@test.com")).isEqualTo(true);
		assertThat(service.addPlaylist("회식", "teddddst@test.com")).isEqualTo(false);	
	}
	
	
	//@Test
	public void getPlaylistList(){
		MusicService service;
		service=new MusicService();
		assertThat(service.getPlaylistList("test@test.com")).isNotEmpty();
System.out.println(service.getPlaylistList("test@test.com"));
		assertThat(service.getPlaylistList("tesdddt@test.com")).isEmpty();	
	}
//	@Test
	public void deletePlaylist(){
		MusicService service;
		service=new MusicService();
		assertThat(service.deletePlaylist("test@test.com", 2)).isNotEqualTo(true);
		assertThat(service.deletePlaylist("test@test.com", 100)).isEqualTo(false);	
	}
	//@Test
	public void playlistMusicList(){
		MusicService service;
		service=new MusicService();
		assertThat(service.playlistMusicList(1, "TJ")).isNotNull();
//			System.out.println(service.playlistMusicList(1, "TJ"));	
	}
	//@Test
	public void  updatePlaylistTitle(){
		MusicService service;
		service=new MusicService();
		assertThat(service.updatePlaylistTitle("야홍", "test@test.com", 1)).isEqualTo(true);
//			assertThat(service.updatePlaylistTitle("야홍", "test@test.com", 100)).isEqualTo(false);
//			System.out.println(service.playlistMusicList(1, "TJ"));	
	}
	//@Test
	public void deletePlayList(){
		MusicService service;
		service=new MusicService();
		assertThat(service.deletePlayList(1)).isEqualTo(true);
		assertThat(service.deletePlayList(100)).isEqualTo(false);
//			System.out.println(service.playlistMusicList(1, "TJ"));
	}
}

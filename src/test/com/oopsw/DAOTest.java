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
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.dao.KKDAO;
import com.oopsw.model.vo.KKVO;

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
	
	@Test
	public void addKKBookmark() {
		assertTrue(new KKDAO(conn).addKKBookmark("test@test.com", 3));
		assertFalse(new KKDAO(conn).addKKBookmark("test@test.com", 3));
	}
	
	@Test 
	public void deleteKKBookmark() {
		assertTrue(new KKDAO(conn).deleteKKBookmark("test@test.com", 3));
		assertFalse(new KKDAO(conn).deleteKKBookmark("test@test.com", 3));
	}
	
	
	@Test
	public void getRoomInfoList() {
		assertNotNull(new KKDAO(conn).getRoomInfoList(1));
	}
}

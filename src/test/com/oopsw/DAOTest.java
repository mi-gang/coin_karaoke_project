package test.com.oopsw;

import static org.junit.Assert.*;

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
	public void getNearRecommendKKList() {
		assertNull(new KKDAO(conn).getNearRecommendKKList("용산구"));
		assertNotNull(new KKDAO(conn).getNearRecommendKKList("금천구"));
	}
	
	@Test
	public void addKKBookmark() {
		/*
		boolean result = new KKDAO(conn).addKKBookmark("test@test.com", 6);
		assertTrue(result);
		System.out.println("첫 번째 북마크 추가 결과: " + result);
		// assertTrue(new KKDAO(conn).addKKBookmark("test@test.com", 5));
		result = new KKDAO(conn).addKKBookmark("test@test.com", 6);
		assertFalse(result);
		System.out.println("두 번째 북마크 추가 결과: " + result);
		*/
		assertTrue(new KKDAO(conn).addKKBookmark("test@test.com", 7));
	}

}

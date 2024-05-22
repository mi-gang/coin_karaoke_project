package test.com.oopsw;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.service.KKService;

public class KKServiceTest {
	private static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 1. driver loading
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("1. loading ok");
		// 2. connection
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "hr", "hr");
		System.out.println("2. connection ok");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// commit 또는 rollback을 결정
		// connection close 해주기
		conn.close();
	}

	/*// 노래방 별점 불러오기
	@Test
	public void getStarAvgByKK() {
		try {
			assertNotNull(new KKService().getStarAvgByKK(1));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	// 검색 결과 목록 불러오기
	@Test
	public void getKKList() {
		int[] tmp = {0,0,0,0};
		int countChkOption = 0;
		int[] tmp2 = {1,0,0,1};
		int cCO2 = 2;
		try {
			System.out.println("-------------------- getKKList '강남구' ------------");
//			assertNotNull(new KKService().getKKList(tmp, countChkOption, "강남구"));
			System.out.println("-------------------- getKKList '금천구' ------------");
//			assertNotNull(new KKService().getKKList(tmp, countChkOption, "금천구"));
			System.out.println("-------------------- getKKList 추가조건V ~~ '금천구' ------------");
			assertNotNull(new KKService().getKKList(tmp2, cCO2, "금천구"));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*// 해당 노래방 리뷰 불러오기
	@Test
	public void getReviewListByKK() {
		try {
			assertNotNull(new KKService().getReviewListByKK(1));
			assertNotNull(new KKService().getReviewListByKK(2));  // 리뷰 없음이면 null 아니고 size 0
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}

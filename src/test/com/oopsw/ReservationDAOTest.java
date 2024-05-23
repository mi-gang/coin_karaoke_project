package test.com.oopsw;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.dao.ReservationDAO;
import com.oopsw.model.vo.ReservationVO;

public class ReservationDAOTest {
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
		conn.close();
	}

//	@Test
	public void getUpcomingReservation() {
		ReservationDAO dao = new ReservationDAO(conn);
		// assertThat(dao.getUpcomingReservation("test@test.com")).isTrue();
		assertNotNull(dao.getUpcomingReservation("test@test.com"));
		System.out.println(dao.getUpcomingReservation("test@test.com"));
	}
	
	// @Test
	public void getReservationListByRoomId() {
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getReservationListByRoomId(1));
		System.out.println(dao.getReservationListByRoomId(1));
	}
	
	/** 예약 가능 시간 검증하기 */
//	@Test
	public void isValidTimeForReservation() {
		ReservationDAO dao = new ReservationDAO(conn);		
		LocalDateTime startTime = LocalDateTime.of(2024, 5, 23, 6, 30, 0);
		LocalDateTime endTime = LocalDateTime.of(2024, 5, 23, 10, 30, 0);
		assertNotNull(dao.isValidTimeForReservation(startTime, endTime, 1));
		System.out.println("isValidTimeForReservation : "+ dao.isValidTimeForReservation(startTime, endTime, 1));
		//System.out.println(dao.getReservationListByRoomId(1));
	}
	
//	@Test
	public void getUncompletedReservationList(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getUncompletedReservationList("test@test.com"));
		System.out.println(dao.getUncompletedReservationList("test@test.com"));
	}
	
//	@Test
	public void getCompletedReservationList(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getCompletedReservationList("test@test.com"));
		System.out.println(dao.getCompletedReservationList("test@test.com"));
	}
	
//	@Test
	public void getCanceledReservationList(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getCanceledReservationList("test@test.com"));
		System.out.println(dao.getCanceledReservationList("test@test.com"));
	}
	
//	@Test
	public void getOriginalReservationTime(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getOriginalReservationTime("test@test.com", 1));
		System.out.println(dao.getOriginalReservationTime("test@test.com", 1));
	}
	
//	@Test
	public void getAvailableExtraUsingTime(){
		ReservationDAO dao = new ReservationDAO(conn);	
		LocalDateTime endtime = LocalDateTime.of(2024, 5, 23, 10, 30, 0);
		assertNotNull(dao.getAvailableExtraUsingTime(1, endtime));
		System.out.println(dao.getAvailableExtraUsingTime(1, endtime));
	}
	
	@Test
	public void addTest(){
		ReservationDAO dao = new ReservationDAO(conn);	
		LocalDateTime endTime = LocalDateTime.now();

		ReservationVO reservationVO = new ReservationVO(0, 0, endTime, endTime, "test@test.com", 1, "df", 1, "df");
		dao.addReservation(reservationVO);
	}
	
	
}
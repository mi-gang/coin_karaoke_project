package test.com.oopsw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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

	//@Test
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
	
	@Test
	public void isValidTimeForReservation() {
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.isValidTimeForReservation(startTime, endTime, 1));
		System.out.println(dao.getReservationListByRoomId(1));
	}
	
	@Test
	public void getUncompletedReservationList(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getUncompletedReservationList("test@test.com"));
	}
	
	@Test
	public void getCompletedReservationList(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getCompletedReservationList("test@test.com"));
	}
	
	@Test
	public void getCanceledReservationList(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getCanceledReservationList("test@test.com"));
	}
	
	
	
	@Test
	public void getOriginalReservationTime(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getOriginalReservationTime("test@test.com", 1));
	}
	
	
	
	@Test
	public void getAvailableExtraUsingTime(){
		ReservationDAO dao = new ReservationDAO(conn);		
		assertNotNull(dao.getAvailableExtraUsingTime(1, endtime));
	}
}
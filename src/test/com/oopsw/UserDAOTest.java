package test.com.oopsw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oopsw.model.dao.UserDAO;
import com.oopsw.model.vo.UserVO;

public class UserDAOTest {
	static Connection conn = null;
	static UserDAO dao = null;
	static String userId = "test@test.com";
	static String password = "123";
	static String nickname = "테스트계정";
	static LocalDateTime birthDate = LocalDateTime.of(2024, 4, 1, 0, 0);
	

	@BeforeClass
	public static void init() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			conn = DriverManager.getConnection(url, "hr", "hr");
			conn.setAutoCommit(false);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Before
	public void setDao(){
		dao = new UserDAO(conn);
	}
	
	@After
	public void roll() throws SQLException{
		conn.rollback();
	}

	@Test
	public void connTest() throws SQLException {
	
		assertThat(conn).isNotNull();
		
	}

	@Test
	public void loginTest() throws SQLException{
		assertThat(dao.login("test@test.com", "123")).isTrue();
		assertThat(dao.login("test@test.com", "1234")).isFalse();
	}
	
	@Test
	public void addAndGetUserTest() throws SQLException{
		UserVO user = new UserVO(userId + "2", nickname + "2", birthDate, password);
		assertThat(dao.addUser(user)).isTrue();
		dao.getUser(userId + "2").equals(user);
		try{
			dao.addUser(user);
		} catch(SQLException e){
			assertThat(true).isTrue();
		}
	}
	
	@Test
	public void addUserTest() throws SQLException{
		UserVO user = new UserVO(userId + "2", nickname + "2", birthDate, password);
		assertThat(dao.addUser(user)).isTrue();
		user.setUserId(userId);
		try{
			dao.addUser(user);
		} catch(SQLException e){
			assertThat(true).isTrue();
		}
	}
	
	@Test
	public void isExistEmailTest() throws SQLException{
		assertThat(dao.isExistEmailTest(userId)).isTrue();
		assertThat(dao.isExistEmailTest(userId + "1")).isFalse();
	}
	
	@Test
	public void updatePasswordTest() throws SQLException{
		assertThat(dao.updatePassword(userId + "321", "321")).isFalse();
		assertThat(dao.updatePassword(userId, "321")).isTrue();
		assertThat(dao.login(userId, "321")).isTrue();
	}
	
	@Test
	public void updateAndGetNicknameTest() throws SQLException{
		assertThat(dao.updateNickname(userId + "321", "321")).isFalse();
		assertThat(dao.updateNickname(userId, "321")).isTrue();
		assertThat(dao.getNickname(userId).equals("321")).isTrue();
	}

}

package test.com.oopsw;

import static org.junit.Assert.*;


import org.junit.Test;

import com.oopsw.service.UserService;

public class UserServiceTest {

	@Test
	public void addBookmarkTest() {
		UserService service = new UserService();
		service.addKKBookmark("test@test.com", 1);
		System.out.println(123);
	}

}

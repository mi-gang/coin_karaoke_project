package test.com.oopsw;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.oopsw.service.UserService;

public class UserServiceTest {

	@Test
	public void test() {
		UserService service = new UserService();
		List list = new ArrayList();
		String[] tmp = new String[3];
		tmp[0] = "1";
		tmp[1] = "2";
		tmp[2] = "3";
		list.add(tmp);
		System.out.println(list.get(0)[1]);
	}

}

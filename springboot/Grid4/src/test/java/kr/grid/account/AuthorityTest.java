package kr.grid.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.grid.services.AuthorityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityTest {
	
	@Autowired
	private AuthorityService svc;

	@Test
	public void authorityTest() {
		svc.initAuthority();
	}

}
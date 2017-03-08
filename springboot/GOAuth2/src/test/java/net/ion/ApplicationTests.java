package net.ion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import net.ion.system.user.CustomUserDetailsService;
import net.ion.system.user.RoleEnum;
import net.ion.system.user.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	CustomUserDetailsService svc;

	@Test
	public void contextLoads() {
		String id = "admin";
		String password = "admin";
		String fullname = "관리자1";
		String email = "admin@this.site.net";
		String phone = "010-0000-0001";
		RoleEnum role = RoleEnum.ROLE_ADMIN;
		boolean isEnabled = true;
		svc.createUser(id, password, fullname, email, phone, role, isEnabled);
		
		id = "oper";
		password = "oper";
		fullname = "운영자1";
		email = "oper1@gmail.com";
		phone = "010-0001-0002";
		role = RoleEnum.ROLE_OPERATOR;
		isEnabled = true;
		svc.createUser(id, password, fullname, email, phone, role, isEnabled);

		// Access Token 용
		id = "chatclient";
		password = "chatclient";
		fullname = "채팅사용자";
		email = "chatclient@gmail.com";
		phone = "010-0001-0003";
		role = RoleEnum.ROLE_CLIENT;
		isEnabled = true;
		svc.createUser(id, password, fullname, email, phone, role, isEnabled);
		
		Page<UserEntity> users = svc.findAllUsers(null);
		logger.debug("{}",users.getContent());
	}

}
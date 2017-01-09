package kr.grid.account;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kr.grid.entities.Authority;
import kr.grid.entities.User;
import kr.grid.repositories.AuthorityRepository;
import kr.grid.repositories.UserRepository;
import kr.grid.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	final static String adminEmail = "admin@grid.kr";
	final static String testEmail  = "test@grid.kr";

	@Autowired private UserRepository userDao;
	@Autowired private UserService userService;
	@Autowired private AuthorityRepository authDao;

	@Test
	public void initUser() {
		User user = null;
		// Group group = null;
		
		// delete users
		user = userDao.findByUsername(adminEmail);
		if(user!=null) userDao.delete(user);
		user = userDao.findByUsername(testEmail);
		if(user!=null) userDao.delete(user);
		
//		group = groupDao.findById(0);
//		if(group == null) {
//			GroupTest groupTest = new GroupTest();
//			groupTest.groupTest();
//		}

		Authority auth; 
		List<Authority> authorities = new ArrayList<Authority>();
		user = userDao.findByUsername(adminEmail);
		PasswordEncoder passwordEncoder = userService.passwordEncoder();
//		List<Group> groups = new ArrayList<Group>();
//		groups.add(group);

		if(user == null) {
			user = new User();
			user.setUsername(adminEmail);
			user.setFullname("관리자");
			user.setPassword(passwordEncoder.encode("1111"));
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			// user.setGroups(groups);
			auth = authDao.findByAuthority("ROLE_ADMIN");
			if(auth == null) {
				logger.error("ADMIN authority not found");
				return;
			}
			authorities.add(auth);
			user.setAuthorities(authorities);
			userDao.save(user);
			logger.debug("{}:{} created",user.getUsername(),user.getPassword());
		} else 
			logger.debug(user.getUsername() + " user already exist");
		
		
		authorities.clear();
//		group = groupDao.findById(1);
//		groups.clear();
//		groups.add(group);
//		group = groupDao.findById(99);
//		groups.add(group);

		user = userDao.findByUsername(testEmail);
		if(user == null) {
			user = new User();
			user.setUsername(testEmail);
			user.setFullname("시험용");
			user.setPassword(passwordEncoder.encode("1111"));
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			// user.setGroups(groups);
			auth = authDao.findByAuthority("ROLE_USER");
			if(auth == null) {
				logger.error("USER authority not found");
				return;
			}
			authorities.add(auth);
			user.setAuthorities(authorities);
			userDao.save(user);
			logger.debug("{}:{} created",user.getUsername(),user.getPassword());
			return;
		}
		logger.debug(user.getUsername() + " user aleady exist");
	}

}

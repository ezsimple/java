package kr.grid.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import kr.grid.entities.Authority;
import kr.grid.repositories.AuthorityRepository;

@Service
public class AuthorityService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthorityRepository dao;

	public void initAuthority() {
		/*
		 * 0 : admin
		 * 1 : user
		 * 99: not accepted
		 */
		Authority auth;
		
		final String adminRole = "ROLE_ADMIN";
		final String userRole  = "ROLE_USER";
		
		auth = dao.findByAuthority(adminRole);
		if(auth == null) {
			dao.save(new Authority(adminRole));
			logger.debug("{} insert",adminRole);
		}

		auth = dao.findByAuthority(userRole);
		if(auth == null) {
			dao.save(new Authority(userRole));
			logger.debug("{} insert",userRole);
		} 

		Page<Authority> authorities = dao.findAll();
		for(Authority a : authorities) {
			logger.debug("{} found", a.getAuthority());
		}
		logger.debug("authority id 초기화 완료");
	}

}

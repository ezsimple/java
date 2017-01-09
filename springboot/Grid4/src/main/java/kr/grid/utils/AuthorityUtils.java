package kr.grid.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.grid.entities.Authority;
import kr.grid.repositories.AuthorityRepository;

public class AuthorityUtils {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private AuthorityRepository authDao;
	
	public Authority getAuthority(final String authority) {
		return authDao.findOne(authority);
	}

}

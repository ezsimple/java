package kr.grid.services;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.grid.entities.Authority;
import kr.grid.entities.User;
import kr.grid.repositories.AuthorityRepository;
import kr.grid.repositories.UserRepository;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class UserService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// @Autowired private Environment env; (요긴한 Injection 입니다)
	@Autowired private UserRepository userDao;
	@Autowired private AuthorityRepository authDao;

	@Value("${site.email.admin}")
	private String adminEmail = "not found";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("찾고있음 ==> {}",username);
		User user = userDao.findByUsername(username);
		if(user==null) logger.error("{} 없음",username);
		return user;
	}
	
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}
	
	// 현재 로그인한 사용자 객체를 가져온다 
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails)
			return (User) principal;
		return null;
	}
	
	public User createUser(String username, String fullname, String password) {
		User user = userDao.findByUsername(username);
		if(user!=null) {
			logger.error("{} already exist",username);
			return null;
		}
		List<Authority> authorities = new ArrayList<Authority>();
		user = new User();
		user.setUsername(username);
		user.setFullname(fullname);
		user.setPassword(passwordEncoder.encode(password));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);

		// String adminEmail = env.getProperty("site.email.admin");
		logger.debug(adminEmail);
		String role = "ROLE_USER";
		if(this.adminEmail.equals(username.trim()))
			role = "ROLE_ADMIN";
		logger.debug(username+":"+fullname+":"+role);

		Authority auth = authDao.findByAuthority(role);
		if(auth == null) {
			logger.error("{} authority not found",role);
			return null;
		}
		authorities.add(auth);
		user.setAuthorities(authorities);
		userDao.save(user);

		return user;
	}

	public Page<User> listUser(Pageable pageable) {
		return userDao.findAll(pageable);
	}

//	public Page<User> searchUser(String name, Pageable pageable) {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchAllQuery())
//                .withFilter(boolFilter().must(existsFilter("name")))
//                .withPageable(new PageRequest(0,10))
//                .build();
//	}
//	
}

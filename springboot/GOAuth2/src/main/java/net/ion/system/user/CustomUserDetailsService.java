package net.ion.system.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.ion.repository.CustomUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired 
	CustomUserRepository dao;

	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		try {
			Optional<UserEntity> user = dao.findOneById(id);
			if(user == null){
				throw new UsernameNotFoundException("UserId " + id + " not found");
			}
			return new CustomUser(user.get());
		} catch (Exception e) {
			throw new AuthenticationServiceException(e.getMessage());
		}
	}
	
	private boolean isEmpty(String id, String password, String fullname, String email, String phone) {
		if(StringUtils.isEmpty(id)) return true;
		if(StringUtils.isEmpty(password)) return true;
		if(StringUtils.isEmpty(fullname)) return true;
		if(StringUtils.isEmpty(email))return true;
		if(StringUtils.isEmpty(phone)) return true;
		return false;
	}

	public UserEntity createUser(String id, String password, String fullname, String email, String phone, RoleEnum role, boolean isEnabled) {
		UserEntity user = dao.findOne(id);
		if(user != null) {
			logger.error("createUser Fail : {} already exist", user.getId());
			return null;
		}
		
		if(isEmpty(id,password,fullname,email,phone)) {
			logger.error("createUser Fail : id:{},password:{},fullname:{},email:{},phone:{} has empty field",id,password,fullname,email,phone);
			return null;
		}

		user = new UserEntity();
		user.setFullname(fullname);
		user.setId(id);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setPhone(phone);
		user.setRole(role);
		user.setEnabled(isEnabled);
		
		dao.save(user);

		return user;
	}

	public UserEntity updateUser(String id, String password, String fullname, String email, String phone, RoleEnum role, boolean isEnabled) {
		UserEntity user = dao.findOne(id);
		if(user == null) {
			logger.error("updateUser Fail : {} does not exist", id);
			return null;
		}
		if(!StringUtils.isEmpty(fullname))
			user.setFullname(fullname);
		
		if(!StringUtils.isEmpty(password))
			user.setPassword(passwordEncoder.encode(password));
		
		if(!StringUtils.isEmpty(email))
			user.setEmail(email);

		if(!StringUtils.isEmpty(phone))
			user.setPhone(phone);

		user.setRole(role);
		user.setEnabled(isEnabled);
		
		dao.save(user);

		return user;
	}

	public UserEntity deleteUser(String id) {
		UserEntity user = dao.findOne(id);
		if(user == null) {
			logger.error("deleteUser Fail : {} does not exist", id);
			return null;
		}
		dao.delete(user);
		return user;
	}

	public Page<UserEntity> findAllUsers(
			@PageableDefault(sort = { "fullname" }, direction = Direction.DESC, size = 1000) Pageable pageable) {
		return dao.findAll(pageable);
	}

}
package net.ion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import net.ion.system.user.UserEntity;

public interface CustomUserRepository extends ElasticsearchRepository<UserEntity, String> {
	 Optional<UserEntity> findOneById(String id);
	 Optional<UserEntity> findByEmail(String email);
	 List<UserEntity> findByFullname(String fullname);
}
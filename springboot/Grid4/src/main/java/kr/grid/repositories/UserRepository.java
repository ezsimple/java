package kr.grid.repositories;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import kr.grid.entities.User;

public interface UserRepository extends ElasticsearchRepository<User, String> {
	List<User> findByFullname(String fullname);
	User findByUsername(String username);
}
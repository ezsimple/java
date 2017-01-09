package kr.grid.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import kr.grid.entities.Authority;
import kr.grid.entities.Group;

public interface AuthorityRepository extends ElasticsearchRepository<Authority, String> {
	Page<Authority> findAll();
	Authority findByAuthority(String role);
}
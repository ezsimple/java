package kr.grid.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import kr.grid.entities.Group;

public interface GroupRepository extends ElasticsearchRepository<Group, Long> {
	Page<Group> findAll();
	Group findById(int id);
}

package kr.grid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.grid.entities.Group;
import kr.grid.repositories.GroupRepository;

@Service
public class GroupService {
	
	@Autowired private GroupRepository dao;
	
	public Group save(Group group) {
		return dao.save(group);
	}

}

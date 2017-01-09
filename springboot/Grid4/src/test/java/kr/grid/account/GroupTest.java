package kr.grid.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import kr.grid.entities.Group;
import kr.grid.repositories.GroupRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private GroupRepository groupDao;
	
	@Test
	public void groupTest() {
		/*
		 * 0 : admin
		 * 1 : user
		 * 99: not accepted
		 */
		Group group;
		
		logger.debug(groupDao.findAll().toString());
		
		group = groupDao.findById(0);
		if(group == null) {
			groupDao.save(new Group(0, "admin"));
		}

		group = groupDao.findById(1);
		if(group == null) {
			groupDao.save(new Group(1, "user"));
		}
		
		group = groupDao.findById(99);
		if(group == null) {
			groupDao.save(new Group(99,"not accept"));
		}
		
		Page<Group> grp = groupDao.findAll();
		List<Group> initGroup = new ArrayList<Group>();
		for(Group g : grp) {
			logger.debug(g.getComment());
			if(g.getId() == 1  || g.getId() == 99) 
				initGroup.add(g);
		}
		
		String initGroupList = StringUtils.join(initGroup);
		logger.debug(initGroupList);
		logger.debug("group id initialiezed");
	}

}

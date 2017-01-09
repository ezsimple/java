package kr.grid.elasticsearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.grid.entities.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ElasticTemplateTest {
	
    @Autowired
    private ElasticsearchTemplate template;
    
    @Before
    public void before() {
        template.deleteIndex(Group.class);
    }
    
    @Test
    public void test() {
    	template.createIndex(Group.class);
    	template.putMapping(Group.class);
    }

}

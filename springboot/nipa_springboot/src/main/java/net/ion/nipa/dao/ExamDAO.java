package net.ion.nipa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ion.nipa.common.dao.AbstractDAO;

@Repository
public class ExamDAO extends AbstractDAO {

	@Autowired
	private SqlSession sqlSession;
	
    public String getCurrentDateTime() {
        return sqlSession.selectOne("exam.getCurrentDateTime");
    }
    
	public List<Map<String, Object>> getTestList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("exam.getTestList", map);
	};

}

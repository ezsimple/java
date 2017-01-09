package kr.grid.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExamDAO {

	@Autowired
	private SqlSession sqlSession;
	
    public String getCurrentDateTime() {
        return sqlSession.selectOne("examMapper.getCurrentDateTime");
    }
	
}
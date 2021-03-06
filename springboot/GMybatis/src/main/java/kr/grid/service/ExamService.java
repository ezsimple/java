package kr.grid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.grid.repository.ExamDAO;

@Service
public class ExamService {

    @Autowired
    private ExamDAO dao;
    
    public String getNow() {
    	return dao.getCurrentDateTime();
    }

}
package net.ion.nipa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import net.ion.nipa.common.resolver.CommandMap;
import net.ion.nipa.dao.ExamDAO;

@Service
public class ExamService extends AbstractService {

	@Autowired
	private ExamDAO dao;
	
	public String getNow() {
		return dao.getCurrentDateTime();
	}
	
	public void getTestList(Model model, CommandMap commandMap) throws Exception {
		model.addAttribute("test", getResult(dao.getTestList(commandMap.getMap())));
	}

}
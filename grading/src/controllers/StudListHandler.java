package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyschoolDAO;
import dao.StudentDTO;

public class StudListHandler implements CommonHandler {
	
	
	MyschoolDAO dao;
	
	public StudListHandler setMyschoolDAO(MyschoolDAO dao) {
		this.dao = dao;
		return this;
	}
	
	

	@Override
	public String process(HashMap<String, Object> map) {
		
		
		ArrayList<StudentDTO> list = dao.getStudentList();
		map.put("STUDLIST", list);
		
		return "studList.jsp";
	}

}

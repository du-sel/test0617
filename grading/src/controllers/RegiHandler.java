package controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActiveLectureDTO;
import dao.GradeStudentDTO;
import dao.MyschoolDAO;
import dao.StudentDTO;

public class RegiHandler implements CommonHandler {
	
	MyschoolDAO dao;
	
	public RegiHandler setMyschoolDAO(MyschoolDAO dao) {
		this.dao = dao;
		return this;
	}
	

	@Override
	public String process(HashMap<String, Object> map) {
		
		System.out.println("regi handler DAO: "+dao);
		ArrayList<ActiveLectureDTO> lecList = dao.getLectureList();
		map.put("LECLIST", lecList);
		
		
		
		// 과목별 수강학생의 리스트를 담을 맵
		HashMap<String, ArrayList<GradeStudentDTO>> studMap = new HashMap<>();
		
		for(int i = 0; i<lecList.size(); i++) {
			String lecId = lecList.get(i).getId();
			ArrayList<GradeStudentDTO> studList = dao.getLectureStudents(lecId);
			studMap.put(lecId, studList);
		}
		
		map.put("STUDMAP", studMap);
		
		
		return "regi.jsp";
	}

}

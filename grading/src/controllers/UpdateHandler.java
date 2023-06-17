package controllers;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GradeStudentDTO;
import dao.MyschoolDAO;

public class UpdateHandler implements CommonHandler {
	

	MyschoolDAO dao;
	
	public UpdateHandler setMyschoolDAO(MyschoolDAO dao) {
		this.dao = dao;
		return this;
	}
	
	

	@Override
	public String process(HashMap<String, Object> map) {
		
		int res = 0;
		
		String lecId = (String)map.get("lecId");
		
		ArrayList<GradeStudentDTO> stuList = dao.getLectureStudents(lecId);
		String[] scoreList = (String[]) map.get("scoreList");
		
		for(int i = 0; i<stuList.size(); i++) {
			GradeStudentDTO stu = stuList.get(i);
			String studId = stu.getStudId();
			
			int score = Integer.parseInt(scoreList[i]);
			
			res = dao.updateScore(lecId, studId, score);			
		}
		
		if(res > 0) {
			return "redirect:/grading/regi.do";			
		} else {
			return "";
		}
		
		
	}

}

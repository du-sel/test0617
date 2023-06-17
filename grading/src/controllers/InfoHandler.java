package controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyschoolDAO;
import dao.ReportDTO;

public class InfoHandler implements CommonHandler {

	MyschoolDAO dao;
	
	public InfoHandler setMyschoolDAO(MyschoolDAO dao) {
		this.dao = dao;
		return this;
	}
	
	
	@Override
	public String process(HashMap<String, Object> map) {
		
		System.out.println("인포 들어왔습니다~");
		System.out.println(map.get("id"));
		
		ReportDTO rep = dao.getReport((String)map.get("id"));
		map.put("REPORT", rep);
		
		return "/info.jsp";
	}

}

package controllers;

import java.util.ArrayList;
import java.util.HashMap;


import javax.servlet.http.HttpSession;

import dao.MyschoolDAO;
import dao.StudentDTO;

public class LoginHandler implements CommonHandler {
	
	MyschoolDAO dao;
	
	public LoginHandler setMyschoolDAO(MyschoolDAO dao) {
		this.dao = dao;
		return this;
	}

	@Override
	public String process(HashMap<String, Object> map) {
		
		String view = null;
		HttpSession session = (HttpSession)map.get("session");
		
		String id = (String)map.get("id");
		String pw = (String)map.get("pw");
		
		
		
		if(id.equals("admin") && pw.equals("1234")) {
//			if(session != null) {			
//				session.invalidate();
//				session = req.getSession(true);
//			}
			session.setAttribute("ACCOUNT", "admin");
			session.setAttribute("USERNAME", "관리자");
			
			view = "redirect:/grading/regi.do";
			
		} else if(dao.isStudent(id) && pw.equals("1234")) {
//			if(session != null) {			
//				session.invalidate();
//				session = req.getSession(true);
//			}
			session.setAttribute("ACCOUNT", id);
			String name = dao.getStudent(id).getName();
			session.setAttribute("USERNAME", name);
			
			view = "redirect:/grading/info.do?id="+id+"&pw="+pw;
			
		} else if((dao.isStudent(id) || id.equals("admin")) && !pw.equals("1234")) {
			if(session.getAttribute("NOTFOUND") != null) {
				session.setAttribute("NOTFOUND", null);
			}
			if(session.getAttribute("WRONG") == null) {
				session.setAttribute("WRONG", 1);				
			} else {
				int wrong = (Integer)session.getAttribute("WRONG");
				session.setAttribute("WRONG", ++wrong);
			}
			view = "login.jsp";
		} else {
			if(session.getAttribute("WRONG") != null) {
				session.setAttribute("WRONG", null);
			}
			session.setAttribute("NOTFOUND", "notfound");
			view = "login.jsp";
		}
	
		
		return view;
	}

}

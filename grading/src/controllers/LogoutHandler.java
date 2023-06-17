package controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements CommonHandler {

	@Override
	public String process(HashMap<String, Object> map) {
		
		HttpSession session = (HttpSession)map.get("session");
		
		if(session != null) {
			session.invalidate();
		}
		
		return "login.jsp";
	}

}

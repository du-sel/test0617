package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.CommonHandler;
import controllers.InfoHandler;
import controllers.LoginHandler;
import controllers.LogoutHandler;
import controllers.RegiHandler;
import controllers.StudListHandler;
import controllers.UpdateHandler;
import dao.GradeStudentDTO;
import dao.StudentDTO;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext context = this.getServletContext();
		resp.setContentType("text/html; charset=utf-8");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("session", req.getSession());
		
		String servletPath = req.getServletPath();
		System.out.println("servletPath: "+servletPath);
		RequestDispatcher dispatcher = null;
		
		
		CommonHandler handler = (CommonHandler)context.getAttribute(servletPath);
		
		
		try {
			
			if(servletPath.equals("/login.do")) {

				map.put("id", req.getParameter("id"));
				map.put("pw", req.getParameter("pw"));
				
			}
			
			else if(servletPath.equals("/info.do")) {
				// 리스트에서 들어온 거라면 
				if(req.getParameter("pw") == null) {
					map.put("id", req.getParameter("id"));
				} 
				// 학생이 로그인해서 들어온거라면 
				else {
					map.put("id", req.getParameter("id"));
					map.put("pw", req.getParameter("pw"));
				}
			}
			
			else if(servletPath.equals("/regi.do")) {

			}
			
			else if(servletPath.equals("/studList.do")) {
				
			}
			
			else if(servletPath.equals("/update.do")) {
				map.put("lecId", req.getParameter("lecId"));
				map.put("scoreList", req.getParameterValues("score"));
			}
			
			else if(servletPath.equals("/logout.do")) {
				
			}
			
	
		
		
		
			
			// *********************************************************************
			
			System.out.println("map: "+map);
			String viewURL = handler.process(map);
			
	
			
			for(String key : map.keySet()) {
				System.out.println("밸류값: "+map.get(key));
				req.setAttribute(key, map.get(key));
			}
			
			
			
			if(viewURL.startsWith("redirect:")) {
				System.out.println("섭스트링: "+viewURL.substring(9));
				resp.sendRedirect(viewURL.substring(9));
				return;
			} else {
				System.out.println("viewURL: "+viewURL);
				dispatcher = req.getRequestDispatcher(viewURL);
				dispatcher.include(req, resp);
			}
			
			
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}

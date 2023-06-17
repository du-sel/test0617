package listener;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import controllers.InfoHandler;
import controllers.LoginHandler;
import controllers.LogoutHandler;
import controllers.RegiHandler;
import controllers.StudListHandler;
import controllers.UpdateHandler;
import dao.MyschoolDAO;


@WebListener
public class ContextLoaderListener implements ServletContextListener {

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {

			
			ServletContext context = sce.getServletContext();
			InitialContext itContext = new InitialContext();
			
			DataSource ds = (DataSource)itContext.lookup("java:comp/env/jdbc/myschool");
		
			MyschoolDAO dao = new MyschoolDAO();
			dao.setDataSource(ds);
			
			
			context.setAttribute("IDLIST", dao.getStudentList());
			
			context.setAttribute("/login.do", new LoginHandler().setMyschoolDAO(dao));
			context.setAttribute("/info.do", new InfoHandler().setMyschoolDAO(dao));
			context.setAttribute("/regi.do", new RegiHandler().setMyschoolDAO(dao));
			context.setAttribute("/studList.do", new StudListHandler().setMyschoolDAO(dao));
			context.setAttribute("/update.do", new UpdateHandler().setMyschoolDAO(dao));
			context.setAttribute("/logout.do", new LogoutHandler());
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

}

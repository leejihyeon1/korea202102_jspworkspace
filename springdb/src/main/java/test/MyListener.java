package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 웹 어플리케이션을 가동할때 호출되는 메세지
		System.out.println("서버 가동하네요");
		
		ServletContext servletContext=sce.getServletContext();
		String value=servletContext.getInitParameter("야호");
		servletContext.setAttribute("obj", value);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 웹 어플리케이션을 중지할때 호출되는 메세지
		System.out.println("서버 중지하네요");
	}

}

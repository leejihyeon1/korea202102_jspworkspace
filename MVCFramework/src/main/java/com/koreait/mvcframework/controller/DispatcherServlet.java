package com.koreait.mvcframework.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvcframework.controller.blood.BloodController;
import com.koreait.mvcframework.controller.movie.MovieController;

//이 서블릿은 우리의 웹어플리케이션의 모든~~~요청을 일단 받는 진입점 컨트롤러임
public class DispatcherServlet extends HttpServlet{
	/*모든 컨트롤러의 업무 순서
	 * 1) 요청을 받는다
	 * 2) 요청을 분석한다 (어떤 하위 컨트롤러에게 업무를 전달할지를 알아야 하므로)
	 * 3) 알맞는 로직 객체에 일 시킨다(하위 컨트롤러가..)
	 * 4) 결과가 있다면 결과를 저장한다
	 * 5) 결과를 보여준다
	 * */
	Properties props;
	FileReader reader;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();//웹 어플리케이션의 정보를 얻기 위한 객체
		String path=context.getRealPath("/WEB-INF/mapping.data");
		props=new Properties();
		try {
			reader=new FileReader(path);
			props.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1단계: 요청을 받는다
		System.out.println("요청받음");
		
		//2단계 : 어떤 하위 컨트롤러에게 업무를 할당할지를 분석
		//클라이언트가 요청시 사용한 url자체가 의미를 부여할 수 있는 수단이 되므로,
		//파라미터가 아닌, 요청 url을 이용한 요청분석을 해보자
		String uri=request.getRequestURI();
		RequestDispatcher dis=null;
		Controller controller=null;
		
		String className=props.getProperty(uri);//if문을 대신함
		
		try {
			//타겟 클래스를 동적으로 static영역으로 로드
			Class controllerClass=Class.forName(className);
			//static 영역으로 올라온 클래스 원본을 대상으로 인스턴스 한개를 생성해보자!
			controller=(Controller) controllerClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String viewName=controller.getViewName();	
		dis=request.getRequestDispatcher(viewName);
		controller.doRequest(request, response);
		dis.forward(request, response);

	}
}

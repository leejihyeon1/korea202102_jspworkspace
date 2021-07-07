package com.koreait.model2app.controller;

import java.io.File;
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

/*웹 클라이언트의 모든~~요청을 받는 유일한 진입점 서블릿(요청을 분석하여 어떤 하위컨트롤러가 요청을 전담할지 결정짓고
 * 해당 하위 컨트롤러가 업무를 마친 후 엔, 결과를 다시 클라이언트에게 전달 즉 응답을 처리한다)
 * 1) 요청을 받는다 2)요청 분석 5) 응답 처리*/
public class DispatcherServlet extends HttpServlet{
	//아래의 객체들은 적어도 분석하기 전에 미리 메모리에 올라와있어야함
	Properties props;//java.util 컬렉션 프레임웍 객체 중 map의 자식
	FileReader reader;//properties객체는 자체적으로 파일을 접근할 수 없기 때문에 파일 스트림이 필요함
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		props=new Properties();
		try {
			//파일의 경로는 개발자의 육안이 아닌 프로그래밍적으로 얻어와야, 이 웹어플리케이션을 윈도우 아닌
			//다른 플랫폼에서 실행할때 문제가 되지 않음
			ServletContext context=config.getServletContext();//application 내장 객체의 원형
			//유지 보수성을 높이려면 설정정보등은 자바 코드안에 두기 보다는 외부 설정파일에 두어서, 변경하기 쉽게
			//처리하는 방식이 일반적이다..
			String realPath=context.getRealPath(config.getInitParameter("contextConfigLocation"));
			reader=new FileReader(new File(realPath));
			props.load(reader);//properties객체가 스트림을 이용하는 시점 즉. 파일을 검색하기 위한 준비 끝!
			
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
		//하위 컨트롤러에게 request,response객체를 전달하기 전에 공통적인 기능이 있다면, 형님 컨트롤러에서 
		//처리해줘야 코드 중복을 피할 수 있다!(안해도 되지만 하는게 좋음)
		request.setCharacterEncoding("utf-8");
		
		//2) 요청분석(uri분석)
		String uri=request.getRequestURI();
		//if문 대신 props파일을 탐색하기
		//이렇게 매 요청마다 처리할 로직을 전담 객체를 1:1 부여하는 방식을 가리켜 command pattern이라 한다
		String className=props.getProperty(uri);
		System.out.println(uri+"요청에 동작할 클래스명은 "+className);
		Controller controller=null;
		try {
			//클래스 이름을 이용하여 클래스 load하기!
			Class controllerClass=Class.forName(className);
			
			//파일에 명시된 클래스명을 이용하여 동적으로 인스턴스를 생성하는 방법==팩토리 패턴(factory pattern)
			controller=(Controller) controllerClass.newInstance();//하위 컨트롤러 생성
			
			controller.execute(request, response);//하위 컨트롤러 동작
			
			
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
		
		//5) 응답정보를 이용한 응답 처리 즉 결과 보여주기
		//결과는 MVC중 View담당임 그래서 현재와 다른 jsp에서 처리
		//주의!! 응답을 하면 네트워크 끊기도 요청프로세스가 종료되므로, 응답을 하지않고 원하는 jsp 자원에 포워딩
		String viewName=controller.getViewName();// "/blood/result.jsp" 들어있음
		//넘겨받은 viewname을 이용하여 다시 mapping 파일을 검색
		String viewPage=props.getProperty(viewName);
		
		
		if(controller.isForward()) {//포워딩일 경우(형님한테 넘길 데이터가 있는경우)
			RequestDispatcher dis=request.getRequestDispatcher(viewPage);
			dis.forward(request, response);//포워딩 시작
			
		}else {//단지 재접속만 명령하는 경우 redirect==location.href
			response.sendRedirect(viewPage);//지정한 url로 재접속
			
		}
		
	}
	
	//서블릿의 생명주기 메서드 중, 서블릿 소멸시 호출되는 destroy() 재정의 
	@Override
	public void destroy() {
		if(reader!=null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

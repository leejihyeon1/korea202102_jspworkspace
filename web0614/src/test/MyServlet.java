package test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter; //javase
import java.io.IOException;

//jsp없이도, 웹서버측의 기술을 구현할 수 있고, 아래와 같이 
//서버에서 해석 및 실행되는 클래스를 가리켜 서블릿(Servlet)이라 하며
//javaEE -- java ee for  kit

//서블릿 클래스는 웹서버에서 실행되며, 클라이언트의 웹 요청을 받고, 응답을 처리하는 전담 클래스이다!
//따라서 jsp 대신 서블릿만으로도 웹개발이 가능하다!

//서블릿은 생명주기 메서드라 불리는 주요 메서드가 존재하며, 이 생명주기 메서드는 
//하나의 서블릿 객체가 태어낫 일하며, 소멸하는 과정과 관련한 주요 메서드이다
//서블릿 클래스의 위치가 WEB-INF
public class MyServlet extends HttpServlet{
	//서블릿 인스턴스가 태어난 후, 서블릿의 초기화 작업시 호출되는 메서드 
	public void init(){
		System.out.println("저 태어나서, 초기화 완료");
	}

	//초기화를 완료한 서블릿이, 웹 클라이언트의 요청을 처리할때 동작하는 메서드
	public void service(HttpServletRequest request, HttpServletResponse response){
	System.out.println("클라이언트의 요청을 처리합니다");
	//웹 브라우저에 응답정보를 구성하여 응답하기
	try{
		response.setCharacterEncoding("utf-8");//응답정보의 헤더 인코딩을 utf-8로
		PrintWriter out=response.getWriter();//클라이언트와 연결된 출력스트림
		out.print("my servlet test 성공");
	}catch(IOException e){
		e.printStackTrace();
	}

}

	//서블릿이 소멸할 때 호출되는 메서드
	public void destroy(){
		System.out.println("저 갑니다");
	}
}

<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//service() 메서드 영역==스크립틀릿 영역
	out.print("제가 업로드 처리할게요");
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String myfile=request.getParameter("myfile");//파일 자체는 아님
	
	//업로드 처리 객체인 MultipartRequest를 사용해보자
	//생성자 호출에 의해 업로드 완료
	MultipartRequest multi=new MultipartRequest(request,"C:/korea202102_jspworkspace/site0617/src/main/webapp/data");
	out.print("업로드 완료");
	//out.print(title+"<br>");
	//out.print(writer+"<br>");
	//out.print(content+"<br>");
	//out.print(myfile+"<br>");
%>
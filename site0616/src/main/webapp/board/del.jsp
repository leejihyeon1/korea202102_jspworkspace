<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=null;
	PreparedStatement pstmt=null;
	
	//오라클 접속 
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
	
	String board_id = request.getParameter("board_id"); //파라미터 받기!!
	String sql="delete from board where board_id="+board_id;
	
	out.print(sql);
	
	pstmt=con.prepareStatement(sql);
	int result=pstmt.executeUpdate();
	
	out.print("<script>");
	if(result==0){
		out.print("alert('삭제실패');");
		out.print("history.back();");
	}else{
		out.print("alert('삭제성공');");
		out.print("location.href='/board/list.jsp';");
	}
	out.print("</script>");
	
	pstmt.close();
	con.close();
%>
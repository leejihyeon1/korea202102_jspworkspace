<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//이  jsp는 디자인이 필요없고, 단지 수정과 관련된 dB연동만 하면 된다
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=null;
	PreparedStatement pstmt=null;
	
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
	
	//클라인투인 detail.jsp 로부터 4개의 파라미터 받자!! (한글 인코딩해서)
	request.setCharacterEncoding("utf-8");
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String board_id=request.getParameter("board_id");
	
	out.print("title is "+title+"<br>");
	out.print("writer is "+writer+"<br>");
	out.print("content is "+content+"<br>");
	out.print("board_id is "+board_id+"<br>");
	
	String sql="update board set title=?, writer=?, content=? where board_id=?";
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1, title);
	pstmt.setString(2, writer);
	pstmt.setString(3, content);
	pstmt.setInt(4, Integer.parseInt(board_id));
	
	int result=pstmt.executeUpdate(); //쿼리수행 
	
	out.print("<script>");
	if(result==0){
		out.print("alert('수정실패');");
		out.print("history.back();");
	}else{
		out.print("alert('수정성공');");
		out.print("location.href='/board/detail.jsp?board_id="+board_id+"';");
	}
	out.print("</script>");
	
	if(pstmt!=null)pstmt.close();	
	if(con!=null)con.close();
%>

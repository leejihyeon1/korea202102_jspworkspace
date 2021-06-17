//목록 요청을 처리하는 서블릿
//서블릿이란 javaEE기반의 서버에서 실행될 수 있는 클래스이다..하지만 서블릿만으로는 개발시
//디자인 처리에 너무 많은 
package servlet.board;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ListServlet extends HttpServlet{
	//요청 처리 메서드
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter out=response.getWriter();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try{
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//접속
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
			if(con!=null){
				out.print("connection ok <br>");
				//sql문 실행
				String sql="select * from board order by board_id desc";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();

				//rs를 html 표 table로 출력
				out.print("<table width='100%' border='1px'>");
				
				out.print("<tr>");
				out.print("<th>No</th>");
				out.print("<th>title</th>");
				out.print("<th>writer</th>");
				out.print("<th>regdate</th>");
				out.print("<th>hit</th>");
				out.print("</tr>");

				//반복문으로 tr처리
				while(rs.next()){
					out.print("<tr>");
					out.print("<td>No</td>");
					out.print("<td>"+rs.getString("title")+"</td>");
					out.print("<td>"+rs.getString("writer")+"</td>");
					out.print("<td>"+rs.getString("regdate")+"</td>");
					out.print("<td>"+rs.getInt("hit")+"</td>");
					out.print("</tr>");
				}
			}else{
				out.print("connection fail <br>");
			}
		}catch(ClassNotFoundException e){//java.lang에 있기 때문에 import할 필요 없다
			e.printStackTrace();
		}catch(SQLException e){//java.lang에 있기 때문에 import할 필요 없다
			e.printStackTrace();
		}finally{
			//접속해제
			if(rs!=null){
				try{rs.close();}catch(SQLException e){}
			}
			if(pstmt!=null){
				try{pstmt.close();}catch(SQLException e){}
			}
			if(con!=null){
				try{con.close();}catch(SQLException e){}
			}
		}
	}
}
<%@page import="com.koreait.site0622.model.member.dao.MybatisMemberDAO"%>
<%@page import="com.koreait.site0625.util.message.MessageObject"%>
<%@page import="com.koreait.site0622.model.board.dao.MybatisBoardDAO"%>
<%@page import="com.koreait.site0622.model.member.dao.MemberDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
		MemberDAO memberDAO=new MybatisMemberDAO();
		MessageObject obj=new MessageObject();
%>
<%
	request.setCharacterEncoding("utf-8");
/*String user_id=request.getParameter("user_id");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
*/
%>

<jsp:useBean id="member" class="com.koreait.site0622.model.domain.Member"/>
<jsp:setProperty property="*" name="member"/>

<%
	//db에 넣기
	int result=memberDAO.regist(member);
	if(result==0){
		out.print(obj.getMsgBack("회원등록실패"));
	}else{
		out.print(obj.getMsgURL("회원가입성공", "/member/login.jsp"));
	}
%>
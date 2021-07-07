<%@page import="com.koreait.model2app.util.MessageObject"%>
<%@page import="java.io.File"%>
<%@page import="com.koreait.tourapp.model.domain.Store"%>
<%@page import="com.koreait.tourapp.model.store.dao.MybatisStoreDAO"%>
<%@page import="com.koreait.tourapp.model.store.dao.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	StoreDAO storeDAO=new MybatisStoreDAO();
	MessageObject msg=new MessageObject();
%>
<%
	//1)파일삭제
	int store_id=Integer.parseInt(request.getParameter("store_id"));
	Store store=storeDAO.select(store_id);
	
	String realPath=application.getRealPath("/data");
	int lastIndex=store.getFirstimage().lastIndexOf("/");
	String filename=store.getFirstimage().substring(lastIndex,store.getFirstimage().length());
	System.out.println(realPath+filename);
	
	File file=new File(realPath+filename);
	if(file.delete()){
	//2)db삭제
		int result=storeDAO.delete(store.getStore_id());
		
		response.setContentType("text/html;charset=utf-8");
		if(result<1){
			out.print(msg.getMsgBack("삭제 실패"));
		}else{
			out.print(msg.getMsgURL("삭제 성공", "/main/index.html"));
		}
	}else{
		out.print(msg.getMsgBack("사진 삭제 실패"));
	}
%>
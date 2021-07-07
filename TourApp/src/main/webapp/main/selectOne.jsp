<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.koreait.tourapp.model.domain.Store"%>
<%@page import="com.koreait.tourapp.model.store.dao.MybatisStoreDAO"%>
<%@page import="com.koreait.tourapp.model.store.dao.StoreDAO"%>
<%@ page contentType="application/json; charset=UTF-8"%>
<%!
	StoreDAO storeDAO=new MybatisStoreDAO();
%>
<%
	Store store=storeDAO.getLastStore();
	response.setContentType("application/json;charset=utf-8");
	List<Store> list=new ArrayList<Store>();
	list.add(store);
	
	Map<String,List> map=new HashMap<String,List>();
	map.put("item", list);
	
	Gson gson=new Gson();
	String str=gson.toJson(map);
	
	if(store!=null){
		out.print(str);
	}
%>
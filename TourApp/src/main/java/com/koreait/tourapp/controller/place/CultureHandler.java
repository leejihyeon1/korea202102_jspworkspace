package com.koreait.tourapp.controller.place;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.koreait.tourapp.model.domain.Culture;

public class CultureHandler extends DefaultHandler{
	private boolean isAddr1;
	private boolean isFirstimage;
	private boolean isMapx;
	private boolean isMapy;
	private boolean isTitle;
	ArrayList<Culture> list;
	Culture culture;
	
	//시작태그
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.println("<"+tag+">");
		if(tag.equals("items")) {//vo를 담을 리스트 형을 준비
			list=new ArrayList<Culture>();
		}else if(tag.equals("item")) {
			culture=new Culture();
		}else if(tag.equals("addr1")) {
			isAddr1=true;
		}else if(tag.equals("firstimage")) {
			isFirstimage=true;
		}else if(tag.equals("mapx")) {
			isMapx=true;
		}else if(tag.equals("mapy")) {
			isMapy=true;
		}else if(tag.equals("title")) {
			isTitle=true;
		}
	}
	//태그사이 문자열 발견
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data=new String(ch, start, length);
		//System.out.println(data);
		if(isAddr1) {
			culture.setAddr1(data);
		}else if(isFirstimage) {
			culture.setFirstimage(data);
		}else if(isMapx) {
			culture.setMapx(Double.parseDouble(data));
		}else if(isMapy) {
			culture.setMapy(Double.parseDouble(data));
		}else if(isTitle) {
			culture.setTitle(data);
		}
	}
	//닫는 태그
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");
		if(tag.equals("addr1")) {
			isAddr1=false;
		}else if(tag.equals("firstimage")) {
			isFirstimage=false;
		}else if(tag.equals("mapx")) {
			isMapx=false;
		}else if(tag.equals("mapy")) {
			isMapy=false;
		}else if(tag.equals("title")) {
			isTitle=false;
		}else if(tag.equals("item")) {
			list.add(culture);//완성된 culture vo를 list에 담기
		}
	}
	//문서 종료 태그
	@Override
	public void endDocument() throws SAXException {
		System.out.println("담겨진 결과 수는 "+list.size());
	}

}

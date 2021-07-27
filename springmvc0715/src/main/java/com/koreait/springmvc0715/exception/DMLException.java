package com.koreait.springmvc0715.exception;

//sun에서 제공해주는 exception만으로는 다양한 예외를 처리할 수 없으므로 
//RuntimeException을 상속받아 나만의 커스텀 예외를 만들어서 사용해보자!
public class DMLException extends RuntimeException{
	
	//에러메세지를 담을 수 있는 생성자 호출
	public DMLException(String msg) {
		super(msg);
	}
	
	//에러 메세지 + 에러 원인도 담을 수 있는 생성자 호출
	public DMLException(String msg, Throwable e) {
		super(msg,e);
	}
}

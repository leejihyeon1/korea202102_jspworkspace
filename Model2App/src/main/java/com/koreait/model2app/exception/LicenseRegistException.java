package com.koreait.model2app.exception;

public class LicenseRegistException extends RuntimeException{
	public LicenseRegistException(String message) {
		super(message);
	}
	//Throwable은 예외 api의 최상위 인터페이스
	public LicenseRegistException(String message,Throwable e) {
		super(message,e);
	}
}

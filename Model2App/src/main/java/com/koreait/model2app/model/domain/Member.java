package com.koreait.model2app.model.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private int member_id;
	private String name;
	private String phone;
	private String addr;
	private String photo;
	
	//자격증들을 보유한다 
	List<License> list=new ArrayList<License>();
}

package com.koreait.model2app.model.blood;

public class BloodService {
	
	//웹이건 응용이건 이 메소드를 호출하는 자는 혈액형을 넘기면 됨
	public String getAdvice(String blood) {
		String msg=null;
		//넘겨받은 혈액형에 대한 판단 결과 도출
		if(blood.equals("A형")){
			msg="소심하고 꼼꼼하고 책임감 강함";
		}else if(blood.equals("B형")){
			msg="고집쎄고 털털하다";
		}else if(blood.equals("O형")){
			msg="잘 어울리고 오지랖이다";
		}else if(blood.equals("AB형")){
			msg="선택을 왔다갔다함";
		}
		return msg;
	}
}

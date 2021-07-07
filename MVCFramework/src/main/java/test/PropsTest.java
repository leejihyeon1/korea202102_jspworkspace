package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropsTest {
	Properties props;//key-value의 쌍으로 된 데이터를 이해하는 객체(Map의 자식)
	FileReader reader;//파일접근
	String path="C:\\korea202102_jspworkspace\\MVCFramework\\src\\main\\webapp\\WEB-INF\\mapping.data";
	public PropsTest() {
		props=new Properties();
		try {
			props.load(reader=new FileReader(path));//프로퍼티스 객체가 파일은 인식한 시점//프로퍼티스 객체가 파일은 인식한 시점
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new PropsTest();
	}
}

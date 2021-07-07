package springbasic.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseStudent {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext();//스프링 컨테이너 생성
		//이 시점에서 xml에 명시된 bean태그들은 실제 자바 객체로 인스턴스가 생성되어 , ApplicationContext의 관리대상이 됨
		
		Student s=(Student) context.getBean("student");
		s.study1();
		s.study2();
		s.study3();
		s.study4();
		s.study5();
		s.study6();
		
		Soldier so=(Soldier) context.getBean("soldier");
		so.getUp();
		so.eat();
		so.work();
		so.eat2();
		so.sleep();
	}
}

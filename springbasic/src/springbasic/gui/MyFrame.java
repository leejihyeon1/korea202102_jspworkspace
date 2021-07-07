package springbasic.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lombok.Setter;

@Setter
public class MyFrame extends JFrame{
	//보유할 객체와는 결합도를 낮춰야 한다..
	private JComponent t_id;
	private JComponent t_pass;
	private JComponent t_name;
	private JComponent bt;
	
	//이미 객체들이 생성되어, 스프링 컨테이너에 의해 주입될 예정이므로, 
	//조립만 하자!!
	public void init() {
		setLayout(new FlowLayout());
	
		//조립 
		add(t_id);
		add(t_pass);
		add(t_name);
		add(bt);
		
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


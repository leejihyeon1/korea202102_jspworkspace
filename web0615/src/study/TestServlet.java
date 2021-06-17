package study;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

//�������� javaEE����� �������� �ؼ� �� ����Ǿ��� �� �����Ƿ�,
//�� Ŭ������ �������� ��������!
public class TestServlet extends HttpServlet{
	//��û�� ���� ������ ó���غ���! service() �޼��尡 ��û �� ������ ó��

	//node.js�� ���������� Ŭ���̾�Ʈ�� ��û�� ó���Ϸ���, � ��û������ ������ Ŭ���̾�Ʈ��
	//��û�ߴ����� ���� ������ ����, ��û ��ü�� �̿��ؾ��Ѵ�!
	//����� �������̽��� HttpServletRequest ��ü�� , ��û�� ���� �� �ڵ� �����Ǿ� ������
	//���� ���� ä�����Ƿ�, �����ڰ� �׳� ���⸸ �ϸ� �ȴ�!

	//�� ��û�� ó���Ѵٴ� ���� ���� ��û�� �� ��û�� ���� ��������� ��ī������ �����Ѵ�!
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html"); //mimetype ����
		response.setCharacterEncoding("utf-8");//��Ʈ���� �̱� �� �̸� ���ڵ�
		PrintWriter out=response.getWriter();//���� ����� ��½�Ʈ���� ��ȯ�ϴ� �޼���!
		out.print("this is my second Servlet");
	}
}

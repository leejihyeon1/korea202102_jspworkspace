package site0617.photo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import site0617.model.domain.Gallery;
import site0617.model.gallery.dao.GalleryDAO;
import site0617.util.FileManager;

//아파치 업로드 컴포넌트를 이용한 파일업로드 구현
public class RegistServlet extends HttpServlet{
	ServletContext context;
	String path;
	int maxSize=2*1024*1024; //2M byte
	GalleryDAO galleryDAO; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext();
		path = context.getRealPath("/data");
		galleryDAO = new GalleryDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//jsp에서의 page지시영역의 conentType과 동일
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("저 실행성공이요~!");
		
		//아파치 파일 업로드를 이용한 파일업로드 처리~~
		
		
		//업로드 관련설정 DiskFileItemFactory 이용
		File saveDir=new File(path); //저장될 디렉토리
		DiskFileItemFactory factory=new DiskFileItemFactory();

		factory.setRepository(saveDir);//어느경로에 저장
		factory.setSizeThreshold(maxSize);//저장용량 제한
		
		//업로드 처리 객체 
		ServletFileUpload upload=new ServletFileUpload(factory);//설정 정보 적용하여 인스턴스 생성
		request.setCharacterEncoding("utf-8"); //파라미터에 대한 인코딩(한글 안깨지게)
		
		Gallery gallery=new Gallery();
		
		try {
			List<FileItem> itemList=upload.parseRequest(request);
			out.print("넘겨받은 컴포넌트 값의 수는 "+itemList.size()+"<br>");
			for( FileItem item : itemList) {
				if(item.isFormField()) { //inpuyt type='text' 박스라면...
					
					out.print(item.getFieldName()+"필드의 값은 "+item.getString("utf-8")+"<br>");
					
					String value = item.getString("utf-8");
					
					if(item.getFieldName().equals("title")) {//제목
						gallery.setTitle(value);
					}else if(item.getFieldName().equals("writer")) {//작성자
						gallery.setWriter(value);
					}else if(item.getFieldName().equals("content")) {//내용
						gallery.setContent(value);
					}
				}else { //아니라면, 얘는 input type='file'
					long time=System.currentTimeMillis();
					String newName=item.getName(); //업로드된 파일명 반환  
					out.print("업로드한 파일명은 "+newName);
					
					String ext=FileManager.getExt(newName);//확장자 구하기
					item.write(new File(path+"/"+time+"."+ext)); //서버에 저장!!!
					gallery.setFilename(time+"."+ext);
				}
				
				//반복문이 종료되면, VO에 알맞는 데이터가 채워져 있다!!
				//이 시점에 VO를 이용하여 insert 수행!!
				int result = galleryDAO.insert(gallery);
				out.print("<script>");
				
				if(result==0) {
					out.print("alert('등록실패');");
					out.print("history.back();");
				}else {
					out.print("alert('등록성공');");
					out.print("location.href='/photo/list.jsp';");
				}
				out.print("</script>");
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
  
package com.koreait.model2app.model.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koreait.model2app.exception.LicenseRegistException;
import com.koreait.model2app.model.domain.License;
import com.koreait.model2app.model.domain.Member;
import com.koreait.model2app.model.license.dao.JdbcLicenseDAO;
import com.koreait.model2app.model.license.dao.LicenseDAO;
import com.koreait.model2app.model.member.dao.JdbcMemberDAO;
import com.koreait.model2app.model.member.dao.MemberDAO;
import com.koreait.model2app.util.FileManager;
import com.koreait.model2app.util.pool.PoolManager;

//서비스 인터페이스를 구현한 구현 클래스 
//왜 서비스에 인터페이스까지 둬야 하는지 의문이 생기겠지만, 담주까지 참자...
//이유 어플리케이션의 의존성을 약화시키기 위함..(DI를 배워야 이해가능함..)
public class MemberServiceImpl implements MemberService{
	
	private PoolManager pool=PoolManager.getInstance(); //DAO들사이의 Connection을 
																				//공유하기 위해...
	//부장님이 일시킬 모델 객체들...
	FileManager fileManager; //파일업로드
	MemberDAO memberDAO;//회원정보
	LicenseDAO licenseDAO;//자격증정보
	
	public MemberServiceImpl() {
		memberDAO = new JdbcMemberDAO();
		licenseDAO = new JdbcLicenseDAO();
	}
	
	public int regist(Member member, HttpServletRequest request) throws LicenseRegistException{
		Connection con=pool.getConnection(); //DAO들에게 나누어줄 커넥션 얻기!!
		((JdbcMemberDAO)memberDAO).setCon(con);//Connection 객체 주입
		((JdbcLicenseDAO)licenseDAO).setCon(con);//Connection객체 주입 
		
		//jdbc에서의 Connection은 autoCommit이 true로 설정되어 있다..오라클과 틀림 
		//일단 autoCommit=false로 돌려놓고 트랜잭션 작업하자!!
		try {
			con.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			int member_id=memberDAO.insert(member);// 2/3
	
			List<License> list=member.getList();
			
			for(License obj : list) {
				obj.setMember_id(member_id);//회원등록시 발생한 시퀀스 값을 대입해야 함
				licenseDAO.insert(obj); // 3/3
			}
			try {
				con.commit();//트랜잭션 성공 확정
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(LicenseRegistException e) {
			System.out.println(e.getMessage()); //에러 메시지 출력 (유저들을 위한 비전문 메시지)
			e.printStackTrace();//개발자가 원인을 분석하기 위한 stack구조의 에러 콘솔출력
			try {
				con.rollback(); //트랜잭션 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;//에러발생
		}finally {
			pool.release(con);//반납
			try {
				con.setAutoCommit(true); //상태 원상복구
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List selectAll() {
		Connection con=pool.getConnection();
		((JdbcMemberDAO)memberDAO).setCon(con);
		List list= memberDAO.selectAll();
		pool.release(con);
		return list;
	}

	@Override
	public Member select(int member_id) {
		Connection con=pool.getConnection();
		((JdbcMemberDAO)memberDAO).setCon(con);
		Member member= memberDAO.select(member_id);
		pool.release(con);
		return member;
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int member_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
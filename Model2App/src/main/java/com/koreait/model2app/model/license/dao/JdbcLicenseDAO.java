package com.koreait.model2app.model.license.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.koreait.model2app.exception.LicenseRegistException;
import com.koreait.model2app.model.domain.License;

import lombok.Data;

@Data
public class JdbcLicenseDAO implements LicenseDAO{
	private Connection con;
	
	@Override
	public int insert(License license) throws LicenseRegistException{
		PreparedStatement pstmt=null;
		int result=0;

		String sql="insert  license(license_id, member_id, title) values(seq_license.nextval,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, license.getMember_id());
			pstmt.setString(2, license.getTitle());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			//앞에 인수: 개발자가 원하는 메시지,  뒤에 인수: 에러 원인
			throw new LicenseRegistException("라이센스 등록에 실패", e);//개발자가 원하는 예외를 생성(즉 에러를 일부러 일으킴)
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public License select(int license_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(License license) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int license_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
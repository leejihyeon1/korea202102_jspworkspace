package site0617.model.gallery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site0617.model.PoolManager;
import site0617.model.domain.Gallery;

//이 클래스는 오직 gallery에 테이블에 대한 CRUD만을 담당
public class GalleryDAO {
	PoolManager pool=PoolManager.getInstance();
	
	public int insert(Gallery gallery) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=pool.getConnection();
		String sql="insert into gallery(gallery_id,title,writer,content,filename) values(seq_gallery.nextval,?,?,?,?)";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, gallery.getTitle());
			pstmt.setString(2, gallery.getWriter());
			pstmt.setString(3, gallery.getContent());
			pstmt.setString(4, gallery.getFilename());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Gallery> list=new ArrayList<Gallery>();
		String sql="select * from gallery order by gallery_id desc";
		con=pool.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//rs가 곧 소멸되므로 담아놓자 
			while(rs.next()) {
				Gallery gallery=new Gallery();
				gallery.setGallery_id(rs.getInt("gallery_id"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setFilename(rs.getString("filename"));
				gallery.setHit(rs.getInt("hit"));
				
				list.add(gallery);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	//레코드 한건 가져오기
	public Gallery select(int gallery_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Gallery gallery=null;
		con=pool.getConnection();
		String sql="select * from gallery where gallery_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, gallery_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				gallery=new Gallery();
				gallery.setGallery_id(rs.getInt("gallery_id"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setFilename(rs.getString("filename"));
				gallery.setHit(rs.getInt("hit"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return gallery;
	}
	
	public int delete(int gallery_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=pool.getConnection();
		String sql="delete from gallery where gallery_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, gallery_id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	//1건 수정
	public int update(Gallery gallery) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=pool.getConnection();
		String sql="update gallery set title=?,writer=?,content=?, filename=? where gallery_id=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, gallery.getTitle());
				pstmt.setString(2, gallery.getWriter());
				pstmt.setString(3, gallery.getContent());
				pstmt.setString(4, gallery.getFilename());
				pstmt.setInt(5, gallery.getGallery_id());
				result=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				pool.release(con,pstmt);
			}
			return result;
	}
}

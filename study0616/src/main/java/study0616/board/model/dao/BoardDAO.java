package study0616.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import study0616.model.domain.Board;
import study0616.model.pool.PoolManager;

public class BoardDAO {
	PoolManager poolManager;
	Connection con;
	
	
	public BoardDAO() {
		poolManager=poolManager.getInstance();
	}
	
	public int insert(Board board) {
		con=poolManager.getConnection();
		PreparedStatement pstmt=null;
		String sql="insert into board(board_id,title,writer,content) values(seq_board.nextval,?,?,?)";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		String sql="select * from board order by board_id desc";
		con=poolManager.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Board> list=new ArrayList<Board>();
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board=new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	public Board select(int board_id) {
		String sql="select * from board where board_id="+board_id;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null;
		con=poolManager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board=new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt, rs);
		}
		return board;
	}
	
	public int update(Board board) {
		String sql="update board set title=?, writer=?, content=? where board_id=?";
		PreparedStatement pstmt=null;
		int result=0;
		con=poolManager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getHit());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt);
		}
		return result;
	}
	
	public int delete(int board_id) {
		String sql="delete from board where board_id="+board_id;
		PreparedStatement pstmt=null;
		int result=0;
		con=poolManager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			poolManager.release(con,pstmt);
		}
		return result;
	}
}

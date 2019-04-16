package bookmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookmanager.util.ConnectionFactory;
import bookmanager.util.JDBCClose;
import bookmanager.vo.BookVO;
import bookmanager.vo.RentalVO;

public class RentalDAO {
	
	public void BookExtension(int book_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update tbl_rental set");
			sql.append("  back_date = back_date + 7");
			sql.append("  where book_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, book_no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		
	}

	public int ReturnBook(int book_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		int cnt = -1;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			StringBuilder sql2 = new StringBuilder();

			// 도서 테이블 대여여부 반영
			sql.append("update tbl_books set is_rental = 1");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, book_no);

			cnt += pstmt.executeUpdate();

			// 대여여부 테이블에서 도서 삭제
			sql2.append("delete tbl_rental ");
			sql2.append(" where book_no = ? ");

			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt2.setInt(1, book_no);

			cnt += pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
			JDBCClose.close(pstmt2);
		}
		return cnt;
	}

	public int RentalBook(BookVO book, int user_no, String user_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		int cnt = -1;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			StringBuilder sql2 = new StringBuilder();

			// 도서 테이블 대여여부 반영
			sql.append("update tbl_books set is_rental = 0");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, book.getNo());

			cnt += pstmt.executeUpdate();

			// 대여여부 테이블에 도서 추가
			sql2.append("insert into tbl_rental (no, name, book_no, book_name) ");
			sql2.append(" values(?, ?, ?, ? ) ");

			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt2.setInt(1, user_no);
			pstmt2.setString(2, user_name);
			pstmt2.setInt(3, book.getNo());
			pstmt2.setString(4, book.getTitle());

			cnt += pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
			JDBCClose.close(pstmt2);
		}
		return cnt;
	}

	public RentalVO getRental(int book_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RentalVO rental = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			// 대여현황 테이블에서 책번호로 조회
			sql.append("select r.no, r.name, r.book_no, r.book_name ");
			sql.append(" from tbl_rental r, tbl_books b ");
			sql.append(" where r.book_no = b.no ");
			sql.append(" and  r.book_no = ? ");
//			sql.append("select * from tbl_rental ");
//			sql.append(" where book_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, book_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rental = new RentalVO();
				rental.setNo(rs.getInt("no"));
				rental.setName(rs.getString("name"));
				rental.setBook_no(rs.getInt("book_no"));
				rental.setBook_name(rs.getString("book_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return rental;
	}

	public List<RentalVO> SearchAllRental(int no) {
		List<RentalVO> rentalList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			// 도서 테이블 대여여부 반영
			sql.append("select no, name, book_no, book_name, ");
			sql.append(" to_char(lend_date,'yyyy-mm-dd') lend_date, ");
			sql.append(" to_char(back_date, 'yyyy-mm-dd') back_date ");
			if( no == 0 )
				sql.append(" from tbl_rental ");
			else
				sql.append(" from tbl_rental where no = ? ");
			sql.append(" order by no ");

			pstmt = conn.prepareStatement(sql.toString());
			if( no != 0 )
				pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int user_no = rs.getInt("no");
				String name = rs.getString("name");
				int book_no = rs.getInt("book_no");
				String book_name = rs.getString("book_name");
				String lend_date = rs.getString("lend_date");
				String back_date = rs.getString("back_date");

				RentalVO rental = new RentalVO(user_no, name, book_no, book_name, lend_date, back_date);
				rentalList.add(rental);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return rentalList;
	}

}

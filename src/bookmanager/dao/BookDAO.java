package bookmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookmanager.util.ConnectionFactory;
import bookmanager.util.JDBCClose;
import bookmanager.vo.BookVO;

public class BookDAO {

	/**
	 * 책 번호로 책을 검색
	 * 
	 * @param book_no
	 * @return 책 객체 리턴
	 */
	public BookVO SearchOneBook(int book_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BookVO book = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select * from tbl_books");
			sql.append(" where no = ? and rownum = 1 order by no");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, book_no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String writer = rs.getString("writer");
				int is_rental = rs.getInt("is_rental");
				String preview = rs.getString("preview");

				book = new BookVO(no, name, writer, is_rental, preview);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return book;
	}

	/**
	 * 책 제목 혹은 저자명으로 책검색
	 * 
	 * @param str
	 * @return 1개 이상의 책 객체를 담은 리스트 객체
	 */
	public List<BookVO> selectByStr(String str) {
		BookVO book = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<BookVO> bookList = new ArrayList<>();
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select no, name, writer, is_rental, preview");
			sql.append("  from tbl_books");
			sql.append(" where name = ? or writer = ? order by no");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, str);
			pstmt.setString(2, str);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String writer = rs.getString("writer");
				int is_rental = rs.getInt("is_rental");
				String preview = rs.getString("preview");

				book = new BookVO(no, name, writer, is_rental, preview);
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return bookList;

	}

	/**
	 * 책 객체 정보를 가져와서 DB에 책 정보 추가
	 * 
	 * @param book
	 */
	public void insertBook(BookVO book) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tbl_books(no, name, writer, preview, is_rental) ");
			sql.append(" values(seq_tbl_book_no.nextval, ?, ?, ?, 1 ) ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getWriter());
			pstmt.setString(3, book.getPreview() );

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}

	/**
	 * 모든 책 검색
	 * 
	 * @return 책 객체를 담은 리스트
	 */
	public List<BookVO> selectAllBooks() {

		List<BookVO> bookList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select no, name, writer, is_rental, preview ");
			sql.append("  from tbl_books order by no");

			pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String writer = rs.getString("writer");
				int is_rental = rs.getInt("is_rental");
				String preview = rs.getString("preview");

				BookVO book = new BookVO(no, name, writer, is_rental, preview);
				bookList.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return bookList;
	}

	/**
	 * 책 번호로 책 삭제
	 * 
	 * @param no 책 번호
	 * @return
	 */
	public boolean deleteBook(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from tbl_books ");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return flag;
	}

	/**
	 * num = 1 : 책제목, num = 2 : 책저자, num = 3 : 미리보기 책 항목 수정
	 * 
	 * @param book 책 객체
	 * @param num  수정할 항목
	 */
	public void updateBook(BookVO book, int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update tbl_books ");
			if (num == 1) {
				sql.append("  set name = ? ");
			} else if (num == 2) {
				sql.append("  set writer = ? ");
			} else if (num == 3) {
				sql.append("   set preview = ? ");
			}
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			if (num == 1) {
				pstmt.setString(1, book.getTitle());
			} else if (num == 2) {
				pstmt.setString(1, book.getWriter());
			} else if (num == 3) {
				pstmt.setString(1, book.getPreview());
			}
			pstmt.setInt(2, book.getNo());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

	}
}

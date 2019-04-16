package bookmanager.service;

import java.util.List;

import bookmanager.dao.BookDAO;
import bookmanager.vo.BookVO;

public class BookService {
	private BookDAO dao;

	// DAO 객체 생성
	public BookService() {
		dao = new BookDAO();
	}

	// 한개의 책 검색
	public BookVO SearchOneBook(int book_no) {
		return dao.SearchOneBook(book_no);
	}

	// 제목 혹은 저자로 책 검색
	public List<BookVO> selectByStr(String str) {
		List<BookVO> result = dao.selectByStr(str);
		return result;
	}

	// 책 추가
	public synchronized void insertBook(BookVO book) {

		dao.insertBook(book);
	}

	// 모든 책 조회
	public List<BookVO> selectAllBooks() {
		List<BookVO> bookList = dao.selectAllBooks();
		return bookList;
	}

	// 책 삭제
	public boolean deleteBook(int no) {
		return dao.deleteBook(no);
	}

	// 책 수정
	public void updateBook(BookVO book, int num) {
		dao.updateBook(book, num);
	}

}

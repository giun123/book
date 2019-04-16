package bookmanager.service;

import java.util.List;

import bookmanager.dao.RentalDAO;
import bookmanager.vo.BookVO;
import bookmanager.vo.RentalVO;

public class RentalService {
	private RentalDAO dao;
	
	// DAO 생성
	public RentalService() {
		dao = new RentalDAO();
	}
	
	// 대여 정보 조회
	public RentalVO getRental(int book_no) {
		return dao.getRental(book_no);
	}
	
	// 책 대여
	public int RentalBook(BookVO book, int user_no, String user_name) {
		return dao.RentalBook(book, user_no, user_name);
	}
	
	// 책 반납
	public int Returnbook(int book_no) {
		return dao.ReturnBook(book_no);
	}

	// 모든 대여정보 조회
	public List<RentalVO> SearchAllRental(int user_no) {
		return dao.SearchAllRental(user_no);
	}
	
	// 대여기간 연장
	public void RentalExtension(int book_no) {
		dao.BookExtension(book_no);
	}

}

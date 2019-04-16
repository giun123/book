package bookmanager.ui.book;

import java.util.List;

import bookmanager.ui.BaseUI;
import bookmanager.ui.InterfaceUI;
import bookmanager.ui.rental.RentalUI;
import bookmanager.vo.BookVO;

// 모든 책 정보 조회 UI
public class SearchAllBooksUI extends BaseUI {

	public void execute() {
		wh1: while (true) {
			try {
				ViewBooksList();
				
				if( is_admin() )
					break wh1;
				
				int book_no = scanInt("보고싶은 도서의 번호를 입력하세요 (0 입력시 뒤로) : ");
				if (book_no == 0)
					break wh1;
				InterfaceUI ui = null;
				
				BookVO book = bservice.SearchOneBook(book_no);
				System.out.println( "책 [" + book.getTitle() + "]에 대하여 어떤 작업을 수행하시겠습니까?\n");

				int case_no = scanInt("1.도서 대여 2.미리보기 0.뒤로가기   항목을 선택하세요 : ");
				switch (case_no) {
				case 1:
					ui = new RentalUI(book_no);
					break;
				case 2:
					ui = new PreviewBookUI(book_no);
					break;
				case 0:
					break wh1;
				}
				if( ui == null)
					System.out.println("잘못입력하셨습니다");
				else 
					ui.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	} // execute

	private void ViewBooksList() {
		List<BookVO> bookList = bservice.selectAllBooks();

		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%s \t %-10s \t %-10s \t %-10s \n", "번호", "저자", "대여 여부", "제목" );
		System.out.println("-------------------------------------------------------------------------------");

		for (BookVO book : bookList) {
			int is_rental = book.getIs_rental();
			System.out.printf("%d  \t %-10s \t %-10s \t %s\n", book.getNo(), book.getWriter(),
					is_rental == 1 ? "대여 가능" : "대여 불가능" , book.getTitle() );
		}

		if (bookList.isEmpty()) {
			System.out.println("등록된 도서가 없습니다 ");
		}
		System.out.println("-------------------------------------------------------------------------------\n");
	}

}

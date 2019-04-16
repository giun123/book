package bookmanager.ui.book;

import java.util.List;

import bookmanager.ui.BaseUI;
import bookmanager.ui.InterfaceUI;
import bookmanager.ui.rental.RentalUI;
import bookmanager.vo.BookVO;

// 책 검색 UI
public class SearchBookUI extends BaseUI {
	public void execute() {
		String str = scanStr("조회할 저서나 저자를 입력하세요 : ");

		BooksAnything(str);

	} // execute

	private void BooksAnything(String str) {
		wh1: while (true) {
			try {
				SearchBook(str); // 검색한 책 정보 조회
				
				int book_no = scanInt("보고싶은 도서의 번호를 입력하세요 ( 0 입력시 뒤로 ) : ");
				if (book_no == 0)
					break wh1;

				InterfaceUI ui = null;

				BookVO book = bservice.SearchOneBook(book_no);
				System.out.println("책 [" + book.getTitle() + "]에 대하여 어떤 작업을 수행하시겠습니까?\n");

				wh2: while (true) {
					int case_no = scanInt("1.도서 대여 2.미리보기 0.뒤로가기   항목을 선택하세요 : ");
					switch (case_no) {
					case 1:
						ui = new RentalUI(book_no);
						break;
					case 2:
						ui = new PreviewBookUI(book_no);
						break;
					case 0:
						break wh2;
					}

					if (ui == null)
						System.out.println("잘못입력하셨습니다");
					else
						ui.execute();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 책 검색 UI
	 */
	private void SearchBook(String str) {

		List<BookVO> result = bservice.selectByStr(str);

		if (result.isEmpty()) {
			System.out.println("저서 혹은 저자가 [" + str + "]에 해당하는 책이 존재하지 않습니다");
		} else {
			System.out.println("\n\t\t도서 조회 결과");
			System.out.println("----------------------------------------------");
			System.out.printf("%-10s %-10s %-10s \t%-10s\n", "책번호", "저 자", "대여가능여부", "제 목");
			System.out.println("----------------------------------------------");

			for (BookVO book : result) {
				System.out.printf("%d\t %-10s %-9s \t%-10s\n", book.getNo(), book.getWriter(),
						(book.getIs_rental() == 1 ? "대여가능" : "대여불가능"), book.getTitle());
			}
		}
		System.out.println("----------------------------------------------\n");
	}
}

package bookmanager.ui.rental;

import bookmanager.ui.BaseUI;
import bookmanager.vo.BookVO;

// 대여 UI
public class RentalUI extends BaseUI {
	private int book_no;
	
	public RentalUI() {
		
	}

	public RentalUI(int book_no) {
		this.book_no = book_no;
	}

	public void execute() throws Exception {
		BookVO book = bservice.SearchOneBook(book_no);
		
		if (book == null) {
			System.out.println("해당 번호에 해당하는 책이 없습니다");
		} else {
			if (book.getIs_rental() == 1) {
				System.out.println("\'" + book.getTitle() + "\'책은 대여가 가능합니다");

				String rental = scanStr("대여 하시겠습니까? (y/n)");

				if (rental.equals("y")) {
					int cnt = rservice.RentalBook(book, user_no, user_name);
					
					if(cnt != 0 )
						System.out.println(book_no + "번의 책을 대여했습니다");
					else 
						System.out.println("대여에 실패했습니다");
				}
			} else {
				System.out.println("\'" + book.getTitle() + "\'책은 대여가 불가능합니다\n");
			}

		}
	}

}

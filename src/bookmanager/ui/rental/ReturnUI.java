package bookmanager.ui.rental;

import bookmanager.ui.BaseUI;
import bookmanager.vo.RentalVO;

// 반납 UI
public class ReturnUI extends BaseUI {
	private int book_no;
	
	public ReturnUI() {
		
	}

	public ReturnUI(int book_no) {
		this.book_no = book_no;
	}
	
	@Override
	public void execute() throws Exception {
		RentalVO rental = rservice.getRental(book_no);
		
		if (rental == null) {
			System.out.println("해당 번호의 책은 대여가능 상태입니다");
		} else {
			if (rental.getNo() == user_no) {
				String go_rental = scanStr("반납하시겠습니까? (y/n)");
				
				if(go_rental.equals("y")) {
					int cnt = rservice.Returnbook(rental.getBook_no());
					if(cnt != 0 )
						System.out.println(book_no + "번의 책을 반납했습니다");
					else 
						System.out.println("반납에 실패했습니다");
				}
			} else {
				System.out.println("해당 번호의 책은 다른 사람이 대여한 상태입니다");
			}
		}

	}

}

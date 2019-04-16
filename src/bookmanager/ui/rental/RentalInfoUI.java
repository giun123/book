package bookmanager.ui.rental;

import java.util.List;

import bookmanager.ui.BaseUI;
import bookmanager.ui.InterfaceUI;
import bookmanager.vo.RentalVO;

// 모든 대여정보 조회
public class RentalInfoUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		wh1: while (true) {
			List<RentalVO> result = rservice.SearchAllRental(user_no);

			if (result.isEmpty()) {
				System.out.println("\n대여한 책이 없습니다\n");
				break wh1;
			} else {
				RentalInfo(result);
			}
			
			int book_no = scanInt("도서 번호를 입력하세요 (0 입력시 뒤로) : ");
			if (book_no == 0) {
				break wh1;
			}
			
			System.out.println("1.반납 2.기간연장");
			int select_no = scanInt("실행할 항목을 선택하세요 : ");
			
			InterfaceUI ui = null;
			
			switch (select_no) {
			case 1:
				ui = new ReturnUI(book_no);
				break;
			case 2:
				ui = new ExtensionUI(book_no);
				break;
			case 0:
				break wh1;
			}
			if( ui == null)
				System.out.println("\n잘못입력하셨습니다\n");
			else 
				ui.execute();
		}
	}
}

package bookmanager.ui.book;

import bookmanager.ui.BaseUI;
import bookmanager.ui.InterfaceUI;

// 책 조회 메뉴 UI
public class BookMenuUI extends BaseUI {

	public void execute() {

		wh1:while (true) {
			try {
				int type = menu();
				InterfaceUI ui = null;

				switch (type) {
				case 1:
					ui = new SearchAllBooksUI();
					break;
				case 2:
					ui = new SearchBookUI(); 
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
			} //try
		} // while
	} // execute

	private int menu() {
		System.out.println("------------------------------");
		System.out.println("\t도서 대여 프로그램(회원)");
		System.out.println("------------------------------");
		System.out.println("1. 도서 조회");
		System.out.println("2. 도서 검색");
		System.out.println("0. 뒤로가기");
		System.out.println("------------------------------");
		return scanInt("메뉴 중 원하는 항목을 선택하세요 : ");
	}

}

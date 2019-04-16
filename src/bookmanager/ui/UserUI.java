package bookmanager.ui;

import bookmanager.ui.book.BookMenuUI;
import bookmanager.ui.rental.RentalInfoUI;
import bookmanager.ui.user.UserInfoUI;

// 회원일 경우 첫 UI
public class UserUI extends BaseUI {

	public void execute() {

		while (true) {
			try {
				int type = menu();
				InterfaceUI ui = null;

				switch (type) {
				case 1 :
					ui = new BookMenuUI();
					break;
				case 2 :
					ui = new UserInfoUI();
					break;					
				case 3 : 
					ui = new RentalInfoUI();
					break;
				case 0:
					ui = new ExitUI();
					break;
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
		System.out.println("1. 도서 메뉴");
		System.out.println("2. 회원정보 확인/수정");
		System.out.println("3. 대여현황");
		System.out.println("0. 종료");
		System.out.println("------------------------------\n");
		return scanInt("메뉴 중 원하는 항목을 선택하세요 : ");
	}

}

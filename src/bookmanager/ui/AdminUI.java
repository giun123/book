package bookmanager.ui;

import bookmanager.ui.rental.AllRentalUI;

// 관리자일경우 첫 UI
public class AdminUI extends BaseUI {

	public void execute() {

		while (true) {
			try {
				int type = menu();
				InterfaceUI ui = null;

				switch (type) {
				case 1:
					ui = new UserManagerUI(); 
					break;
				case 2:
					ui = new BooksUI();
					break;
				case 3:
					ui = new AllRentalUI();
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

	// 메뉴 출력
	private int menu() {
		System.out.println("\n-------------------------");
		System.out.println("\t관리자모드");
		System.out.println("-------------------------");
		System.out.println("\t1. 회원관리");
		System.out.println("\t2. 도서관리");
		System.out.println("\t3. 대여현황");
		System.out.println("\t0. 종료");
		System.out.println("-------------------------\n");
		return scanInt("메뉴 중 원하는 항목을 선택하세요 : ");
	}

}

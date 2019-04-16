package bookmanager.ui;

import bookmanager.ui.user.LoginUI;
import bookmanager.ui.user.AddUserUI;

// 제일 처음나오는 UI
public class MainUI extends BaseUI {
	public void execute()  {
		
		while(true) {
			try {
				InterfaceUI ui = null;
				int type = menu();
				switch (type) {
				case 1:
					ui = new LoginUI();
					break;
				case 2:
					ui = new AddUserUI();
					break;	
				case 0:
					ui = new ExitUI();
					break;
				}
				
				if(ui != null)
					ui.execute();
				else
					System.out.println("번호를 입력선택하셨습니다.");
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int menu() {
		System.out.println("----------------------------------");
		System.out.println("\t도서 관리 프로그램");
		System.out.println("----------------------------------");
		System.out.println("    1. 로그인");
		System.out.println("    2. 회원가입");
		System.out.println("    0. 종료");
		System.out.println("----------------------------------\n");
		int type = scanInt("메뉴 중 원하는 항목을 선택하세요 : ");
		
		return type;
	}

}

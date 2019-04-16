package bookmanager.ui;

import bookmanager.ui.user.AddUserUI;
import bookmanager.ui.user.DeleteUserUI;
import bookmanager.ui.user.SearchAllUserUI;
import bookmanager.ui.user.UpdateUserUI;

// 회원 관리 메뉴 UI
public class UserManagerUI extends BaseUI {

	@Override
	public void execute()  {
		
		wh1:while(true) {
			try {
				InterfaceUI ui = null;
				int type = menu();
				switch (type) {
				case 1:
					ui = new SearchAllUserUI();
					break;
				case 2:
					ui = new AddUserUI();
					break;
				case 3:
					ui = new DeleteUserUI();
					break;
				case 4:
					ui = new UpdateUserUI();
					break;
				case 0:
					break wh1;
				}
				
				if(ui != null)
					ui.execute();
				else
					System.out.println("번호를 잘못선택하셨습니다.");
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int menu() {
		System.out.println("-------------------------");
		System.out.println("\t회원관리");
		System.out.println("-------------------------");
		System.out.println("    1. 전체 회원 조회");
		System.out.println("    2. 회원정보 추가");
		System.out.println("    3. 회원정보 삭제");
		System.out.println("    4. 회원정보 수정");
		System.out.println("    0. 뒤로가기");
		System.out.println("-------------------------");
		int type = scanInt("원하는 항목을 선택하세요 : ");
		
		return type;
	}

}

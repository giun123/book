package bookmanager.ui.user;

import bookmanager.ui.BaseUI;

// 회원 삭제 UI
public class DeleteUserUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		new SearchAllUserUI().SearchAllUser();

		int no = scanInt("삭제할 회원번호를 입력하세요 : ");

		int cnt = uservice.deleteUser(no);
		
		if(cnt != 0)
			System.out.println("회원 번호[" + no + "]에 해당하는 회원을 삭제하였습니다");
		else
			System.out.println("회원 번호[ " +  no + "]에 해당하는 회원이 존재하지 않습니다");
		 
		
	}

	
}

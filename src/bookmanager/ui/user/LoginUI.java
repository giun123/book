package bookmanager.ui.user;

import bookmanager.ui.AdminUI;
import bookmanager.ui.BaseUI;
import bookmanager.ui.UserUI;
import bookmanager.vo.UserVO;

// 로그인 UI
public class LoginUI extends BaseUI{

	public void execute() {

		String id = scanStr("아이디를 입력하세요 : ");
		String password = scanStr("비밀번호를 입력하세요 : ");
		
		///////////////
		UserVO user = new UserVO();
		user.setId(id);
		user.setPassword(password);
		
		UserVO login_result = uservice.userLogin(user);
		
		if( login_result != null) {
			System.out.println("\n로그인을 성공했습니다");
			user_no = login_result.getNo();
			user_id = login_result.getId();
			user_name = login_result.getName();
			
			if( is_admin() ) {
				new AdminUI().execute();
			} else {
				new UserUI().execute();
			}
			
		} else {
			System.out.println("\n로그인을 실패했습니다");
		}
		////////////////
		
		
	}


}

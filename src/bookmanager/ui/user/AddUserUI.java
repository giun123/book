package bookmanager.ui.user;

import bookmanager.ui.BaseUI;
import bookmanager.vo.UserVO;

// 회원 추가 UI
public class AddUserUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		String id = scanStr("아이디를 입력하세요 : ", true);
		String password = scanStr("패스워드를 입력하세요 : ", true);
		String name = scanStr("이름을 입력하세요 : ", true);
		String phone_number = scanStr("전화번호를 입력하세요 : ");

		////////////////
		UserVO user = new UserVO();

		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setPhoneNumber(phone_number);

		UserVO result_user = uservice.searchUser("id", user);
		if (result_user == null) {
			int cnt = uservice.insertUser(user);
			if (cnt != 0)
				System.out.println("\n회원정보가 추가되었습니다");
			else
				System.out.println("\n회원정보가 추가되지않았습니다");
		} else
			System.out.println("\n아이디가 중복되는 회원이 존재합니다");
	}

}

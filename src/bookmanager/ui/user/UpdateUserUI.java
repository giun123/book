package bookmanager.ui.user;

import bookmanager.ui.BaseUI;
import bookmanager.vo.UserVO;

// 회원 수정
public class UpdateUserUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		new SearchAllUserUI().SearchAllUser();

		UserVO user = new UserVO();
		int no = scanInt("수정할 사용자 번호를 입력하세요 : ");
		int num = scanInt("1 : 비밀번호\t2 : 이름\t3 : 전화번호\t0 : 뒤로가기\t : ");

		user.setNo(no);
		if (num != 0) {
			switch (num) {
			case 1:
				String password = scanStr("변경할 비밀번호를 입력하세요 : ");
				user.setPassword(password);
				break;
			case 2:
				String name = scanStr("변경할 이름을 입력하세요 : ");
				user.setName(name);
				break;
			case 3:
				String phone_number = scanStr("변경할 전화번호를 입력하세요 : ");
				user.setPhoneNumber(phone_number);
				break;
			}
			int cnt = uservice.updateUser(user, num);
			
			if(cnt != 0)
				System.out.println("회원정보 수정을 완료했습니다");
			else
				System.out.println("회원정보 수정을 실패했습니다");
		}

	}

}

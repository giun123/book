package bookmanager.ui.user;

import bookmanager.ui.BaseUI;
import bookmanager.vo.UserVO;

// 본인의 회원 정보 확인
public class UserInfoUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		UserVO user = new UserVO();
		user.setNo(user_no);

		viewUserInfo(user);

		updateUserInfo(user);

	}

	private void viewUserInfo(UserVO user) {
		UserVO result = uservice.searchUser("no", user);

		System.out.println("----------------------------------------------");
		System.out.println("\t\t회원정보 조회 결과");
		System.out.println("----------------------------------------------");
		System.out.println("   아이디   : " + result.getId());
		System.out.println("   비밀번호 : " + result.getPassword());
		System.out.println("   이    름 : " + result.getName());
		System.out.println("   전화번호 : " + result.getPhoneNumber());
		System.out.println("   가입일시 : " + result.getRegDate());
		System.out.println("----------------------------------------------\n");
	}

	private void updateUserInfo(UserVO user) {
		System.out.println("회원정보를 수정하려는 경우 수정할 항목을 선택하세요 : ");

		int num = scanInt("1.비밀번호 \t 2.전화번호\t 0.뒤로가기 ");
		if (num != 0) {
			switch (num) {
			case 1:
				String password = scanStr("변경할 비밀번호를 입력하세요 : ");
				user.setPassword(password);
				break;

			case 2:
				String phone_number = scanStr("변경할 전화번호를 입력하세요 : ");
				user.setPhoneNumber(phone_number);
				break;
			}

			int cnt = uservice.updateUser1(user, num);

			if (cnt != 0)
				System.out.println("회원정보 수정을 완료했습니다");
			else
				System.out.println("회원정보 수정을 실패했습니다");
		}
	}

}

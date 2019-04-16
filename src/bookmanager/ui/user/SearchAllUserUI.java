package bookmanager.ui.user;

import java.util.List;

import bookmanager.ui.BaseUI;
import bookmanager.vo.UserVO;

// 모든 회원 조회
public class SearchAllUserUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		SearchAllUser();
	}

	public void SearchAllUser() {
		List<UserVO> bookList = uservice.searchAllUsers();

		System.out.println("\n-------------------------------------------------------------------");
		System.out.printf("%-10s%-15s\t%-15s\t%-15s\t%-10s\n", "번호", "아이디", "패스워드", "이름", "전화번호");
		System.out.println("-------------------------------------------------------------------");

		for (UserVO book : bookList) {
			System.out.printf("%d\t%-10s \t%-10s \t%-10s \t%-5s\n", book.getNo(), book.getId(), book.getPassword(),
					book.getName(), book.getPhoneNumber());
		}

		if (bookList.isEmpty()) {
			System.out.println("조회할 게시물이 없습니다 ");
		}
		System.out.println("-------------------------------------------------------------------\n");

	}

}
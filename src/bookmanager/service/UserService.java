package bookmanager.service;

import java.util.List;

import bookmanager.dao.UserDAO;
import bookmanager.vo.UserVO;

public class UserService {
	private UserDAO dao;
	
	// DAO 생성
	public UserService() {
		dao = new UserDAO();
	}

	// 회원 추가
	public int insertUser(UserVO user) {
		return dao.insertUser(user);
	}

	// 회원 로그인
	public UserVO userLogin(UserVO user) {
		return dao.userLogin(user);
	}

	// 회원 삭제
	public int deleteUser(int no) {
		return dao.deleteUser(no);
	}

	// 모든 회원 조회
	public List<UserVO> searchAllUsers() {
		return dao.searchAllUsers();
	}

	// 컬럼으로 회원 검색
	public UserVO searchUser(String column, UserVO user) {
		return dao.searchUser(column, user);
	}

	// 회원 수정 (관리자)
	public int updateUser(UserVO user, int num) {
		return dao.updateUser(user, num);
		
	}

	// 회원 수정 (회원)
	public int updateUser1(UserVO user, int num) {
		return dao.updateUser1(user, num);
		
	}
	

}

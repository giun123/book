package bookmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookmanager.util.ConnectionFactory;
import bookmanager.util.JDBCClose;
import bookmanager.vo.UserVO;

public class UserDAO {

	/**
	 * 유저 추가
	 * @param user
	 * @return
	 */
	public int insertUser(UserVO user) {
		Connection conn = null; // DB 접속
		PreparedStatement pstmt = null; // 쿼리 실행

		int cnt = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder(); // 쿼리 작성 개체
			sql.append("insert into tbl_Users(no, id, password, name, phone_number) "); // 쿼리 작성
			sql.append(" values(seq_tbl_user_no.nextval, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString()); // 쿼리를 실행할수있는 객체

			pstmt.setString(1, user.getId()); // ?에 변수 넣기
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPhoneNumber());

			cnt = pstmt.executeUpdate(); // 쿼리 실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return cnt;
	}

	/**
	 * 로그인 정보가 있는경우 유저 객체 리턴
	 * @param user
	 * @return
	 */
	public UserVO userLogin(UserVO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserVO result_user = null;
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from tbl_users ");
			sql.append(" where id = ? and password = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result_user = new UserVO();

				int no = rs.getInt("no");
				String id = rs.getString("id");
				String name = rs.getString("name");
				result_user.setNo(no);
				result_user.setId(id);
				result_user.setName(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return result_user;
	}

	/**
	 * 전체 회원 조회
	 * @return
	 */
	public List<UserVO> searchAllUsers() {

		List<UserVO> userList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select no, id, password, name, phone_number, reg_date");
			sql.append("     , to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from tbl_users order by no");

			pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String phone_number = rs.getString("phone_number");
				String reg_date = rs.getString("reg_date");

				UserVO user = new UserVO(no, id, password, name, phone_number, reg_date);
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return userList;
	}

	/**
	 * 회원 검색
	 * @param column no 혹은 id로 검색
	 * @param user
	 * @return
	 */
	public UserVO searchUser(String column, UserVO user) {
		UserVO result_user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select no, id, password, name, phone_number, ");
			sql.append("     to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from tbl_users");
			String query = " where " + column + " = ?";
			sql.append(query);
//			sql.append(" where no = ?");

			pstmt = conn.prepareStatement(sql.toString());
			switch (column) {
			case "no":
				pstmt.setInt(1, user.getNo());
				break;
			case "id":
				pstmt.setString(1, user.getId());
				break;
			}
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String phoneNumber = rs.getString("phone_number");
				String reg_date = rs.getString("reg_date");

				result_user = new UserVO(no, id, password, name, phoneNumber, reg_date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return result_user;
	}

	/**
	 * 회원번호로 회원 검색
	 * @param no
	 * @return
	 */
	public int deleteUser(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from tbl_users ");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return cnt;
	}

	/**
	 * 회원 정보 수정 
	 * num = 1 : 비밀번호 , num = 2 : 이름, num = 3 : 전화번호
	 * @param user
	 * @param num
	 * @return
	 */
	public int updateUser(UserVO user, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			if (num == 1) {
				sql.append("update tbl_users ");
				sql.append("  set password = ? ");
				sql.append(" where no = ? ");
			} else if (num == 2) {
				sql.append("update tbl_users ");
				sql.append("  set name = ? ");
				sql.append(" where no = ? ");
			} else if (num == 3) {
				sql.append("update tbl_users ");
				sql.append("  set phone_number = ? ");
				sql.append(" where no = ? ");
			}

			pstmt = conn.prepareStatement(sql.toString());

			if (num == 1) {
				pstmt.setString(1, user.getPassword());
				pstmt.setInt(2, user.getNo());
			} else if (num == 2) {
				pstmt.setString(1, user.getName());
				pstmt.setInt(2, user.getNo());
			} else if (num == 3) {
				pstmt.setString(1, user.getPhoneNumber());
				pstmt.setInt(2, user.getNo());
			}
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return cnt;
	}

	/**
	 * 회원이 본인 정보 수정
	 * num = 1 : 비밀번호 , num = 3 : 전화번호
	 * @param user
	 * @param num
	 * @return
	 */
	public int updateUser1(UserVO user, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			if (num == 1) {
				sql.append("update tbl_users ");
				sql.append("  set password = ? ");
				sql.append(" where no = ? ");
			} else if (num == 2) {
				sql.append("update tbl_users ");
				sql.append("  set phone_number = ? ");
				sql.append(" where no = ? ");
			}

			pstmt = conn.prepareStatement(sql.toString());

			if (num == 1) {
				pstmt.setString(1, user.getPassword());
				pstmt.setInt(2, user.getNo());
			} else if (num == 2) {
				pstmt.setString(1, user.getPhoneNumber());
				pstmt.setInt(2, user.getNo());
			}
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return cnt;
	}
}

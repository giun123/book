package bookmanager.vo;

public class UserVO {
	private int no;
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	private String regDate;
	
	public UserVO() {
		super();
	}

	public UserVO(int no, String id, String password, String name, String phoneNumber, String regDate) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "UsersVO [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", regDate=" + regDate + "]";
	}
	
	
}

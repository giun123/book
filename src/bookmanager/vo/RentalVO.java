package bookmanager.vo;

public class RentalVO { // 대여 정보
	private int no;
	private String name;
	private int book_no;
	private String book_name;
	private String lend_date;
	private String back_date;

	public RentalVO() {
		
	}

	public RentalVO(int no, String name, int book_no, String book_name, String lend_date, String back_date) {
		this.no = no;
		this.name = name;
		this.book_no = book_no;
		this.book_name = book_name;
		this.lend_date = lend_date;
		this.back_date = back_date;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBook_no() {
		return book_no;
	}

	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getLend_date() {
		return lend_date;
	}

	public void setLend_date(String lend_date) {
		this.lend_date = lend_date;
	}

	public String getBack_date() {
		return back_date;
	}

	public void setBack_date(String back_date) {
		this.back_date = back_date;
	}
}

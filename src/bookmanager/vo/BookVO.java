package bookmanager.vo;

public class BookVO {
	private int no;
	private String Title;
	private String writer;
	private int is_rental;
	private String preview;

	public BookVO() {

	}

	public BookVO(int no, String Title, String writer, int is_rental, String preview) {
		this.no = no;
		this.Title = Title;
		this.writer = writer;
		this.is_rental = is_rental;
		this.preview = preview;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getIs_rental() {
		return is_rental;
	}

	public void setIs_rental(int is_rental) {
		this.is_rental = is_rental;
	}

	public String getPreview() {
		return preview;
	}
	
	public void setPreview(String preview) {
		this.preview = preview;
	}

}

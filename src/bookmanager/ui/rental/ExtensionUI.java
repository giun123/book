package bookmanager.ui.rental;

import bookmanager.ui.BaseUI;

// 책 대여기간 연장 UI
public class ExtensionUI extends BaseUI {
	
	private int book_no;
	public ExtensionUI() {
		
	}
	
	public ExtensionUI(int book_no) {
		this.book_no = book_no;
	}

	@Override
	public void execute() {
		rservice.RentalExtension(book_no);
		
	}
	
}

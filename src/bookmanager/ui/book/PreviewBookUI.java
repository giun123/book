package bookmanager.ui.book;

import bookmanager.ui.BaseUI;
import bookmanager.vo.BookVO;

// 책 미리보기 UI
public class PreviewBookUI extends BaseUI {
	private int book_no;

	public PreviewBookUI() {
		
	}

	public PreviewBookUI(int book_no) {
		this.book_no = book_no;
	}

	@Override
	public void execute() throws Exception {

		BookVO book = bservice.SearchOneBook(book_no);

		System.out.println("-------------------------------------------");
		System.out.println("\t\t미리보기");
		System.out.println("-------------------------------------------");
		String preview = book.getPreview();
		
		if( preview == null) {
			System.out.println("미리보기가 없습니다");
		} else {
			System.out.println(preview);
		}
		System.out.println("-------------------------------------------\n");

	}

}

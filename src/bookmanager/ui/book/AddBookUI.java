package bookmanager.ui.book;

import bookmanager.ui.BaseUI;
import bookmanager.vo.BookVO;

// 책 추가 UI
public class AddBookUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		String name = scanStr("책제목을 입력하세요 : ", true);
		String writer = scanStr("저자 입력하세요 : ", true);
		String preview = scanStr("미리 보기를 입력하세요 : ");
		int is_rental = 1;
		BookVO book = new BookVO();
		
		book.setTitle(name);
		book.setWriter(writer);
		book.setPreview(preview);
		book.setIs_rental(is_rental);
		
		bservice.insertBook(book);
		
		System.out.println("도서 정보가 추가 되었습니다");
		
	}

	
}

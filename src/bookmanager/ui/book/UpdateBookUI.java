package bookmanager.ui.book;

import bookmanager.ui.BaseUI;
import bookmanager.vo.BookVO;

// 책 수정 UI
public class UpdateBookUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		BookVO book = new BookVO();

		int no = scanInt("수정할 도서번호를 입력하세요 : ");
		int num = scanInt("1 : 책제목\t2 : 저자\t\t 3 : 미리보기\t\t 0 : 뒤로가기\t ");

		book.setNo(no);

		if (num != 0) {
			switch (num) {
			case 1:
				String title = scanStr("변경할 책제목을 입력하세요 : ");
				book.setTitle(title);
				break;
			case 2:
				String writer = scanStr("변경할 저자이름을 입력하세요 : ");
				book.setWriter(writer);
				break;
			case 3:
				String preview = scanStr("변경할 미리보기를 입력하세요 : ");
				book.setPreview(preview);
				break;
			}
			bservice.updateBook(book, num);
			
			System.out.println("도서정보를 수정하였습니다");
		}
	}
}

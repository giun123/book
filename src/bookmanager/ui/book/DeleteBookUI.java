package bookmanager.ui.book;

import bookmanager.ui.BaseUI;

// 책 삭제 UI
public class DeleteBookUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		int no = scanInt("삭제할 도서번호를 입력하세요 : ");

		boolean bool = bservice.deleteBook(no);
		
		if(bool)
			System.out.println("도서 번호[" + no + "]에 해당 도서를 삭제하였습니다");
		else
			System.out.println("도서 번호[ " +  no + "]에 해당 도서가 존재하지 않습니다");
		 
		
	}

	
}

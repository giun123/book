package bookmanager.ui;

import bookmanager.ui.book.AddBookUI;
import bookmanager.ui.book.DeleteBookUI;
import bookmanager.ui.book.SearchAllBooksUI;
import bookmanager.ui.book.UpdateBookUI;

//도서 메뉴 UI
public class BooksUI extends BaseUI {

	@Override
	public void execute()  {
		
		wh1:while(true) {
			try {
				InterfaceUI ui = null;
				int type = menu();
				switch (type) {
				case 1:
					ui = new SearchAllBooksUI();
					break;
				case 2:
					ui = new AddBookUI();
					break;
				case 3:
					ui = new DeleteBookUI();
					break;
				case 4:
					ui = new UpdateBookUI();
					break;
				case 0:
					break wh1;
				}
				
				if(ui != null)
					ui.execute();
				else
					System.out.println("번호를 잘못선택하셨습니다.");
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int menu() {
		System.out.println("\n-------------------------");
		System.out.println("\t도서 관리 메뉴");
		System.out.println("-------------------------");
		System.out.println("    1. 도서 조회");
		System.out.println("    2. 도서 추가");
		System.out.println("    3. 도서 삭제");
		System.out.println("    4. 도서 수정");
		System.out.println("    0. 뒤로가기");
		System.out.println("-------------------------\n");
		int type = scanInt("원하는 항목을 선택하세요 : ");
		
		return type;
	}

}

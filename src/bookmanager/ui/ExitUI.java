package bookmanager.ui;

// 프로그램 종료 UI
public class ExitUI extends BaseUI{
	@Override
	public void execute() throws Exception {
		System.out.println("도서 관리 프로그램을 종료합니다");
		System.exit(0);
	}

}

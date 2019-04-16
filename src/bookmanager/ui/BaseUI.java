package bookmanager.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import bookmanager.service.BookService;
import bookmanager.service.BookServiceFactory;
import bookmanager.service.RentalService;
import bookmanager.service.RentalServiceFactory;
import bookmanager.service.UserService;
import bookmanager.service.UserServiceFactory;
import bookmanager.vo.RentalVO;

public abstract class BaseUI implements InterfaceUI {
	private Scanner sc;
	protected BookService bservice;
	protected RentalService rservice;
	protected UserService uservice;
	protected static int user_no;
	protected static String user_id;
	protected static String user_name;

	public BaseUI() {
		sc = new Scanner(System.in);
		bservice = BookServiceFactory.getInstance();
		rservice = RentalServiceFactory.getInstance();
		uservice = UserServiceFactory.getInstance();
	}

	// int형 변수 입력
	protected int scanInt(String msg) {
		System.out.print(msg);

		return Integer.parseInt(sc.nextLine());
	}

	// String형 변수 입력
	protected String scanStr(String msg) {
		System.out.print(msg);
		
		return sc.nextLine();
	}
	
	/**
	 * 필수 입력가능하게
	 * @param msg
	 * @param is_required
	 * @return
	 */
	protected String scanStr(String msg, boolean is_required) {
		System.out.print(msg);
		String str = sc.nextLine();
		if( is_required && str.equals("")) {
			System.out.println("해당 항목을 필수입니다");
			str = scanStr(msg, is_required);
		}

		return str;
	}
	
	// 현재 관리자인지 리턴
	public boolean is_admin() {
		if( user_id.equals("admin") && user_name.equals("admin") )
			return true;
		else
			return false;
	}
	
	// 대여 정보에서 연체정보 조회
	public void viewOverDue(RentalVO rental) {
		try {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date back_date = transFormat.parse(rental.getBack_date());
			Date date = new Date();
			
			long diff = date.getTime() - back_date.getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		    if( diffDays > 0) {
		    	System.out.print( "\t" + diffDays + "일 연체");
		    } else {
		    	System.out.print( "\t" + (-1*diffDays+1) + "일 남음");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void RentalInfo(List<RentalVO> result) {

		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t대여 정보 조회 결과");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.printf("%s \t%s \t%s \t%-15s \t%-10s \t%-10s \t%-10s\n", "유저번호", "이름", "도서 번호", 
				"대여 날짜",	"반납 날짜", "연체 여부", "도서 이름");
		System.out.println("----------------------------------------------------------------------------------------------");

		for (RentalVO rental : result) {
			System.out.printf("%d \t%s \t%d \t%s \t%s", rental.getNo(), rental.getName(), rental.getBook_no(),
					rental.getLend_date(), rental.getBack_date());
			viewOverDue(rental); // 연체 여부
			System.out.printf("\t\t%-10s\n", rental.getBook_name());
		}
		System.out.println("----------------------------------------------------------------------------------------------");
	}

}

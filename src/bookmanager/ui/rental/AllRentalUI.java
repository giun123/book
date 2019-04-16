package bookmanager.ui.rental;

import java.util.List;

import bookmanager.ui.BaseUI;
import bookmanager.vo.RentalVO;

// 모든 대여 정보 조회 UI
public class AllRentalUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		List<RentalVO> result = rservice.SearchAllRental(0);

		if (result.isEmpty()) {
			System.out.println("대여된 도서가 없습니다");
		} else {
			RentalInfo(result);
		}
	}
	
}

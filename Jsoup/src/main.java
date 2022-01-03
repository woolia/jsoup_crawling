import java.util.ArrayList;

import console.ViewDAO;
import entity.SongVO;
import repository.DbDAO;
import service.CrawlingDAO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<SongVO> SL = new ArrayList<SongVO>();

		
		// 크롤링
		//CrawlingDAO.Crawling(SL);
		
		// 크롤링 된 정보를 DB 에 업로드
		//DbDAO.UploadToDB(SL);
		
		// 크롤링과 DB 업로드이 같은 트랜잭션이고
		
		// DB 다운로드와 결과확인이 같은 트랜잭션이다.
		
		// 그래서 함께 사용 x 
		
		// DB에서 정보를 다운로드 하는 메서드
		DbDAO.DownloadToDB(SL);
		
		// console창에서 결과확인 및 기능 이용
		ViewDAO.view_in_console(SL);
		
	}

}

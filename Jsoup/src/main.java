import java.util.ArrayList;

import console.ViewDAO;
import entity.SongVO;
import repository.DbDAO;
import service.CrawlingDAO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<SongVO> SL = new ArrayList<SongVO>();

		
		// ũ�Ѹ�
		//CrawlingDAO.Crawling(SL);
		
		// ũ�Ѹ� �� ������ DB �� ���ε�
		//DbDAO.UploadToDB(SL);
		
		// ũ�Ѹ��� DB ���ε��� ���� Ʈ������̰�
		
		// DB �ٿ�ε�� ���Ȯ���� ���� Ʈ������̴�.
		
		// �׷��� �Բ� ��� x 
		
		// DB���� ������ �ٿ�ε� �ϴ� �޼���
		DbDAO.DownloadToDB(SL);
		
		// consoleâ���� ���Ȯ�� �� ��� �̿�
		ViewDAO.view_in_console(SL);
		
	}

}

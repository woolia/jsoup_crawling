package service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import entity.SongVO;

public class CrawlingDAO {

	public static void Crawling(ArrayList<SongVO> SL) {
		
		String url = "https://www.melon.com/chart/index.htm"; // melon 100�� ��Ʈ
		// JSoup ���̺귯�� (Document , Elements)  -> Maven pom.xml �� �߰�
		Document doc = null;
		Elements tmp;
		
        /*
	        Document Ŭ���� : �����ؼ� ���� HTML ��ü ����
	        Element Ŭ����  : Documnet�� HTML ���
	        Elements Ŭ���� : Element�� ���� �ڷ���
         */       
		
		String name_temp = null;
		String singer_temp = null;
		String album_temp = null;
		int rank = 0;
		
		try {
			doc = Jsoup.connect(url).get(); //Document���� �������� ��ü �ҽ��� ����ȴ�
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//select�� �̿��Ͽ� ���ϴ� �±׸� �����Ѵ�.
		// ����� ��� 1~50���� <tr class="lst50"> ���� ������ �ְ� 51~100���� <tr class="lst100"> ���� ������ �ִ�.
		// �׷��� ���� lst50 �� ���� �κ������� ���� �����´�.
		
		Elements element = doc.select("div#tb_list").select("tr.lst50"); // div�±��� id(#)�� tb_list �� ������ tr�±��� class(.)�� lst50 �� ���� �ǹ�
		
		for(int i =0 ; i<50 ; i++) {
			// ������ �������� ����
			tmp = element.select("div.ellipsis.rank01"); // element �ȿ� �ִ� �±��� div�±��� Ŭ������ ellipsis rank01 �� ��� -> �̷��� ���Ⱑ ������ .���� ���ᰡ�� div. div�� Ŭ������ �ǹ�
			name_temp = tmp.get(i).text();
			/* -------------------- .select("div.ellipsis.rank01").select("span"); ���� �׽�Ʈ �� �κ� ---------------------  */
			
			// ������ �������� ����
			tmp = element.select("div.ellipsis.rank02").select("span");
			singer_temp = tmp.get(i).text();
			
			
			// �ٹ��� �������� ����
			tmp = element.select("div.ellipsis.rank03");
			album_temp = tmp.get(i).text();
			
			// ����� �������� ����
			tmp = element.select("span.rank"); // span �±��� Ŭ������ rank
			rank = Integer.parseInt((tmp.get(i).text()));
			//rank = Integer.parseInt(tmp.get(i).text());
			/* ------------ select("span.rank "); ���� �׽�Ʈ �� �κ� -------------------- */
			
			// arrayList�� ����

			System.out.printf("data : %d %s %s %s \n" , rank , name_temp , singer_temp , album_temp);
			SL.add(new SongVO(rank, name_temp, singer_temp, album_temp));
		}
		
		// 51~ 100�� �� �۾�
		// element �� tr.lst100 ���� �ٲٱ⸸ �ϸ� ��  
		
		element = doc.select("div#tb_list").select("tr.lst100");
		for(int i =0 ; i<50 ; i++) {

			tmp = element.select("div.ellipsis.rank01"); 
			name_temp = tmp.get(i).text();

			tmp = element.select("div.ellipsis.rank02").select("span");
			singer_temp = tmp.get(i).text();
			

			tmp = element.select("div.ellipsis.rank03");
			album_temp = tmp.get(i).text();

			tmp = element.select("span.rank"); 
			rank = Integer.parseInt((tmp.get(i).text()));

			System.out.printf("data : %d %s %s %s \n" , rank , name_temp , singer_temp , album_temp);
			SL.add(new SongVO(rank, name_temp, singer_temp, album_temp));
		}
		
		System.out.println("Crawling complete");
		
		
	}
}

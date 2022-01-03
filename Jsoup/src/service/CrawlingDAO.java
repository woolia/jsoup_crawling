package service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import entity.SongVO;

public class CrawlingDAO {

	public static void Crawling(ArrayList<SongVO> SL) {
		
		String url = "https://www.melon.com/chart/index.htm"; // melon 100위 차트
		// JSoup 라이브러리 (Document , Elements)  -> Maven pom.xml 에 추가
		Document doc = null;
		Elements tmp;
		
        /*
	        Document 클래스 : 연결해서 얻어온 HTML 전체 문서
	        Element 클래스  : Documnet의 HTML 요소
	        Elements 클래스 : Element가 모인 자료형
         */       
		
		String name_temp = null;
		String singer_temp = null;
		String album_temp = null;
		int rank = 0;
		
		try {
			doc = Jsoup.connect(url).get(); //Document에는 페이지의 전체 소스가 저장된다
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//select를 이용하여 원하는 태그를 선택한다.
		// 멜론의 경우 1~50위는 <tr class="lst50"> 으로 감싸져 있고 51~100위는 <tr class="lst100"> 으로 감싸져 있다.
		// 그래서 먼저 lst50 인 곳만 부분적으로 먼저 가져온다.
		
		Elements element = doc.select("div#tb_list").select("tr.lst50"); // div태그의 id(#)가 tb_list 인 곳에서 tr태그의 class(.)가 lst50 인 곳을 의미
		
		for(int i =0 ; i<50 ; i++) {
			// 제목을 가져오는 과정
			tmp = element.select("div.ellipsis.rank01"); // element 안에 있는 태그중 div태그의 클래스가 ellipsis rank01 인 경우 -> 이렇게 띄어쓰기가 있으면 .으로 연결가능 div. div의 클래스를 의미
			name_temp = tmp.get(i).text();
			/* -------------------- .select("div.ellipsis.rank01").select("span"); 인지 테스트 할 부분 ---------------------  */
			
			// 가수를 가져오는 과정
			tmp = element.select("div.ellipsis.rank02").select("span");
			singer_temp = tmp.get(i).text();
			
			
			// 앨범을 가져오는 과정
			tmp = element.select("div.ellipsis.rank03");
			album_temp = tmp.get(i).text();
			
			// 등수를 가져오는 과정
			tmp = element.select("span.rank"); // span 태그의 클래스가 rank
			rank = Integer.parseInt((tmp.get(i).text()));
			//rank = Integer.parseInt(tmp.get(i).text());
			/* ------------ select("span.rank "); 인지 테스트 할 부분 -------------------- */
			
			// arrayList에 삽입

			System.out.printf("data : %d %s %s %s \n" , rank , name_temp , singer_temp , album_temp);
			SL.add(new SongVO(rank, name_temp, singer_temp, album_temp));
		}
		
		// 51~ 100위 권 작업
		// element 를 tr.lst100 으로 바꾸기만 하면 됨  
		
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

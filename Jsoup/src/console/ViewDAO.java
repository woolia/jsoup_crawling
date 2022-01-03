package console;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import entity.SongVO;

public class ViewDAO {

	
	// 메인 콘솔창
	public static void view_in_console(ArrayList<SongVO> SL) {
		Scanner sc = new Scanner(System.in);
		String mainMenu = "------------------------ 멜론 --------------------\n1. 음원 차트 확인 \n2. 검색 \n3. 종료 \n-------------";
		int choice = 0;
		
		while(choice != -1) {
			
			System.out.println(mainMenu);
			System.out.printf("번호를 선택하세요 : ");
			choice = Integer.parseInt(sc.nextLine());
			
			choice = algorithm(sc , choice , SL);
		}
	}
	
	// 콘솔창 알고리즘 메서드
	private static int algorithm(Scanner sc, int num, ArrayList<SongVO> SL) {
		String search;
		int length = SL.size();
		
		if(num == 1) {
			System.out.println("--------------------- 멜론 차트 순위");
			System.out.println("-------------------------------------");
			
			for(int i = 0; i<length; i++) {
				System.out.printf("%d위\t%s\t%s\t%s\n",SL.get(i).getRank(), SL.get(i).getName(),SL.get(i).getArtist(),SL.get(i).getAlbum());
			}
			System.out.println("-------------------------------------");
			return 1;
		}
		else if(num == 2) {
			HashSet<Integer> resultKey = new HashSet<Integer>();
			System.out.println("------------- 검색 -------------");
			System.out.printf("노래/가수/앨범명을 입력하세요 (하나만)");
			search = sc.nextLine();
			
			Searching(search, SL, resultKey);
			Iterator itr = resultKey.iterator();
			if(resultKey.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
			}
			else {
				int i =0;
				System.out.printf("총 %d 개의 검색 결과가 있습니다. \n" , resultKey.size());
				while(itr.hasNext()) {
					i = (int) itr.next();
					System.out.printf("%d위\t%s\t%s\t%s\n" , SL.get(i).getRank(),SL.get(i).getName(),SL.get(i).getArtist(),SL.get(i).getAlbum());
				}
			}
			System.out.println("--------------------- 끝");
			return 2;
		}
		else if(num == 3) {
			return -1;
		}
		else {
			System.out.println("1번 2번 3번 중에서만 선택해 주세여");
		}
		return 0;
	}



	// 문자열을 찾는 검색 메서드
	private static void Searching(String search , ArrayList<SongVO> SL , HashSet<Integer> Key) {
		char[] atmp , btmp; // atmp , btmp 배열 선언
		int cnt = 0;
		atmp = search.toCharArray(); // search 문자열을 char형 배열로 바꿔준다. 반환되는 배열의 길이는 문자열의 길이와 같다.
		
		/*
 	예시
	입력
public class Test {
  public static void main(String[] args) {
    String str = "hello world";
    char[] arr = str.toCharArray();
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}

	출력
h e l l o _ w o r l d    _ 은 공백
		 */
	
		System.out.println("SL의 길이 : " + SL.size());
		long start = System.nanoTime();
		for(int i = 0; i<SL.size(); i++) {
			// 이름에서 검색
			
			btmp = SL.get(i).getName().toCharArray(); // SL에서 가져온 이름을 CharArray 형태로 변환
			for(char a : atmp) {
				for(char b : btmp) {
//					System.out.println(a + " : " + b);
					if(a == b && a != ' ') { // 공백이 아닐때만 cnt를 증가시킴 예를 들어 A: 가 나 , B: 다 라   라고 하면 논리적으로는 전혀 다르지만 공백위치는 같게 되어 같은 글자라고 판별
						cnt++;
					}
				}
				if(cnt == 0)
					break;
			}
			if(cnt == atmp.length) {
				Key.add(i);  // 리스트에서 get(i) 로 가져올 수 있도록
			}
			
			cnt = 0; //초기화
			
			
			// 아티스트 중에서 검색
			btmp = SL.get(i).getArtist().toCharArray();
			for(char a : atmp) {
				for(char b : btmp) {
//					System.out.println(a + " : " + b);
					if(a == b && a != ' ') {
						cnt++;
					}
				}
				if(cnt == 0)
					break;
			}
			if(cnt == atmp.length) {
				Key.add(i);
			}
			
			cnt = 0;
			// 앨범중에서 검색
			
			btmp = SL.get(i).getAlbum().toCharArray();
			for(char a : atmp) {
				for(char b : btmp) {
//					System.out.println(a + " : " + b);
					if(a == b && a != ' ') {
						cnt++;
					}
				}
				if(cnt == 0)
					break;
			}
			
			if(cnt == atmp.length) {
				Key.add(i);
			}
			cnt = 0;
			
		}
		long end = System.nanoTime();
		
		System.out.println("char 형으로 만든 시간 : " + (end - start));
		
	}
	
	private static void SearchCustom(String search , ArrayList<SongVO> SL , HashSet<Integer> Key) {
		
		String name;
		String artist;
		String album;
		
		
		int cnt = 0;
		
		long start = System.nanoTime();
		for(int i = 0 ; i < SL.size(); i++) {
			
			name = SL.get(i).getName();
			artist = SL.get(i).getArtist();
			album = SL.get(i).getAlbum();
			
			if(artist.contains(search) || album.contains(search) || name.contains(search)) {
				cnt = i;
				Key.add(cnt);
			}
		}
		
		long end = System.nanoTime();
		
		System.out.println("String 형으로 만든 시간 : " + (end - start));
	}
	
	// Char 로 한 Searching 과 그냥 String 메서드를 활용한 Searching 의 차이
	/*
	 * Char : 복잡
	 * String : 단순
	 * 
	 * 먼저 Char 형으로 하면 char 배열값마다 비교하기 때문에 검색을 하더라도 텍스트의 연결유무 상관없이 포함되는 단어를 추출함
	 * ex) 비오 검색 --> 비오 , 비가 오는(연결되지 않지만 글자가 포함) 즉, 검색한 텍스트가 모두 포함하는 데이터를 추출
	 * 반면 String으로 하면 텍스트의 연결유무 상관하여 단어를 추출함
	 * ex) 비오 검색 --> 비오만 나옴 ("비가 오는" 텍스트가 연결되지 않으면 추출 안됨)
	 * 
	 * 속도
	 * Char 형으로 만든 검색엔진이 String 메서드를 활용한 것 보다 2배 더 빠르다
	 * 
	 * Char : 불안정
	 * String : 안정
	 * 
	 * Char 형으로 만들면 아무래도 로직을 직접 짜다보니 결과값이 매우 불안정하게 나온다. ex) 검색어로 빅마마 를 검색했는데 빅마마가 안나옴 반면 비오를 검색하면 가수 비오가 정확하게 나옴
	 * String 형으로 만들면 신뢰가 가는 메서드 이다 보니 결과값이 예상하는 대로 안정하게 나온다.
	 */
	
	
	
}

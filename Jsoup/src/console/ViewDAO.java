package console;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import entity.SongVO;

public class ViewDAO {

	
	// ���� �ܼ�â
	public static void view_in_console(ArrayList<SongVO> SL) {
		Scanner sc = new Scanner(System.in);
		String mainMenu = "------------------------ ��� --------------------\n1. ���� ��Ʈ Ȯ�� \n2. �˻� \n3. ���� \n-------------";
		int choice = 0;
		
		while(choice != -1) {
			
			System.out.println(mainMenu);
			System.out.printf("��ȣ�� �����ϼ��� : ");
			choice = Integer.parseInt(sc.nextLine());
			
			choice = algorithm(sc , choice , SL);
		}
	}
	
	// �ܼ�â �˰��� �޼���
	private static int algorithm(Scanner sc, int num, ArrayList<SongVO> SL) {
		String search;
		int length = SL.size();
		
		if(num == 1) {
			System.out.println("--------------------- ��� ��Ʈ ����");
			System.out.println("-------------------------------------");
			
			for(int i = 0; i<length; i++) {
				System.out.printf("%d��\t%s\t%s\t%s\n",SL.get(i).getRank(), SL.get(i).getName(),SL.get(i).getArtist(),SL.get(i).getAlbum());
			}
			System.out.println("-------------------------------------");
			return 1;
		}
		else if(num == 2) {
			HashSet<Integer> resultKey = new HashSet<Integer>();
			System.out.println("------------- �˻� -------------");
			System.out.printf("�뷡/����/�ٹ����� �Է��ϼ��� (�ϳ���)");
			search = sc.nextLine();
			
			Searching(search, SL, resultKey);
			Iterator itr = resultKey.iterator();
			if(resultKey.size() == 0) {
				System.out.println("�˻� ����� �����ϴ�.");
			}
			else {
				int i =0;
				System.out.printf("�� %d ���� �˻� ����� �ֽ��ϴ�. \n" , resultKey.size());
				while(itr.hasNext()) {
					i = (int) itr.next();
					System.out.printf("%d��\t%s\t%s\t%s\n" , SL.get(i).getRank(),SL.get(i).getName(),SL.get(i).getArtist(),SL.get(i).getAlbum());
				}
			}
			System.out.println("--------------------- ��");
			return 2;
		}
		else if(num == 3) {
			return -1;
		}
		else {
			System.out.println("1�� 2�� 3�� �߿����� ������ �ּ���");
		}
		return 0;
	}



	// ���ڿ��� ã�� �˻� �޼���
	private static void Searching(String search , ArrayList<SongVO> SL , HashSet<Integer> Key) {
		char[] atmp , btmp; // atmp , btmp �迭 ����
		int cnt = 0;
		atmp = search.toCharArray(); // search ���ڿ��� char�� �迭�� �ٲ��ش�. ��ȯ�Ǵ� �迭�� ���̴� ���ڿ��� ���̿� ����.
		
		/*
 	����
	�Է�
public class Test {
  public static void main(String[] args) {
    String str = "hello world";
    char[] arr = str.toCharArray();
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}

	���
h e l l o _ w o r l d    _ �� ����
		 */
	
		System.out.println("SL�� ���� : " + SL.size());
		long start = System.nanoTime();
		for(int i = 0; i<SL.size(); i++) {
			// �̸����� �˻�
			
			btmp = SL.get(i).getName().toCharArray(); // SL���� ������ �̸��� CharArray ���·� ��ȯ
			for(char a : atmp) {
				for(char b : btmp) {
//					System.out.println(a + " : " + b);
					if(a == b && a != ' ') { // ������ �ƴҶ��� cnt�� ������Ŵ ���� ��� A: �� �� , B: �� ��   ��� �ϸ� �������δ� ���� �ٸ����� ������ġ�� ���� �Ǿ� ���� ���ڶ�� �Ǻ�
						cnt++;
					}
				}
				if(cnt == 0)
					break;
			}
			if(cnt == atmp.length) {
				Key.add(i);  // ����Ʈ���� get(i) �� ������ �� �ֵ���
			}
			
			cnt = 0; //�ʱ�ȭ
			
			
			// ��Ƽ��Ʈ �߿��� �˻�
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
			// �ٹ��߿��� �˻�
			
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
		
		System.out.println("char ������ ���� �ð� : " + (end - start));
		
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
		
		System.out.println("String ������ ���� �ð� : " + (end - start));
	}
	
	// Char �� �� Searching �� �׳� String �޼��带 Ȱ���� Searching �� ����
	/*
	 * Char : ����
	 * String : �ܼ�
	 * 
	 * ���� Char ������ �ϸ� char �迭������ ���ϱ� ������ �˻��� �ϴ��� �ؽ�Ʈ�� �������� ������� ���ԵǴ� �ܾ ������
	 * ex) ��� �˻� --> ��� , �� ����(������� ������ ���ڰ� ����) ��, �˻��� �ؽ�Ʈ�� ��� �����ϴ� �����͸� ����
	 * �ݸ� String���� �ϸ� �ؽ�Ʈ�� �������� ����Ͽ� �ܾ ������
	 * ex) ��� �˻� --> ����� ���� ("�� ����" �ؽ�Ʈ�� ������� ������ ���� �ȵ�)
	 * 
	 * �ӵ�
	 * Char ������ ���� �˻������� String �޼��带 Ȱ���� �� ���� 2�� �� ������
	 * 
	 * Char : �Ҿ���
	 * String : ����
	 * 
	 * Char ������ ����� �ƹ����� ������ ���� ¥�ٺ��� ������� �ſ� �Ҿ����ϰ� ���´�. ex) �˻���� �򸶸� �� �˻��ߴµ� �򸶸��� �ȳ��� �ݸ� ����� �˻��ϸ� ���� ����� ��Ȯ�ϰ� ����
	 * String ������ ����� �ŷڰ� ���� �޼��� �̴� ���� ������� �����ϴ� ��� �����ϰ� ���´�.
	 */
	
	
	
}

package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.SongVO;

public class DbDAO {
	
	private static String driver = "org.mariadb.jdbc.Driver"; // mariadb���
	private static String url = "jdbc:mariadb://127.0.0.1:3306/melondb"; // MelonDB �����ͺ��̽��� ����
	private static String user = "zecrar";
	private static String pw = "1234";
	
	
	// DB�� �����ϴ� �޼��� ( DB ���� )
	
	public static void UploadToDB(ArrayList<SongVO> SL) {
		
		Connection conn;
		String sql;

		try {
			Class.forName(driver); // org.mariadb.jdbc.Driver �� �־�� �Ѵ�. jar������ �ٿ�ްų� �ƴϸ� maven ���� ����
			conn = DriverManager.getConnection(url, user, pw);

			if( conn != null) {
				System.out.println("���� ���� !!");
			}
			else {
				System.out.println("���� ����");
				return;
			}
				
			for(int i = 0; i < SL.size(); i++) {
				sql = "insert into MelonDB(rank , name , artist , album) values(?,?,?,?)";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, SL.get(i).getRank());
				st.setString(2, SL.get(i).getName());
				st.setString(3, SL.get(i).getArtist());
				st.setString(4, SL.get(i).getAlbum());
				st.executeUpdate();
			}
			System.out.println("Upload_complete");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	// UploadToDB ����
	
	
	// DB���� ��Ʈ������ �޾ƿ��� �޼���
	public static void DownloadToDB(ArrayList<SongVO> SL) {
		
		Connection conn;
		
		String sql;
		int rank = 0;
		String NT , ST , AT;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			
			if(conn != null) {
				System.out.println("���� ����");
			}
			
			sql = "SELECT * FROM MelonDB";
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				rank = rs.getInt("RANK");
				NT = rs.getString("NAME");
				ST = rs.getString("ARTIST");
				AT = rs.getString("ALBUM");
				
				SL.add(new SongVO(rank , NT , ST , AT));
			}
			System.out.println("Download complete");
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}

package entity;

public class SongVO {

	int rank;
	String name;
	String Artist;
	String Album;
	// DB의 컬럼명과 되도록이면 일치되도록 헷갈리지 않게
	
	
	public SongVO(int rank, String name, String artist, String album) {
		this.rank = rank;
		this.name = name;
		Artist = artist;
		Album = album;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getArtist() {
		return Artist;
	}
	public void setArtist(String artist) {
		Artist = artist;
	}
	
	public String getAlbum() {
		return Album;
	}
	public void setAlbum(String album) {
		Album = album;
	}
	
	
	
	
	
}

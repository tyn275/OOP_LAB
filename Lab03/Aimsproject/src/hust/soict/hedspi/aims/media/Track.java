package hust.soict.hedspi.aims.media;

public class Track implements Playable{
	
	private String title;
	private int length;

	public Track(String title, int length) {
		this.title = title;
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void play() {
	    System.out.println("Playing DVD: " + this.getTitle());
	    System.out.println("DVD length: " + this.getLength());
	}
	
	@Override
	public boolean equals(Object o) {
	    // Kiểm tra nếu tham số là chính đối tượng hiện tại
	    if (this == o) {
	        return true;
	    }

	    // Kiểm tra nếu tham số là null hoặc không phải là đối tượng Track
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }

	    // Ép kiểu Object sang Track
	    Track track = (Track) o;

	    // So sánh thuộc tính title của hai đối tượng
	    return this.title.equalsIgnoreCase(track.title) && this.length == track.length;
	}
}
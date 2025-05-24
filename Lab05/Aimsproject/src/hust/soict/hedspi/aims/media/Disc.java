package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.IllegalItemException;

public class Disc extends Media {
	
	private String director;
	private int length;

    public Disc(int id, String title, String category, float cost, int length, String director) throws IllegalItemException {
        super(id, title, category, cost);
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative: " + length);
        }
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null/empty");
        }
        this.length = length;
        this.director = director;
    }
	
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
}
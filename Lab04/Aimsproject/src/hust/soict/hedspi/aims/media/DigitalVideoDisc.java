package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable{
	private String director;
	private int length;
	private static int nbDigitalVideoDiscs = 0;
	
	public String getDirector() {
		return director;
	}
	public DigitalVideoDisc(String title) {
		super(++nbDigitalVideoDiscs, title, "", 0.0f, "", 0);
	}
	public DigitalVideoDisc(String category, String title, float cost) {
		super(++nbDigitalVideoDiscs, title, category, cost, "", 0);
	}
	public DigitalVideoDisc(String director, String category, String title, float cost) {
		super(++nbDigitalVideoDiscs, title, category, cost, director, 0);
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(++nbDigitalVideoDiscs, title, category, cost, director, length);
	}
	
	//18/04/2025
	public String toString()
	{
		return "DVD - " + getTitle() + " - " + getCategory() + " - " + this.director 
				+ " - " + this.length + " - " + getCost() + " $";
	}
	
	/*public boolean isMatch(String title) {
	    return getTitle().equalsIgnoreCase(title);
	}*/
	
	public void play() {
	    System.out.println("Playing DVD: " + this.getTitle());
	    System.out.println("DVD length: " + this.getLength());
	}
}
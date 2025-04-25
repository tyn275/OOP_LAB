package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable{
	
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	
	public CompactDisc(int id, String title, String category, float cost, String director, int length, String artist) {
		super(id, title, category, cost, director, length);
		this.artist = artist;
	}
	
	public void addTrack(Track track) {
		if(tracks.contains(track))
			System.out.println("Track: " + track.getTitle() + "exists");
		else {
			tracks.add(track);
			System.out.println("Track: " + track.getTitle() + "added");
		}
	}
	
	public void removeTrack(Track track) {
		if(tracks.contains(track)) {
			tracks.remove(track);
			System.out.println("Track: " + track.getTitle() + "removed");
		}
		else
			System.out.println("Track: " + track.getTitle() + "not found");
	}
	
	public int getLength() {
		int totalLength = 0;
		for (Track track: tracks) {
			totalLength += track.getLength();
		}
		return totalLength;
	}
	
	public String getArtist() {
		return artist;
	}

	@Override
	public void play() {
	    System.out.println("Playing CD: " + this.getTitle());
	    for (Track track : tracks) {
	        track.play();
	    }
	}


}
package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable{
	
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	
	 public CompactDisc(int id, String title, String category, String director, int length, float cost, String artist) throws IllegalItemException {
	    	super(id, title, category, cost, director, length);
	        if (artist == null || artist.isBlank()) {
	            throw new IllegalArgumentException("Artist cannot be null/empty");
	        }
	        this.artist = artist;
	        this.tracks = new ArrayList<>();
	    }

	    public CompactDisc(int id, String title, String category, String director, int length, float cost, String artist, ArrayList<Track> tracks) throws IllegalItemException {
	    	super(id, title, category, cost, director, length);
	        if (artist == null || artist.isBlank()) {
	            throw new IllegalArgumentException("Artist cannot be null/empty");
	        }
	        this.artist = artist;
	        this.tracks = tracks;
	    }
	
	    public void addTrack(Track track) {
	        if (track == null) {
	            throw new IllegalArgumentException("Track cannot be null");
	        }
	        if (tracks.contains(track)) {
	            throw new IllegalArgumentException("Track '" + track.getTitle() + "' already exists");
	        }
	        tracks.add(track);
	    }
	
	    public void removeTrack(Track track) {
	        if (!tracks.contains(track)) {
	            throw new NoSuchElementException("Track '" + track.getTitle() + "' not found");
	        }
	        tracks.remove(track);
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
	
    public List<Track> getTracks(){
        return tracks;
    }
    
    public void setTracks(ArrayList<Track> tracks){
        this.tracks = tracks;
    }
    public void setTrack(int index, Track track) {
        if (index < 0 || index >= tracks.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null");
        }
        tracks.set(index, track);
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive");
        }

        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("Total length: " + this.getLength() + " minutes");

        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                throw new PlayerException("Failed to play track: " + track.getTitle() + " - " + e.getMessage());
            }
        }
    }
    
    @Override
    public String play(boolean returnDetails) throws PlayerException {
        play();

        StringBuilder sb = new StringBuilder();
        sb.append("CD: ").append(getTitle()).append("\n");
        sb.append("Artist: ").append(artist).append("\n");
        sb.append("Tracks:\n");

        for (Track track : tracks) {
            sb.append("- ").append(track.getTitle()).append("\n");
            if (returnDetails) {
                sb.append("  Length: ").append(track.getLength()).append(" minutes\n");
            }

        }
        return sb.toString();
    }
    
    
    @Override
    public String toString() {
        return "CD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " $, Artist: " + this.getArtist() + ", Total Tracks: " + this.tracks.size() + ", Total Length: " + this.getLength() + " minutes";
    }

    @Override
    public String printData(){
        String data = "CD detail:\n";
        data += "Title: " + this.getTitle() + "\n";
        data += "Category: " + this.getCategory() + "\n";
        data += "Artist: " + this.getArtist() + "\n";
        data += "Total Tracks: " + this.tracks.size() + "\n";
        data += "Length: " + this.getLength() + "\n";
        data += "Cost: " + this.getCost() + "\n";
        return data;
    }


}
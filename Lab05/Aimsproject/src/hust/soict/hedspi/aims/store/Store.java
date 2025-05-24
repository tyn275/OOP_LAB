package hust.soict.hedspi.aims.store;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;

public class Store {
	
	private ArrayList<Media> itemsInstore = new ArrayList<Media>();
	public static final int MAX_NUMBER_INSTORE = 100;
	
	public void displayStore() {
		for (int k = 0; k < itemsInstore.size(); k++) {
			System.out.println((k+1) + ". " + itemsInstore.get(k).toString());
		}
	}
	
	private boolean IsStoreFull() {
		return itemsInstore.size() >= MAX_NUMBER_INSTORE;
	}
	
	public void addMedia(Media media) {
		if (!IsStoreFull()) {
			itemsInstore.add(media);
			System.out.println("The media has been added\n");
		}
		else 
			System.out.println("The store is almost full\n");
		
	}
	
	public void removeMedia(Media media) {
	    if (itemsInstore.isEmpty()) {
	        System.out.println("The store is empty\n");
	        return;
	    }

	    if (itemsInstore.remove(media)) {
	        System.out.println("The media has been removed\n");
	    } else {
	        System.out.println("The media does not exist\n");
	    }
	}

    // Tìm kiếm theo ID
    public Media searchByID(int id) {
    	boolean found = false;
        for (Media media: itemsInstore) {
            if (media.getId() == id) {
            	found = true;
                System.out.println("Found: " + media.toString());
                return media;
            }
        }
        if (!found)
        	System.out.println("Không tìm thấy DVD với ID: " + id);
        return null;
    }
    
    // Tìm kiếm theo title
    public Media searchByTitle(String title) {
        boolean found = false;
        for (Media media: itemsInstore) {
            if (media.isMatch(title)) {
            	found = true;
            	System.out.println("Found: " + media.toString());
            	return media;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy DVD với tiêu đề: " + title);
        }
		return null;
    }

	public ArrayList<Media> getitemsInstore() {
		return itemsInstore;
	}

}
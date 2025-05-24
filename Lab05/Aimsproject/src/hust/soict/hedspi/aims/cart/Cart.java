package hust.soict.hedspi.aims.cart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import hust.soict.hedspi.aims.exception.CartFullException;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
	public static final int MAX_NUMBER_ORDERED = 20;
	
	public void displayCart() {
		for (int k = 0; k < itemsOrdered.size(); k++) {
			System.out.println((k+1) + ". " + itemsOrdered.get(k).toString());
		}
	}
	
	private boolean IsFull() {
		return itemsOrdered.size() >= MAX_NUMBER_ORDERED;
	}
	
	public void addMedia(Media media) throws CartFullException {
        if (IsFull()) {
            throw new CartFullException("Cart is full (Max: " + MAX_NUMBER_ORDERED + ")");
        }
        itemsOrdered.add(media);
    }


    public void removeMedia(Media media) throws MediaNotFoundException {
        if (!itemsOrdered.contains(media)) {
            throw new MediaNotFoundException("Media not found: " + media.getTitle());
        }
        itemsOrdered.remove(media);
    }
    
    public float totalCost() {
    	float totalCost = 0;
    	if (itemsOrdered.size() == 0) return totalCost;
    	for (Media media: itemsOrdered) {
    		totalCost += media.getCost();
    	}
    	return totalCost;
    }
    
    // In ra danh sách giỏ hàng và tổng giá trị
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        displayCart();

        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("***************************************************");
    }
    
    // Tìm kiếm theo ID
    public Media searchByID(int id) {
    	boolean found = false;
        for (Media media: itemsOrdered) {
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
        for (Media media: itemsOrdered) {
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
    

	public void sortByTitleCost() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Sắp xếp theo tiêu đề:");
        for (Media media : itemsOrdered) {
            System.out.println(media.getTitle() + " - " + media.getCost());
        }
	}

	public void sortByCostTitle() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Sắp xếp theo chi phí:");
        for (Media media : itemsOrdered) {
            System.out.println(media.getTitle() + " - " + media.getCost());
        }
	}

	public void clear() {
		itemsOrdered.clear();
	}
	
	public ObservableList<Media> getitemsOrdered() {
		return itemsOrdered;
	}
}
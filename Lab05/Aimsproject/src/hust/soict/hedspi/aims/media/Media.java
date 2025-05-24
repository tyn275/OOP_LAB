package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import hust.soict.hedspi.aims.exception.IllegalItemException;

import java.util.Collections;

public abstract class Media {
	
	private int id;
	private String title;
	private String category;
	private float cost;
	
    public Media(int id, String title, String category, float cost) throws IllegalItemException {
        if (title == null) {
            throw new IllegalItemException("Title is null");
        }
        if (cost < 0) {
            throw new IllegalItemException("Cost is illegal: " + cost);
        }
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public float getCost() {
		return cost;
	}
	
	public boolean isMatch(String title) {
	    return this.getTitle().equalsIgnoreCase(title);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Media)) return false;
	    
	    Media other = (Media) obj; // Ép kiểu tường minh để hỗ trợ JRE cũ
	    return this.title.equals(other.title) && Float.compare(this.cost, other.cost) == 0;
	}

	public int compareTo(Media other) {
	    if (other == null) {
	        return 1; // Trả về giá trị dương thay vì ném NullPointerException
	    }

	    int titleCompare = this.title.compareTo(other.title);
	    if (titleCompare != 0) {
	        return titleCompare;
	    }

	    return Float.compare(this.cost, other.cost);
	}
	
	public String printData(){return "";}
	
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public class Main {
        public void main(String[] args) {
            // Tạo danh sách media
            List<Media> mediaList = new ArrayList<>();
            mediaList.add(new Book(101, "Harry Potter", "Fantasy", new ArrayList<String>(Arrays.asList("J.K. Rowling")),
    				25.99f));
            mediaList.add(new Book(102, "Clean Code", "Programming",
    				new ArrayList<String>(Arrays.asList("Robert C. Martin")), 32.50f));
            mediaList.add(new Book(103, "The Hobbit", "Fantasy", new ArrayList<String>(Arrays.asList("J.R.R. Tolkien")),
    				20.00f));

            // Sắp xếp theo tiêu đề, sau đó theo chi phí
            Collections.sort(mediaList, Media.COMPARE_BY_TITLE_COST);
            System.out.println("Sắp xếp theo tiêu đề:");
            for (Media media : mediaList) {
                System.out.println(media.getTitle() + " - " + media.getCost());
            }

            // Sắp xếp theo chi phí, sau đó theo tiêu đề
            Collections.sort(mediaList, Media.COMPARE_BY_COST_TITLE);
            System.out.println("Sắp xếp theo chi phí:");
            for (Media media : mediaList) {
                System.out.println(media.getTitle() + " - " + media.getCost());
            }
        }
    }
}
package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public abstract class Media {
	
	private int id;
	private String title;
	private String category;
	private float cost;
	
	public Media(int id, String title, String category, float cost) {
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
	public boolean equals(Object o) {
	    // Kiểm tra nếu tham số là chính đối tượng hiện tại
	    if (this == o) {
	        return true;
	    }

	    // Kiểm tra nếu tham số là null hoặc không phải là đối tượng Media
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }

	    // Ép kiểu Object sang Media
	    Media media = (Media) o;

	    // So sánh thuộc tính title của hai đối tượng
	    return this.title.equalsIgnoreCase(media.title);
	}
	
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
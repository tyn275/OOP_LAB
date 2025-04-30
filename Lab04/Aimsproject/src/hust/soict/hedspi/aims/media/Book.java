package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
	private List<String> authors = new ArrayList<String>();
	
	public Book(int id, String title, String category, ArrayList<String> authors, float cost) {
		super (id, title, category, cost);
		this.authors = new ArrayList<>();
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
	//addAuthor
	public void addAuthor(String authorName) {
		if(!authors.contains(authorName)) {
			authors.add(authorName);
			System.out.println("Author" + authorName + "added");
		}
		else {
			System.out.println("Author" + authorName + "is already in the list");
		}
	}
	
	//removeAuthor
	public void removeAuthor(String authorName) {
		if(authors.contains(authorName)) {
			authors.remove(authorName);
			System.out.println("Author" + authorName + "removed");
		}
		else {
			System.out.println("Author" + authorName + "is not exist");
		}
	}
}
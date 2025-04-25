package hust.soict.hedspi.aims;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;

public class Aims {
	
    private static final Scanner scanner = new Scanner(System.in);
	private static final Store store = new Store();
	private static final Cart cart = new Cart();
	
	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
	}
	
	public static void storeMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. See a media’s details");
		System.out.println("2. Add a media to cart");
		System.out.println("3. Play a media");
		System.out.println("4. See current cart");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
	}
	
	public static void mediaDetailsMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add to cart");
		System.out.println("2. Play");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
		}
	
	public static void cartMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter media in cart");
		System.out.println("2. Sort media in cart");
		System.out.println("3. Remove media from cart");
		System.out.println("4. Play a media");
		System.out.println("5. Place order");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4-5");
	}
	
	public static void viewStore() {
		System.out.println("Store items: ");
		store.displayStore();
		
		int choice;
		
		do {
			storeMenu();
			choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
            case 1: seeMediaDetails();
            break;
            case 2: addMediaToCart();
            break;
            case 3: playMedia();
            break;
            case 4: seeCurrentCart();
            break;
            case 0: System.out.println("Return to main menu");
            break;
            default: System.out.println("Invalid choice");
            break;
            }
		}while(choice != 0);
	}
	
	public static void Update_store() {
		System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.print("Please choose a number: 1-2: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
        	System.out.println("1. Add DVD");
            System.out.println("2. Add CD");
            System.out.println("3. Add Book");
            System.out.print("Please choose a number: 1-2-3: ");
            int type = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            String gabageManager;
            switch(type) {
            case 1:
                System.out.print("Enter director: ");
                String directorDVD = scanner.nextLine();
                
                System.out.print("Enter length: ");
                int lengthDVD = scanner.nextInt();
                
                System.out.print("Enter cost: ");
                float costDvd = scanner.nextFloat();
            	DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, directorDVD, lengthDVD, costDvd);
                store.addMedia(dvd);
            	break;
            case 2:
                System.out.print("Enter director: ");
                String directorCD = scanner.nextLine();
                
                System.out.print("Enter artist: ");
                String artistCD = scanner.nextLine();
                
                System.out.print("Enter length: ");
                int lengthCD = scanner.nextInt();
                
                System.out.print("Enter number of tracks:");
                int tracksSize = scanner.nextInt();
                gabageManager = scanner.nextLine();
                ArrayList<Track> tracks = new ArrayList<Track>();
                for(int i = 1; i <= tracksSize; i++) {
                	System.out.println("Enter track:");
                    String track = scanner.nextLine();
                	System.out.println("Enter track's length:");
                    int lengthTrack = scanner.nextInt();
                    gabageManager = scanner.nextLine();
                	tracks.add(new Track(track, lengthTrack));
                }
                System.out.print("Enter cost: ");
                float costCd = scanner.nextFloat();
                CompactDisc cd = new CompactDisc(store.getitemsInstore().size() + 1, title, category, costCd, directorCD, lengthCD, artistCD);
                store.addMedia(cd);
                break;
                case 3:
                    System.out.println("Enter number of authors:");
                    int authorsSize = scanner.nextInt();
                    gabageManager = scanner.nextLine();
                    ArrayList<String> authors = new ArrayList<String>();
                    for(int i = 1; i <= authorsSize; i++) {
                        System.out.println("Enter author's name:");
                        String author = scanner.nextLine();
                    	authors.add(author);
                    }
                    System.out.print("Enter cost: ");
                    float costBk = scanner.nextFloat();
                    Book bk = new Book(store.getitemsInstore().size() + 1, title, category, costBk);
                    store.addMedia(bk);
                    break;
            }
            System.out.println("Media added.");
        } else if (choice == 2) {
        	System.out.print("Enter title: ");
            String title = scanner.nextLine();
            Media media = store.searchByTitle(title);
            if (media != null) {
                store.removeMedia(media);
                System.out.println("Media removed.");
            } else {
                System.out.println("Media not found.");
            }
        }
	}
	
    public static void seeCurrentCart() {
        cart.displayCart();
        int option;
        do {
            cartMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1: filterCart();
                break;
                case 2: sortCart();
                break;
                case 3: removeFromCart();
                break;
                case 4: playMediaInCart();
                break;
                case 5: {
                    System.out.println("Order placed. Thank you!");
                    cart.clear();
                }
                break;
                case 0: System.out.println("Returning to main menu...");
                break;
                default: System.out.println("Invalid option.");
            }
        } while (option != 0);
    }

    public static void seeMediaDetails() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }
        System.out.println(media.toString());

        int choice;
        do {
            mediaDetailsMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: cart.addMedia(media);
                break;
                case 2: {
                    if (media != null && media instanceof Playable) {
                    	Playable playable = (Playable) media;
                        playable.play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                }
                break;
                case 0: {}
                break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    public static void addMediaToCart() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            cart.addMedia(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null && media instanceof Playable) {
        	Playable playable = (Playable) media;
            playable.play();
        } else {
            System.out.println("Media not found or not playable.");
        }
    }

    public static void filterCart() {
        System.out.println("Filter by: 1. ID  2. Title");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            cart.searchByID(id);
        } else {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            cart.searchByTitle(title);
        }
    }

    public static void sortCart() {
        System.out.println("Sort by: 1. Title  2. Cost");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) {
            cart.sortByTitleCost();
        } else {
            cart.sortByCostTitle();
        }
        cart.displayCart();
    }

    public static void removeFromCart() {
        System.out.print("Enter title to remove: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    public static void playMediaInCart() {
        System.out.print("Enter title to play: ");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if(media != null && media instanceof Playable) {
        	Playable playable = (Playable) media;
            playable.play();
        } else {
            System.out.println("Media not playable or not found.");
        }
    }

	public static void main(String[] args) {
		// Create a new cart
		Cart anOrder = new Cart();

		// Create new DVD objects and add them to the cart
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);

		anOrder.addMedia(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fictions", "George Lucas", 87, 24.95f);

		anOrder.addMedia(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);

		anOrder.addMedia(dvd3);

		System.out.println("Total cost is: ");
		System.out.println(anOrder.totalCost());
		
		
		List<Media> mediae = new ArrayList<>();
		
		//create some media here
		//for example: cd, dvd, book
		
		CompactDisc cd = new CompactDisc(0, null, null, 0, null, 0, null);
		DigitalVideoDisc dvd = new DigitalVideoDisc(null, null, null, 0, 0);
		Book book = new Book(0, null, null, 0);
		mediae.add(cd);
		mediae.add(dvd);
		mediae.add(book);
		
		for (Media m: mediae) {
			System.out.println(m.toString());
		}
		
		
		 int option;
	        do {
	            showMenu();
	            option = scanner.nextInt();
	            scanner.nextLine();
	            switch (option) {
	                case 1: viewStore();
	                break;
	                case 2: Update_store();
	                break;
	                case 3: seeCurrentCart();
	                break;
	                case 0: System.out.println("Exiting");
	                break;
	                default: System.out.println("Invalid option. Try again.");
	                break;
	            }
	        } while (option != 0);	
	}
		
}          
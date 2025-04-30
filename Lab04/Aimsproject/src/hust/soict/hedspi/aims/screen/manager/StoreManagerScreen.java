package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame{
	private Store store;
	
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Option");

		// View Store
		JMenuItem viewStore = new JMenuItem("View store");
		viewStore.addActionListener(new ButtonListener());  // Thêm listener
		menu.add(viewStore);

		// Update Store
		JMenu smUpdateStore = new JMenu("Update Store");

		JMenuItem addBook = new JMenuItem("Add Book");
		addBook.addActionListener(new ButtonListener());
		smUpdateStore.add(addBook);

		JMenuItem addCD = new JMenuItem("Add CD");
		addCD.addActionListener(new ButtonListener());
		smUpdateStore.add(addCD);

		JMenuItem addDVD = new JMenuItem("Add DVD");
		addDVD.addActionListener(new ButtonListener());
		smUpdateStore.add(addDVD);

		menu.add(smUpdateStore);

		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);

		return menuBar;
	}
	
	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInstore = store.getitemsInstore();
		for (int i = 0; i < store.getitemsInstore().size(); i++) {
			MediaStore cell = new MediaStore(mediaInstore.get(i));
			center.add(cell);
		}
		
		return center;
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if (button.equals("Add Book")) {
				dispose();
				new AddBookToStoreScreen(store);
			} else if (button.equals("Add CD")) {
				dispose();
				new AddCompactDiscToStoreScreen(store);
			} else if (button.equals("Add DVD")) {
				dispose();
				new AddDigitalVideoDiscToStoreScreen(store);
			}
		}
	}
	
	
	public StoreManagerScreen(Store store) {
		this.store = store;
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setTitle("Store");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Store newStore = new Store();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		newStore.addMedia(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		newStore.addMedia(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", "Aladudu", 100, 18.99f);
		newStore.addMedia(dvd3);

		Book book1 = new Book(101, "Harry Potter", "Fantasy", new ArrayList<String>(Arrays.asList("J.K. Rowling")),
				25.99f);
		newStore.addMedia(book1);

		Book book2 = new Book(102, "Clean Code", "Programming",
				new ArrayList<String>(Arrays.asList("Robert C. Martin")), 32.50f);
		newStore.addMedia(book2);

		Book book3 = new Book(103, "The Hobbit", "Fantasy", new ArrayList<String>(Arrays.asList("J.R.R. Tolkien")),
				20.00f);
		newStore.addMedia(book3);

		CompactDisc cd1 = new CompactDisc(
				201, "Best of Pop", "Music", 19.99f,  "Various Artists", 45, "DJ Mike", new ArrayList<Track>(Arrays
						.asList(new Track("Pop Song 1", 3), new Track("Pop Song 2", 4), new Track("Pop Song 3", 5))));
		newStore.addMedia(cd1);
		
		CompactDisc cd2 = new CompactDisc(202, "Chill Beats", "Lo-fi", 15.50f, "LoFi Girl", 30, "Lofi Girl",
				new ArrayList<Track>(
						Arrays.asList(new Track("Chill 1", 2), new Track("Chill 2", 2), new Track("Chill 3", 3))));
		newStore.addMedia(cd2);

		CompactDisc cd3 = new CompactDisc(203, "Rock Anthems", "Rock", 21.99f,"Classic Rock Inc.", 50, "Rock Legends", new ArrayList<Track>(Arrays
				.asList(new Track("Thunderstruck", 5), new Track("Back in Black", 4), new Track("Highway to Hell", 4))));
		newStore.addMedia(cd3);

		StoreManagerScreen stManager = new StoreManagerScreen(newStore);
	}

	public Store getStore() {
		return store;
	}
}

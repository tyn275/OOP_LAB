package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen{

	private JTextField tfAuthors = new JTextField(10);
	private ArrayList<String> authors = new ArrayList<String>();
	
	public ArrayList<String> getAuthors() {
		return authors;
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if(button.equals("SUBMIT")) {
				Submit_Title_Category_Cost();
				String[] authorsParts = tfAuthors.getText().split(",\\s*");
				for (String author : authorsParts) {			
				    if (author.length() > 0) {
				        authors.add(author);
				    }
				}
				tfAuthors.setText("");
				Book book = new Book(getStore().getitemsInstore().size() + 1, getTitle(), getCategory(), authors, getCost());
				Store store = getStore();
				store.addMedia(book);
				StoreManagerScreen stManager = new StoreManagerScreen(store);
			}
		}
	}
	
	public AddBookToStoreScreen(Store store) {
		super(store);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS)); // or another layout
		center.add(createCenter());
		center.add(addCustomFields());
		center.add(createSubmit());
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(center, BorderLayout.CENTER);

		setTitle("Add Book");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public JButton createSubmit() {
		JButton submit = new JButton("SUBMIT");
		submit.addActionListener(new ButtonListener());
		return submit;
	}
	
	public JPanel addCustomFields() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 2, 2, 2));

		center.add(inputContainer("Enter authors(seperate by a comma): "));
		center.add(tfAuthors);
		return center;
	}
	
	

}

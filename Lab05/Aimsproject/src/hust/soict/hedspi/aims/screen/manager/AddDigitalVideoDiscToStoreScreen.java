package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen{

	private JTextField tfDirector = new JTextField(10);
	private JTextField tfLength = new JTextField(10);
	private int length;
	private String director;
	
	public int getLength() {
		return length;
	}

	public String getDirector() {
		return director;
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if(button.equals("SUBMIT")) {
				Submit_Title_Category_Cost();
				director = tfDirector.getText();
				tfDirector.setText("");
				length = Integer.parseInt(tfLength.getText());
				tfLength.setText("");
				DigitalVideoDisc dvd = new DigitalVideoDisc(getTitle(), getCategory(), director, length, getCost());
				Store store = getStore();
				store.addMedia(dvd);
				StoreManagerScreen stManager = new StoreManagerScreen(store);
			}
		}
	}
	
	public AddDigitalVideoDiscToStoreScreen(Store store) {
		super(store);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(createCenter());
		center.add(addCustomFields());
		center.add(createSubmit());
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(center, BorderLayout.CENTER);

		setTitle("Add DVD");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton createSubmit() {
		JButton submit = new JButton("SUBMIT");
		submit.addActionListener(new ButtonListener());
		return submit;
	}
	
	public JPanel addCustomFields() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 2, 2, 2));

		center.add(inputContainer("Enter director: "));
		center.add(tfDirector);
		
		center.add(inputContainer("Enter length: "));
		center.add(tfLength);
		return center;
	}
	
	
	
}

package hust.soict.hedspi.aims.screen.manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame{
	private Store store;
	private JTextField tfTitle = new JTextField(10);
	private JTextField tfCategory = new JTextField(10);
	private JTextField tfCost = new JTextField(10);
	private String title;
	private String category;
	private float cost;
	
	public JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if (button.equals("View store")) {
				dispose();
				StoreManagerScreen stManager = new StoreManagerScreen(store);
			}
		}
	}
	
	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Option");
		JMenuItem viewStore = new JMenuItem("View store");
		viewStore.addActionListener(new ButtonListener());
		menu.add(viewStore);

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
	
	public JPanel inputContainer (String input) {
		JLabel inputLabel = new JLabel(input);
		inputLabel.setFont(new Font(inputLabel.getFont().getName(), Font.PLAIN, 15));
		inputLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(Box.createVerticalGlue());
		container.add(inputLabel);
		container.add(Box.createVerticalGlue());
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		return container;
	}
	
	public void Submit_Title_Category_Cost() {
		title = tfTitle.getText();
		tfTitle.setText("");
		category = tfCategory.getText();
		tfCategory.setText("");
		cost = Float.parseFloat(tfCost.getText());
		tfCost.setText("");
	}
	
	public JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 2, 2, 2));

		center.add(inputContainer("Enter title: "));
		center.add(tfTitle);
		
		center.add(inputContainer("Enter category: "));
		center.add(tfCategory);
		
		center.add(inputContainer("Enter cost: "));
		center.add(tfCost);
		return center;
	}
	
	public abstract JButton createSubmit();

	public AddItemToStoreScreen(Store store) {
		this.store = store;
	}
	
	public float getCost() {
		return cost;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public Store getStore() {
		return store;
	}
}

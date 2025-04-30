package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen{

	
	//int id, String title, String category, float cost, String director, int length, String artist, ArrayList<Track> tracks
	private JTextField tfDirector = new JTextField(10);
	private JTextField tfLength = new JTextField(10);
	private JTextField tfArtist = new JTextField(10);
	private JTextField tfTracks = new JTextField(10);
	private int length;
	private String director;
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	
	public int getLength() {
		return length;
	}

	public String getDirector() {
		return director;
	}

	public ArrayList<Track> getTracks() {
		return tracks;
	}

	public String getArtist() {
		return artist;
	}
	
	public AddCompactDiscToStoreScreen(Store store) {
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

		setTitle("Add CD");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if(button.equals("SUBMIT")) {
				Submit_Title_Category_Cost();
				artist = tfArtist.getText();
				tfArtist.setText("");
				director = tfDirector.getText();
				tfDirector.setText("");
				length = Integer.parseInt(tfLength.getText());
				tfLength.setText("");
				String[] trackParts = tfTracks.getText().split(",\\s*");
				for (String part : trackParts) {
				    // Step 2: Split each track info by colon
				    String[] nameAndLength = part.split(":\\s*");

				    if (nameAndLength.length == 2) {
				        String trackName = nameAndLength[0]; // e.g., "track 1"
				        tracks.add(new Track(nameAndLength[0], Integer.parseInt(nameAndLength[1])));
				    }
				}
				tfTracks.setText("");
				CompactDisc cd = new CompactDisc(getStore().getitemsInstore().size() + 1, getTitle(), getCategory(), getCost(), director, length, artist, tracks);
				Store store = getStore();
				store.addMedia(cd);
				StoreManagerScreen stManager = new StoreManagerScreen(store);
			}
		}
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

		center.add(inputContainer("Enter director: "));
		center.add(tfDirector);
		
		center.add(inputContainer("Enter length: "));
		center.add(tfLength);
		
		center.add(inputContainer("Enter artist: "));
		center.add(tfArtist);
		
		center.add(inputContainer("Enter tracks(Input fomat:\"NAME : DURATION,\",seperate by a comma):"));
		center.add(tfTracks);
		return center;
	}
}

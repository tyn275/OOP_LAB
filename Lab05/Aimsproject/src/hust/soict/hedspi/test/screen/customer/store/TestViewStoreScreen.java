package hust.soict.hedspi.test.screen.customer.store;

import java.util.ArrayList;
import java.util.Arrays;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application{
	
	public static Store store;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final String STORE_FXML__FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML__FILE_PATH));
		ViewStoreController viewStoreController = new ViewStoreController(store);
		fxmlLoader.setController(viewStoreController);
		Parent root = fxmlLoader.load();
		
		primaryStage.setTitle("Store");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		store = new Store();
		
		CompactDisc cd = new CompactDisc(
				201, "Best of Pop", "Music", 19.99f,  "Various Artists", 45, "DJ Mike", new ArrayList<Track>(Arrays
						.asList(new Track("Pop Song 1", 3), new Track("Pop Song 2", 4), new Track("Pop Song 3", 5))));
		DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		Book book = new Book(101, "Harry Potter", "Fantasy", new ArrayList<String>(Arrays.asList("J.K. Rowling")),
				25.99f);
		
		store.addMedia(book);
		store.addMedia(cd);
		store.addMedia(dvd);
		
		launch(args);

	}

}

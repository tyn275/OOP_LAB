package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {
	
	private Store store;
	private Cart cart;
	
	public ViewStoreController() {
		this.store = new Store();
		this.cart = new Cart();
	}
	
	public ViewStoreController(Store store, Cart cart) {
		this.store = store;
		this.cart = cart;
	}
	    @FXML
	    private GridPane gridPane;
	    
	    @FXML
	    public void initialize() {
	    	
	    	//Test store
	    	ArrayList<String> authors = new ArrayList<>();
	    	authors.add("J.K. Rowling");
	    	authors.add("Nhi Trinh Yen");
	    	
		    store.getitemsInstore().add(new Book(1, "Harry Potter và Hòn đá Phù thủy", "Fantasy", authors, 3.0f));
	    	store.getitemsInstore().add(new Book(2, "Harry Potter và Phòng chứa Bí mật", "Fantasy", authors, 3.5f));
	    	store.getitemsInstore().add(new Book(3, "Harry Potter và Tù nhân ngục Azkaban", "Fantasy", authors, 5.0f));
	    	store.getitemsInstore().add(new Book(4, "Harry Potter và Chiếc cốc lửa", "Fantasy", authors, 4.5f));
	    	
	    	store.getitemsInstore().add(new CompactDisc(201, "WTF", "Music", 19.99f,  
	    		    "Various Artists", 45, "DJ Mike", 
	    		    new ArrayList<>(Arrays.asList(
	    		        new Track("Pop Song 1", 3), 
	    		        new Track("Pop Song 2", 4), 
	    		        new Track("Pop Song 3", 5)
	    		    ))
	    		));
	    	
	    	final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
	    	int column = 0;
	    	int row = 1;
	    	for (int i = 0; i < store.getitemsInstore().size(); i++) {
	    		try {
	    			FXMLLoader fxmlLoader = new FXMLLoader();
	    			fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH));
	    			ItemController itemController = new ItemController(cart);
	    			fxmlLoader.setController(itemController);
	    			AnchorPane anchorPane = new AnchorPane();
	    			anchorPane = fxmlLoader.load();
	    			itemController.setData(store.getitemsInstore().get(i));
	    			
	    			if (column == 3) {
	    				column = 0;
	    				row++;
	    			}
	    			
	    			gridPane.add(anchorPane, column++, row);
	    			GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
	    		} catch(IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    }

	    @FXML
	    void btnViewCartPressed(ActionEvent event) {
	    	try {
	    		final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
	    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
	    		//fxmlLoader.setController(new CartController(store, cart));
	    		Parent root = fxmlLoader.load();
	    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		stage.setScene(new Scene(root));
	    		stage.setTitle("Cart");
	    		stage.show();
	    	}catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }

	    /*@FXML
	    void 004cff(ActionEvent event) {

	    }*/  
}

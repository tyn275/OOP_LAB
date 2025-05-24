package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import java.util.ArrayList;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
	
	private Cart cart;
	private Store store;
	
	public CartController() {
		this.cart = new Cart();
	}
	
	public CartController(Store store, Cart cart) {
		this.store = store;
		this.cart = cart;
	}

    @FXML
    private TableColumn<Media, Integer> colMediaID;

    @FXML
    private Label costLabel;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Float> colMediaCost;
    
    @FXML
    private RadioButton radioBtnFilterID;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;
    
    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableColumn<Media, String> colMediaTitle;
    
    @FXML
    private TextField tfFilter;
    
    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    void btnPlayPressed(ActionEvent event) {

    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	cart.removeMedia(media);
    	updateTotalCost();
    }
    
    @FXML
    void btnPlaceOrdered(ActionEvent event) {
        // Hiển thị thông báo xác nhận thanh toán
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thanh toán thành công");
        alert.setHeaderText(null);
        alert.setContentText("Bạn đã thanh toán thành công!");
        alert.showAndWait();

        // Xóa toàn bộ sản phẩm trong giỏ hàng
        cart.clear();

        // Cập nhật giao diện giỏ hàng
        updateCartView();

    }
    
    private void updateCartView() {
        tblMedia.getItems().clear(); 
        costLabel.setText("0.00 $"); // Cập nhật tổng giá trị về 0
    }

    /*@FXML
    void 004cff(ActionEvent event) {

    }*/

    @FXML
    void btnViewStorePressed(ActionEvent event) {
    	try {
    		final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
    		//fxmlLoader.setController(new CartController(store, cart));
    		Parent root = fxmlLoader.load();
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.setTitle("Store");
    		stage.show();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private FilteredList<Media> filteredList;
    
    @FXML
    public void initialize() {
    	
    	colMediaID.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
    	colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
    	colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
    	colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
    	
    	//Test hiện thị giỏ hàng
    	ArrayList<String> authors = new ArrayList<>();
    	authors.add("J.K. Rowling");
    	authors.add("Nhi Trinh Yen");
    	
    	cart.getitemsOrdered().add(new Book(1, "Harry Potter và Hòn đá Phù thủy", "Fantasy", authors, 3.0f));
    	cart.getitemsOrdered().add(new Book(2, "Harry Potter và Phòng chứa Bí mật", "Fantasy", authors, 3.5f));
    	cart.getitemsOrdered().add(new Book(3, "Harry Potter và Tù nhân ngục Azkaban", "Fantasy", authors, 5.0f));
    	cart.getitemsOrdered().add(new Book(4, "Harry Potter và Chiếc cốc lửa", "Fantasy", authors, 4.5f));
    	
    	updateTotalCost();
    	
    	if(cart.getitemsOrdered() != null)
    		tblMedia.setItems(cart.getitemsOrdered());
    	
    	btnPlay.setVisible(false);
    	btnRemove.setVisible(false);
    	
    	tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {

			@Override
			public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
				updateButtonBar(newValue);
			}
		});
    	
        filteredList = new FilteredList<>(cart.getitemsOrdered(), media -> true);

        tblMedia.setItems(filteredList);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(newValue);
        });

    }
    
    void updateButtonBar(Media media) {
		if (media == null) {
			btnPlay.setVisible(false);
			btnRemove.setVisible(false);
		}
		else {
			btnRemove.setVisible(true);
			if (media instanceof Playable) {
				btnPlay.setVisible(true);
			}
			else {
				btnPlay.setVisible(false);
			}
		}
	}
    
    private void showFilteredMedia(String filterText) {
 
        String lowerCaseFilter = filterText.toLowerCase();

        filteredList.setPredicate(media -> {
            if (lowerCaseFilter.isEmpty()) {
                return true;
            }

            
            if (radioBtnFilterID.isSelected()) {
                return String.valueOf(media.getId()).contains(lowerCaseFilter);
            } else if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);
            }

            return true; 
        });
    }
    
    private void updateTotalCost() {
    	costLabel.setText(String.format("%.2f", cart.totalCost()));
    }
}

package View_Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML private TextField partSearchField;
    @FXML private TextField productSearchField;
    @FXML private TableView partTable;
    @FXML private TableView productTable;
    @FXML private Button partSearch;
    @FXML private Button productSearch;
    @FXML private Button partAdd;
    @FXML private Button partModify;
    @FXML private Button partDelete;
    @FXML private Button productAdd;
    @FXML private Button productModify;
    @FXML private Button productDelete;
    @FXML private Button exit;

    public void searchPartsAction() {

    }

    public  void searchProductsAction() {

    }

    public void addPartAction() {

    }

    public void modifyPartAction() {

    }

    public void deletePartAction() {

    }

    public void addProductAction() {

    }

    public void modifyProductAction() {

    }

    public void deleteProductAction() {

    }

    public void exitApplication() {
        System.exit(0);
    }

}

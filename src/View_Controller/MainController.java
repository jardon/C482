package View_Controller;

import Model.Part;
import Model.Product;
import Model.Inventory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TextField partSearchField;
    @FXML private TextField productSearchField;
    @FXML private TableView<Part> partTable;
    @FXML private TableView<Product> productTable;
    @FXML private Button partSearch;
    @FXML private Button productSearch;
    @FXML private Button partAdd;
    @FXML private Button partModify;
    @FXML private Button partDelete;
    @FXML private Button productAdd;
    @FXML private Button productModify;
    @FXML private Button productDelete;
    @FXML private Button exit;
    @FXML private TableColumn<Part, String> partId;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, String> partPrice;
    @FXML private TableColumn<Part, String> partStock;
    @FXML private TableColumn<Product, String> prodId;
    @FXML private TableColumn<Product, String> prodName;
    @FXML private TableColumn<Product, String> prodPrice;
    @FXML private TableColumn<Product, String> prodStock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        partStock.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        partTable.setItems(Inventory.getAllParts());

        prodId.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        prodName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prodPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        prodStock.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        productTable.setItems(Inventory.getAllProducts());
    }

    public void searchPartsAction(ActionEvent event) {
        String s = partSearchField.getText();

        try {
            // decide logic for string to int
            Part p = Inventory.lookupPart(Integer.parseInt(s));
            partTable.setItems(Inventory.lookupPart(s));
            // update table data
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public  void searchProductsAction(ActionEvent event) {
        String s = productSearchField.getText();

        try {
            // decide logic for string to int
            Product p = Inventory.lookupProduct(Integer.parseInt(s));
            ObservableList<Product> prodList = Inventory.lookupProduct(s);
            // updaet table data
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addPartAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PartsController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Add Part");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void modifyPartAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PartsController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Modify Part");
            stage.setScene(new Scene(loader.load()));

            PartsController controller = loader.getController();
            controller.initData(partTable.getSelectionModel().getSelectedItem());

            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletePartAction(ActionEvent event) {
        Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
    }

    public void addProductAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Add Product");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void modifyProductAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductsController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Modify Product");
            stage.setScene(new Scene(loader.load()));

            ProductsController controller = loader.getController();
            controller.initData(productTable.getSelectionModel().getSelectedItem());

            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteProductAction(ActionEvent event) {
        Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
    }

    public void exitApplication() { System.exit(0); }

}

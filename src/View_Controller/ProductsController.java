package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class ProductsController implements Initializable {

    @FXML private TextField partSearchField;
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private TextField max;
    @FXML private TextField min;
    @FXML private TableView<Part> foundParts;
    @FXML private TableView<Part> includedParts;
    @FXML private TableColumn<Part, String> foundId;
    @FXML private TableColumn<Part, String> foundName;
    @FXML private TableColumn<Part, String> foundStock;
    @FXML private TableColumn<Part, String> foundPrice;
    @FXML private TableColumn<Part, String> includedId;
    @FXML private TableColumn<Part, String> includedName;
    @FXML private TableColumn<Part, String> includedStock;
    @FXML private TableColumn<Part, String> includedPrice;
    @FXML private Button partSearch;
    @FXML private Button partAdd;
    @FXML private Button partDelete;
    @FXML private Button save;
    @FXML private Button cancel;
    @FXML private Label label;
    private Product product;
    ObservableList<Part> tempList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        foundId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        foundName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        foundStock.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        foundPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        foundParts.setItems(Inventory.getAllParts());

        includedId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        includedName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        includedStock.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        includedPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        includedParts.setItems(tempList);
    }

    public void initData(Product product) {
        this.product = product;
        id.setText(Integer.toString(product.getId()));
        name.setText(product.getName());
        price.setText(Double.toString(product.getPrice()));
        stock.setText(Integer.toString(product.getStock()));
        min.setText(Integer.toString(product.getMin()));
        max.setText(Integer.toString(product.getMax()));
        id.setDisable(true);
        label.setText("Modify Product");
        includedParts.setItems(product.getAllAssociatedParts());
    }

    public void searchPartAction() {
        
    }

    public void addPartAction(ActionEvent event) {
        if(product != null)
            product.addAssociatedPart(foundParts.getSelectionModel().getSelectedItem());
        else
            tempList.add(foundParts.getSelectionModel().getSelectedItem());
    }

    public void deletePartAction(ActionEvent event) {
        if(product != null)
            product.deleteAssociatedPart(includedParts.getSelectionModel().getSelectedItem());
        else
            tempList.remove(includedParts.getSelectionModel().getSelectedItem());
    }

    public void saveAction(ActionEvent event) {
        if(product == null) {
            product = new Product(
                    Integer.parseInt(id.getText()),
                    name.getText(),
                    Double.parseDouble(price.getText()),
                    Integer.parseInt(stock.getText()),
                    Integer.parseInt(min.getText()),
                    Integer.parseInt(max.getText()));

            for (Part part : tempList)
                product.addAssociatedPart(part);

            Inventory.addProduct(product);
        }

        else {
            product.setName(name.getText());
            product.setPrice(Double.parseDouble(price.getText()));
            product.setStock(Integer.parseInt(stock.getText()));
            product.setMin(Integer.parseInt(min.getText()));
            product.setMax(Integer.parseInt(max.getText()));
        }

        cancelAction(event);
    }

    public void cancelAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainController.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

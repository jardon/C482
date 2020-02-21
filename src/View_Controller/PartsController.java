package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class PartsController implements Initializable {

    @FXML private Button save;
    @FXML private Button cancel;
    @FXML private RadioButton inHouse;
    @FXML private RadioButton outsourced;
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private TextField max;
    @FXML private TextField min;
    @FXML private TextField secondary;
    @FXML private Label label;
    @FXML private Label secondaryLabel;
    private ToggleGroup radioGroup;
    private Part part;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioGroup = new ToggleGroup();
        inHouse.setToggleGroup(radioGroup);
        outsourced.setToggleGroup(radioGroup);
        secondaryLabel.setText("Disabled");
        secondary.setDisable(true);
        inHouse.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected)
                    secondaryLabel.setText("MachineID");
                else
                    secondaryLabel.setText("Company Name");
                secondary.setDisable(false);
            }
        });

        outsourced.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected)
                    secondaryLabel.setText("Company Name");
                else
                    secondaryLabel.setText("MachineID");
                secondary.setDisable(false);
            }
        });
    }

    public void initData(Part part) {
        this.part = part;
        id.setText(Integer.toString(part.getId()));
        name.setText(part.getName());
        stock.setText(Integer.toString(part.getStock()));
        price.setText(Double.toString(part.getPrice()));
        min.setText(Integer.toString(part.getMin()));
        max.setText(Integer.toString(part.getMax()));
        label.setText("Modify Part");
        id.setDisable(true);

        if(part.getClass() == InHouse.class) {
            inHouse.setSelected(true);
            outsourced.setDisable(true);
            secondaryLabel.setText("MachineID");
            secondary.setText(Integer.toString(((InHouse) part).getMachineId()));
        }

        if(part.getClass() == Outsourced.class) {
            outsourced.setSelected(true);
            inHouse.setDisable(true);
            secondaryLabel.setText("Company Name");
            secondary.setText(((Outsourced) part).getCompanyName());
        }
    }

    public void savePartAction(ActionEvent event) {
        try {
            if(part == null) {
                if (radioGroup.getSelectedToggle().equals(inHouse))
                    Inventory.addPart(new InHouse(
                            Integer.parseInt(id.getText()),
                            name.getText(),
                            Double.parseDouble(price.getText()),
                            Integer.parseInt(stock.getText()),
                            Integer.parseInt(min.getText()),
                            Integer.parseInt(max.getText()),
                            Integer.parseInt(secondary.getText())));

                else if (radioGroup.getSelectedToggle().equals(outsourced))
                    Inventory.addPart(new Outsourced(
                            Integer.parseInt(id.getText()),
                            name.getText(),
                            Double.parseDouble(price.getText()),
                            Integer.parseInt(stock.getText()),
                            Integer.parseInt(min.getText()),
                            Integer.parseInt(max.getText()),
                            secondary.getText()));
                else
                    System.out.println("No radio button selected.");
            }
            else {
                part.setName(name.getText());
                part.setPrice(Double.parseDouble(price.getText()));
                part.setStock(Integer.parseInt(stock.getText()));
                part.setMin(Integer.parseInt(min.getText()));
                part.setMax(Integer.parseInt(max.getText()));

                if(part.getClass() == InHouse.class)
                    ((InHouse) part).setMachineId(Integer.parseInt(secondary.getText()));
                else if(part.getClass() == Outsourced.class)
                    ((Outsourced) part).setCompanyName(secondary.getText());
                else
                    System.out.println("Unable to determine Part subclass.");
            }
        }
            catch (Exception e) {
                System.out.println(e);
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

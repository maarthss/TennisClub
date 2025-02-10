package Controller.FXMLControllers.Fields;

import Model.Fields;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {
    

    @FXML
    private ChoiceBox cb1;
    
    @FXML
    private TextField nameFields;
    
    @FXML
    private ChoiceBox statusFields;
    
    @FXML 
    private Spinner priceFields;
    
    @FXML
    private Button btFields;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> statusValues = FXCollections.observableArrayList("Free", "Reserved");
        statusFields.setItems(statusValues);
        
        String name = nameFields.getText();
        String status = (String) statusFields.getValue();
        Double price = (Double) priceFields.getValue();
        
        //f1.insertField(name, price, status);
    }    
    
}

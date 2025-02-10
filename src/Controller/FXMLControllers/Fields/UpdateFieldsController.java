/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Fields;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class UpdateFieldsController implements Initializable {
    
    @FXML
    private TextField tfName;
    
    @FXML
    private Spinner spPrice;
    
    @FXML
    private Button btUpdate;
    
    @FXML
    private ChoiceBox cbStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String name = tfName.getText();
        Double price = (Double) spPrice.getValue();
        String status = (String) cbStatus.getValue();
        
    }    
    
}

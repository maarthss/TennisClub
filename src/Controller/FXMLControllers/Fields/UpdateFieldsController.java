/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Fields;

import Model.Fields;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateFieldsController implements Initializable {
    
    @FXML
    private TextField tfName;
    
    @FXML
    private Spinner spPrice;
    
    @FXML
    private Button btUpdate;
    
    @FXML
    private ChoiceBox cbStatus;
    
    @FXML 
    private Label lbID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> statusValues = FXCollections.observableArrayList("Free", "Reserved");
        cbStatus.setItems(statusValues);

        
        String name = tfName.getText();
        Double price = (Double) spPrice.getValue();
        String status = (String) cbStatus.getValue();        
        
    }    
    
    @FXML
    public void getData(Fields field){
        
        lbID.setText(Integer.toString(field.getId()));
        tfName.setText(field.getName());
        
        spPrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(10.0, 25.0, 0.0, 0.2));
        spPrice.getValueFactory().setValue(field.getPrice());
        
        cbStatus.setValue(field.getStatus());
        
    }
    
    
    @FXML
    public void setNewData(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Confirmation");
        alert.setHeaderText("You're about to update a field");
        alert.setContentText("Are you sure about that?");
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            int id = Integer.parseInt(lbID.getText());
            String valueName = tfName.getText();
            String valueStatus = (String) cbStatus.getValue();
            String valuePrice = Double.toString((double) spPrice.getValueFactory().getValue());
        
            Fields f = new Fields();
            f.updateFields("name", valueName, id);
            f.updateFields("price", valuePrice, id);
            f.updateFields("status", valueStatus, id);
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fields/General/RUD_fields.fxml"));
                Parent root = loader.load();
                
                RUD_fieldsController controller = loader.getController();
                
                controller.refreshTable();
                
                Stage stage = (Stage)btUpdate.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateFieldsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }

    }
    
}

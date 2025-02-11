/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Matches;

import Controller.FXMLControllers.Fields.RUD_fieldsController;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Model.Matches;
import Model.Members;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class UpdateMatchesController implements Initializable {

    @FXML
    private Button btUpdate;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label lbID;

    @FXML
    private TextArea taResult;

    @FXML
    private TextField tfField;

    @FXML
    private TextField tfMember1;

    @FXML
    private TextField tfMember2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    public void getData(Matches match){
        
        lbID.setText(Integer.toString(match.getId()));
        dpDate.setValue(match.getDate());
        taResult.setText(match.getResult());
        tfField.setText(String.valueOf(match.getId_field()));
        tfMember1.setText(String.valueOf(match.getId_member1()));
        tfMember2.setText(String.valueOf(match.getId_member2()));
        
    }
    
    
    @FXML
    public void setNewData(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Confirmation");
        alert.setHeaderText("You're about to update a match");
        alert.setContentText("Are you sure about that?");
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            int id = Integer.parseInt(lbID.getText());
            
            String valueDate = dpDate.getValue().toString();
            String valueResult = taResult.getText();
            String valueMember1 = tfMember1.getText();
            String valueMember2 = tfMember2.getText();
            String valueField = tfField.getText();
            
            Matches m = new Matches();
            m.updateMatch("date", valueDate, id);
            m.updateMatch("result", valueResult, id);
            m.updateMatch("id_member1", valueMember1, id);
            m.updateMatch("id_member2", valueMember2, id);
            m.updateMatch("id_field", valueField, id);
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Matches/General/GeneralMatches.fxml"));
                Parent root = loader.load();
                
                GeneralMatchesController controller = loader.getController();
                
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

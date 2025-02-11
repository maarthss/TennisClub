/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Members;

import Controller.CRUD.Insert;
import Controller.FXMLControllers.Fields.RUD_fieldsController;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Model.Fields;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;
import java.time.LocalDate;


public class InsertMembersController implements Initializable {


    @FXML
    private TextField tfDNI;
    
    @FXML
    private TextField tfName;
    
    @FXML
    private TextField tfSurname;
    
    @FXML
    private TextField tfPhone;
    
    @FXML
    private DatePicker dpMembership;
    
    @FXML
    private DatePicker dpBirth;
    
    @FXML
    private Button btInsert;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
    @FXML
    public void insertData(){
        
        Insert i = new Insert();
        
        String dni = tfDNI.getText();
        String name = tfName.getText();
        String surname = tfSurname.getText();
        LocalDate membership = dpMembership.getValue();
        LocalDate birth = dpBirth.getValue();
        String phoneNumber = tfPhone.getText();

        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Insert Confirmation");
        alert.setHeaderText("You're about to add a member");
        alert.setContentText("Are you sure about that?");
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            

            Members m = new Members();
            m.insertMember(dni, name, surname, membership, birth, phoneNumber);
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Members/General/GeneralMembers.fxml"));
                Parent root = loader.load();
                
                GeneralMembersController controller = loader.getController();
                
                controller.refreshTable();
                
                Stage stage = (Stage)btInsert.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateFieldsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

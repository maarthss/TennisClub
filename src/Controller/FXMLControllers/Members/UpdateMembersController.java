/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Members;

import Controller.FXMLControllers.Fields.RUD_fieldsController;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Model.Fields;
import Model.Members;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;


public class UpdateMembersController implements Initializable {
    
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
    private Button btUpdate;
    
    @FXML
    private Label lbID;
    
    @FXML
    private Button btMembers;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tfDNI.setEditable(false);
        tfDNI.setMouseTransparent(true);
        
        URL home = getClass().getResource("/Resources/casa.png");
        Image imgHome = new Image(home.toString(), 24, 24, false, true);
        btMembers.setGraphic((new ImageView(imgHome)));
        
        LocalDate actualDate = LocalDate.now(); //Local date que servirà tant pel datePicker de membership com pel de birth
        
        //Feim que la data de l'inici de la subcripció no pugui ser anterior a l'actual
        dpMembership.setDayCellFactory(new Callback<DatePicker, DateCell>(){
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item, empty);
                        
                        if(item.isBefore(actualDate)){
                            setDisable(true);
                        }
                    }
                };
            }
            
        });
        
        
        //Feim que la data de naixement no pugui ser superior a la del dia actual
        dpBirth.setDayCellFactory(new Callback<DatePicker, DateCell>(){
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item, empty);
                        
                        if(item.isAfter(actualDate)){
                            setDisable(true);
                        }
                    }
                };
            }
            
        });
 
    }    
    
    @FXML
    public void getData(Members member){
        
        lbID.setText(Integer.toString(member.getId()));
        tfDNI.setText(member.getDni());
        tfName.setText(member.getName());
        tfSurname.setText(member.getSurname());
        dpMembership.setValue(member.getMembership_start());
        dpBirth.setValue(member.getDate_of_birth());
        tfPhone.setText(member.getPhone_number());
    }
    
    @FXML
    public void setNewData(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Confirmation");
        alert.setHeaderText("You're about to update a member");
        alert.setContentText("Are you sure about that?");
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            int id = Integer.parseInt(lbID.getText());
            
            String valueDni = tfDNI.getText();
            String valueName = tfName.getText();
            String valueSurname = tfSurname.getText();
            String valueMembership = dpMembership.getValue().toString();
            String valueBirth = dpBirth.getValue().toString();
            String valuePhone = tfPhone.getText();
            
            if(valueDni.isEmpty() || valueName.isEmpty() || valueSurname.isEmpty() ||  valueMembership == null || valueBirth == null || valuePhone.isEmpty()){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Insert error");
            error.setHeaderText("Field could not be added");
            error.setContentText("All fields must be filled");
            error.showAndWait();
            return;
        }
            
            Members m = new Members();
            m.updateMember("dni", valueDni, id);
            m.updateMember("name", valueName, id);
            m.updateMember("surname", valueSurname, id);
            m.updateMember("membership_start", valueMembership, id);
            m.updateMember("date_of_birth", valueBirth, id);
            m.updateMember("phone_number", valuePhone, id);
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Members/General/GeneralMembers.fxml"));
                Parent root = loader.load();
                
                GeneralMembersController controller = loader.getController();
                
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
    
    @FXML
    private void goToMembers(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Members/General/GeneralMembers.fxml"));
            Parent root = loader.load();
            GeneralMembersController controller = loader.getController();
            
            Stage stage = (Stage)btMembers.getScene().getWindow();
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

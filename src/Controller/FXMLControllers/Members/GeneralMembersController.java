/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Members;

import Controller.FXMLControllers.Fields.FXMLDocumentController;
import Controller.FXMLControllers.Fields.RUD_fieldsController;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Controller.FXMLControllers.MenuController;
import Model.Fields;
import Model.Members;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;



public class GeneralMembersController implements Initializable {
    
    @FXML
    private TextField tfMembers;
    
    @FXML
    private Spinner spMembers;
    
    @FXML
    private ChoiceBox cbMembers;
    
    @FXML
    private TableView<Members> tableMembers;
    
    @FXML
    private TableColumn columnID;
    
    @FXML
    private TableColumn columnDNI;
    
    @FXML
    private TableColumn columnName;
    
    @FXML
    private TableColumn columnSurname;
    
    @FXML
    private TableColumn columnMembership;
    
    @FXML
    private TableColumn columnBirth;
    
    @FXML
    private TableColumn columnPhone;
    
    @FXML
    private Button btUpdate;
    
    @FXML
    private Button btDelete;
    
    @FXML
    private Button btInsert;
    
    @FXML
    private Button btHome;
    
    private ObservableList<Members> filteredList;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        filteredList = FXCollections.observableArrayList();
        
        Members m = new Members();
        columnID.setCellValueFactory(new PropertyValueFactory("id"));
        columnDNI.setCellValueFactory(new PropertyValueFactory("dni"));
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory("surname"));
        columnMembership.setCellValueFactory(new PropertyValueFactory("membership_start"));
        columnBirth.setCellValueFactory(new PropertyValueFactory("date_of_birth"));
        columnPhone.setCellValueFactory(new PropertyValueFactory("phone_number"));
        
        tableMembers.setItems(m.selectMembers());
        
        
        URL home = getClass().getResource("/Resources/casa.png");
        Image imgHome = new Image(home.toString(), 24, 24, false, true);
        btHome.setGraphic((new ImageView(imgHome)));
                

        
    }    
    
    @FXML
    public void deleteSelectedMember(){
        
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("You're about to delete a member");
            alert.setContentText("Are you sure about that?");
        
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Members m = new Members();
                Members selectedMember = tableMembers.getSelectionModel().getSelectedItem();
                if(selectedMember != null){
                    int selectedId = selectedMember.getId();
                    selectedMember.deleteMember(selectedId);
                    m.selectMembers().remove(selectedMember);
                    refreshTable();
                }else{
                    throw new Exception();
                }
            }
        }catch(Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Warning");
            error.setHeaderText("Can't delete the member");
            error.setContentText("Select a member to delete");
            error.showAndWait();
        }
    }
    
    
    @FXML
    public void refreshTable(){
        Members m = new Members();
        tableMembers.setItems(m.selectMembers());
    }
    
    
    @FXML
    public Members updateSelectedMember(){   
        
        Members m = null;
        
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Confirmation");
            alert.setHeaderText("You're about to update a member");
            alert.setContentText("Are you sure about that?");
        
        
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Members selectedMember = tableMembers.getSelectionModel().getSelectedItem();
        
                if(selectedMember != null){
                    int id = selectedMember.getId();
                    String dni = selectedMember.getDni();
                    String name = selectedMember.getName();
                    String surname = selectedMember.getSurname();
                    LocalDate membership = selectedMember.getMembership_start();
                    LocalDate birth = selectedMember.getDate_of_birth();
                    String phoneNumber = selectedMember.getPhone_number();

                    m = new Members(id, dni, name, surname, membership, birth, phoneNumber);
                }else{
                    throw new Exception();
                }
        
                try {
            
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Members/Update/UpdateMembers.fxml"));
                    Parent root = loader.load();
                    UpdateMembersController ufController = loader.getController();
                    ufController.getData(m);
            
                    Stage stage = (Stage)tableMembers.getScene().getWindow();
            
                    Scene newScene = new Scene(root);
                    stage.setScene(newScene);
                    stage.show();
            
                } catch (IOException ex) {
                    Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        }catch(Exception e){
            
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Warning");
            error.setHeaderText("Can't update the member");
            error.setContentText("Select a member to update");
            error.showAndWait();
        }
        
        return m;
    }
    
    
    @FXML
    private void goToInsert(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Members/Insert/InsertMembers.fxml"));
            Parent root = loader.load();
            InsertMembersController controller = loader.getController();
            
            Stage stage = (Stage)btInsert.getScene().getWindow();
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    @FXML
    private void filterByName(){
        
        Members m = new Members();
        String dniFilter = tfMembers.getText();
        
        if(dniFilter.isEmpty()){
            tableMembers.setItems(m.selectMembers());
        }else{
            filteredList.clear();
            for(Members member : m.selectMembers()){
                if(member.getName().toLowerCase().contains(dniFilter.toLowerCase())){
                    filteredList.add(member);
                }
            }
            tableMembers.setItems(filteredList);
        }
    }
    
    
    @FXML
    private void goBackMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Menu.fxml"));
            Parent root = loader.load();
                
            MenuController controller = loader.getController();
                                
            Stage stage = (Stage)btHome.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateFieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

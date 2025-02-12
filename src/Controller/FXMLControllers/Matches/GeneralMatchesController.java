/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.FXMLControllers.Matches;

import Controller.FXMLControllers.Fields.RUD_fieldsController;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Controller.FXMLControllers.Members.InsertMembersController;
import Controller.FXMLControllers.Members.UpdateMembersController;
import Controller.FXMLControllers.MenuController;
import Model.Matches;
import Model.Members;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GeneralMatchesController implements Initializable {

    @FXML
    private Button btDelete;

    @FXML
    private Button btInsert;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn columnDate;

    @FXML
    private TableColumn columnField;

    @FXML
    private TableColumn columnID;

    @FXML
    private TableColumn columnMember1;

    @FXML
    private TableColumn columnMember2;

    @FXML
    private TableColumn columnResult;

    @FXML
    private TableView<Matches> tableMatches;

    @FXML
    private TextField tfMatches;
    
    @FXML
    private Button btHome;
    
    private ObservableList<Matches> filteredList;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        filteredList = FXCollections.observableArrayList();
        
        Matches m = new Matches();
        columnID.setCellValueFactory(new PropertyValueFactory("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory("date"));
        columnResult.setCellValueFactory(new PropertyValueFactory("result"));
        columnMember1.setCellValueFactory(new PropertyValueFactory("id_member1"));
        columnMember2.setCellValueFactory(new PropertyValueFactory("id_member2"));
        columnField.setCellValueFactory(new PropertyValueFactory("id_field"));
        
        tableMatches.setItems(m.selectMatches());
        
        URL home = getClass().getResource("/Resources/casa.png");
        Image imgHome = new Image(home.toString(), 24, 24, false, true);
        btHome.setGraphic((new ImageView(imgHome)));
        
    }    
    
    @FXML
    public void deleteSelectedMatch(){
        
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("You're about to delete a match");
            alert.setContentText("Are you sure about that?");
        
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Matches m = new Matches();
                Matches selectedMatch = tableMatches.getSelectionModel().getSelectedItem();
                if(selectedMatch != null){
                    int selectedId = selectedMatch.getId();
                    selectedMatch.deleteMatch(selectedId);
                    m.selectMatches().remove(selectedMatch);
                    refreshTable();
                }else{
                    throw new Exception();
                }
            }
        }catch(Exception e){
            
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Warning");
            error.setHeaderText("Can't delete the match");
            error.setContentText("Select a match to delete");
            error.showAndWait();
            
        }
        
    }
    
    @FXML
    public void refreshTable(){
        Matches m = new Matches();
        tableMatches.setItems(m.selectMatches());
    }
    
    
    @FXML
    public Matches updateSelectedMatch(){
        Matches m = null;
        
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Confirmation");
            alert.setHeaderText("You're about to update a match");
            alert.setContentText("Are you sure about that?");
        
        
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Matches selectedMatch = tableMatches.getSelectionModel().getSelectedItem();
        
                if(selectedMatch != null){
                    int id = selectedMatch.getId();
                    LocalDate matchDate= selectedMatch.getDate();
                    String matchResult = selectedMatch.getResult();
                    int member1 = selectedMatch.getId_member1();
                    int member2 = selectedMatch.getId_member2();
                    int field = selectedMatch.getId_field();

                    m = new Matches(id, matchDate, matchResult, member1, member2, field);
                }else{
                    throw new Exception();
                }
        
                try {
            
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Matches/Update/UpdateMatches.fxml"));
                    Parent root = loader.load();
                    UpdateMatchesController ufController = loader.getController();
                    ufController.getData(m);
            
                    Stage stage = (Stage)tableMatches.getScene().getWindow();
            
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
            error.setHeaderText("Can't update the match");
            error.setContentText("Select a match to update");
            error.showAndWait();
        }
        
        
        return m;
    }
    
    
    @FXML
    private void goToInsert(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Matches/Insert/InsertMatches.fxml"));
            Parent root = loader.load();
            InsertMatchesController controller = loader.getController();
            
            Stage stage = (Stage)btInsert.getScene().getWindow();
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void filterByID(){
        
        Matches m = new Matches();
        String idFilter = tfMatches.getText();
        
        if(idFilter.isEmpty()){
            tableMatches.setItems(m.selectMatches());
        }else{
            filteredList.clear();
            for(Matches match : m.selectMatches()){
                if(String.valueOf(match.getId()).contains(idFilter)){
                    filteredList.add(match);
                } else {
                }
            }
            tableMatches.setItems(filteredList);
        }
    }
    
    @FXML
    private void goBackHome(){
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

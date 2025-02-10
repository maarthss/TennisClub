package Controller.FXMLControllers.Fields;

import Model.Fields;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


public class RUD_fieldsController implements Initializable {

    @FXML
    private TextField nameFields;
    
    @FXML
    private Spinner priceFields;
    
    @FXML
    private ChoiceBox statusFields;
    
    @FXML
    private TableColumn columnID;
    
    @FXML
    private TableColumn columnName;
    
    @FXML
    private TableColumn columnPrice;
    
    @FXML
    private TableColumn columnStatus;
    
    @FXML
    private TableView<Fields> tableFields;
    
    @FXML
    private Button btUpdate;
    
    @FXML
    private Button btDelete;
    
    @FXML 
    private Button btInsert;
    
    private ObservableList<Fields> filteredList;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        filteredList = FXCollections.observableArrayList();
        
        Fields f = new Fields();
        columnID.setCellValueFactory(new PropertyValueFactory("id"));
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory("price"));
        columnStatus.setCellValueFactory(new PropertyValueFactory("status"));
        
        tableFields.setItems(f.getFields());
        
    }    
    
    @FXML
    public void deleteSelectedField(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("You're about to delete a field");
        alert.setContentText("Are you sure about that?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Fields f = new Fields();
            Fields selectedField = tableFields.getSelectionModel().getSelectedItem();
            if(selectedField != null){
                int selectedId = selectedField.getId();
                selectedField.deleteFields(selectedId);
                f.getFields().remove(selectedField);
                refreshTable();
            }else{
                alert.close();
            }
        }
    }
    
    @FXML
    public void refreshTable(){
        Fields f = new Fields();
        tableFields.setItems(f.getFields());
    }
    
    @FXML
    public Fields updateSelectedField(){   
        
        Fields f = null;
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Update Confirmation");
        alert.setHeaderText("You're about to update a field");
        alert.setContentText("Are you sure about that?");
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Fields selectedField = tableFields.getSelectionModel().getSelectedItem();
        
            if(selectedField != null){
                int id = selectedField.getId();
                String name = selectedField.getName();
                Double price = selectedField.getPrice();
                String status = selectedField.getStatus();
            
                f = new Fields(id, name, price, status);
            }
        
            try {
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fields/Update/UpdateFields.fxml"));
                Parent root = loader.load();
                UpdateFieldsController ufController = loader.getController();
                ufController.getData(f);
            
                Stage stage = (Stage)tableFields.getScene().getWindow();
            
                Scene newScene = new Scene(root);
                stage.setScene(newScene);
                stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return f;
    }
    
    @FXML
    private void goToInsert(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fields/Insert/FXMLDocument.fxml"));
            Parent root = loader.load();
            FXMLDocumentController controller = loader.getController();
            
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
        
        Fields f = new Fields();
        String nameFilter = nameFields.getText();
        
        if(nameFilter.isEmpty()){
            tableFields.setItems(f.getFields());
        }else{
            filteredList.clear();
            for(Fields field : f.getFields()){
                if(field.getName().toLowerCase().contains(nameFilter.toLowerCase())){
                    filteredList.add(field);
                }
            }
            tableFields.setItems(filteredList);
        }
    }
    
    
    
}
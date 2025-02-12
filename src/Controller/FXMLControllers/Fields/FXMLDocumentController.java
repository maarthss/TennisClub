package Controller.FXMLControllers.Fields;

import Controller.CRUD.Insert;
import Controller.FXMLControllers.MenuController;
import Model.Fields;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField nameFields;
    
    @FXML
    private ChoiceBox statusFields;
    
    @FXML 
    private Spinner priceFields;
    
    @FXML
    private Button btFields;
    
    @FXML
    private Button btBackToFields;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<String> statusValues = FXCollections.observableArrayList("Free", "Reserved");
        statusFields.setItems(statusValues);
        
        priceFields.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(10.0, 25.0, 0.0, 0.2)); 
        
        URL home = getClass().getResource("/Resources/casa.png");
        Image imgHome = new Image(home.toString(), 24, 24, false, true);
        btBackToFields.setGraphic((new ImageView(imgHome)));
        
    }    
    
    @FXML
    public void insertData(){
        
        Insert i = new Insert();
        
        String name = nameFields.getText();
        String status = (String) statusFields.getValue();
        Double price = (Double) priceFields.getValueFactory().getValue();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Insert Confirmation");
        alert.setHeaderText("You're about to add a field");
        alert.setContentText("Are you sure about that?");
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            

            Fields f = new Fields();
            f.insertField(name, price, status);
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fields/General/RUD_fields.fxml"));
                Parent root = loader.load();
                
                RUD_fieldsController controller = loader.getController();
                
                controller.refreshTable();
                
                Stage stage = (Stage)btFields.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateFieldsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void goBackFields(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Fields/General/RUD_fields.fxml"));
            Parent root = loader.load();
            RUD_fieldsController controller = loader.getController();
            
            Stage stage = (Stage)btBackToFields.getScene().getWindow();
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

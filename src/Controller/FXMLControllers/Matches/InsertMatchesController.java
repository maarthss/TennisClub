
package Controller.FXMLControllers.Matches;

import Controller.CRUD.Insert;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Controller.FXMLControllers.Members.GeneralMembersController;
import Model.Matches;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertMatchesController implements Initializable {

     @FXML
    private Button btInsert;

    @FXML
    private DatePicker dpDate;

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
    public void insertData(){
        
        Insert i = new Insert();
        
        LocalDate date = dpDate.getValue();
        String matchResult = taResult.getText();
        int member1 = Integer.parseInt(tfMember1.getText());
        int member2 = Integer.parseInt(tfMember2.getText());
        int field = Integer.parseInt(tfField.getText());
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Insert Confirmation");
        alert.setHeaderText("You're about to add a member");
        alert.setContentText("Are you sure about that?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            
            Matches m = new Matches();
            m.insertMatch(date, matchResult, member1, member2, field);
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Matches/General/GeneralMatches.fxml"));
                Parent root = loader.load();
                
                GeneralMatchesController controller = loader.getController();
                
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

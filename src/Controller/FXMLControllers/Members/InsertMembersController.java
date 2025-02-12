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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.w3c.dom.Document;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




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
    
    @FXML
    private Button btMembers;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        if(dni.isEmpty() || name.isEmpty() || surname.isEmpty() || membership == null || birth == null || phoneNumber.isEmpty()){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Insert error");
            error.setHeaderText("Field could not be added");
            error.setContentText("All fields must be filled");
            error.showAndWait();
            return;
        }
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            
            try{
            
            if(!validationDNI(dni)){
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
            }else{
                throw new Exception();
            }
        
        }catch(Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Insert error");
            error.setHeaderText("Member could not be added");
            error.setContentText("This dni is already on the database");
            error.showAndWait();
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
    
    
    private boolean validationDNI(String dni){
        
        boolean dniExists = false;
        java.sql.Connection conn = null;
        
        String query = "SELECT * FROM members WHERE DNI = ?";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, dni);
            
            try (ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    dniExists = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertMembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return dniExists;
    }
}

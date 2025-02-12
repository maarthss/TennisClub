
package Controller.FXMLControllers.Matches;

import Controller.CRUD.Insert;
import Controller.FXMLControllers.Fields.RUD_fieldsController;
import Controller.FXMLControllers.Fields.UpdateFieldsController;
import Controller.FXMLControllers.Members.GeneralMembersController;
import Controller.FXMLControllers.Members.InsertMembersController;
import Model.Matches;
import Model.Members;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class InsertMatchesController implements Initializable {

     @FXML
    private Button btInsert;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfField;

    @FXML
    private TextField tfMember1;

    @FXML
    private TextField tfMember2;
    
    @FXML
    private Button btMatches;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        URL home = getClass().getResource("/Resources/casa.png");
        Image imgHome = new Image(home.toString(), 24, 24, false, true);
        btMatches.setGraphic((new ImageView(imgHome)));
        
        //Feim que la data del partit no pugui ser anterior a la del dia actual
        LocalDate actualDate = LocalDate.now();
        dpDate.setDayCellFactory(new Callback<DatePicker, DateCell>(){
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
    }    
    
    @FXML
    public void insertData() {
        try {
            Insert i = new Insert();

            LocalDate date = dpDate.getValue();
            int member1 = Integer.parseInt(tfMember1.getText());
            int member2 = Integer.parseInt(tfMember2.getText());
            int field = Integer.parseInt(tfField.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insert Confirmation");
            alert.setHeaderText("You're about to add a match");
            alert.setContentText("Are you sure about that?");

            if (date == null || tfMember1.getText().isEmpty() || tfMember2.getText().isEmpty() || tfField.getText().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.WARNING);
                error.setTitle("Insert error");
                error.setHeaderText("Match could not be added");
                error.setContentText("All fields must be filled");
                error.showAndWait();
                return;
            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                
                if (validationMembers(member1) && validationMembers(member2) && validationField(field)) {

                    // Verificar que los jugadores no son el mismo
                    if (member1 != member2) {
                        Matches m = new Matches();
                        m.insertMatch(date, null, member1, member2, field);

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Matches/General/GeneralMatches.fxml"));
                            Parent root = loader.load();

                            GeneralMatchesController controller = loader.getController();

                            controller.refreshTable();

                            Stage stage = (Stage) btInsert.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(UpdateFieldsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        throw new Exception("A player can't play against itself");
                    }
                } else {
                    // Mostrar error si alguno de los jugadores no existe
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Insert error");
                    error.setHeaderText("Match could not be added");
                    error.setContentText("One or both players or the field do not exist in the database");
                    error.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Insert error");
            error.setHeaderText("Can't add the match");
            error.setContentText(e.getMessage()); // Mostrar el mensaje de la excepción
            error.showAndWait();
        }
    }

    @FXML
    private void goToMatches(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Matches/General/GeneralMatches.fxml"));
            Parent root = loader.load();
            GeneralMatchesController controller = loader.getController();
            
            Stage stage = (Stage)btMatches.getScene().getWindow();
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean validationMembers(int id){
        
        boolean memberExists = false;
        java.sql.Connection conn = null;
        
        String query = "SELECT * FROM members WHERE ID = ?";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            
            try (ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    memberExists = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertMembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return memberExists;
    }
    
    private boolean validationField(int id){
        
        boolean fieldExists = false;
        java.sql.Connection conn = null;
        
        String query = "SELECT * FROM fields WHERE ID = ?";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            
            try (ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    fieldExists = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertMembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return fieldExists;
    }
}



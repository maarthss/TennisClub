package Controller.FXMLControllers.Fields;

import Model.Fields;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Fields f = new Fields();
        columnID.setCellValueFactory(new PropertyValueFactory("id"));
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory("price"));
        columnStatus.setCellValueFactory(new PropertyValueFactory("status"));
        
        tableFields.setItems(f.getFields());
        
        
        //Falta posar s'update: Dins aquest controller hi ha d'haver s'event, s'ha de recollir la informació de la columna seleccionada i passar a la següent pantalla    
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
            }else{
                alert.close();
            }
        }
    }
    
    
    @FXML
    public Fields updateSelectedField(){
        
        Fields f = null;
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
            
            
        } catch (IOException ex) {
            Logger.getLogger(RUD_fieldsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
}



/*quiero usar el fxml loader para pasarle los datos. Para lo que lo voy a usar es para, desde el controlador 1, recoger unos datos(que son un objeto de una clase que tengo yo) y pasarselos al siguiente controlador y que me los cargue en la interfaz*/


/*public class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}
*/


/*import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Controlador1 {

    private Stage stage;
    private Persona persona;

    public Controlador1() {
        // Aquí inicializas la persona, por ejemplo:
        persona = new Persona("Juan", 25);
    }

    @FXML
    public void irAControlador2() throws IOException {
        // Cargar el FXML de Controlador2
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controlador2.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la vista cargada
        Controlador2 controlador2 = loader.getController();
        
        // Pasar la Persona al Controlador2
        controlador2.setPersona(persona);

        // Mostrar la nueva escena con el controlador 2
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
*/

/*import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controlador2 {

    @FXML
    private Label nombreLabel;

    @FXML
    private Label edadLabel;

    private Persona persona;

    // Método para recibir el objeto Persona
    public void setPersona(Persona persona) {
        this.persona = persona;
        actualizarInterfaz();
    }

    // Método para actualizar los datos en la interfaz
    private void actualizarInterfaz() {
        if (persona != null) {
            nombreLabel.setText(persona.getNombre());
            edadLabel.setText(String.valueOf(persona.getEdad()));
        }
    }
}
*/
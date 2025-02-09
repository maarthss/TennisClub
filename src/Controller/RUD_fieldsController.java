package Controller;

import Model.Fields;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    }    
    
}

package Controller;

import Model.Fields;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class FXMLDocumentController implements Initializable {
    

    @FXML
    private ChoiceBox cb1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Fields f1 = new Fields();
        cb1.setItems(f1.getFields());
    }    
    
}

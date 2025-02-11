package Controller.CRUD;

import Controller.Connection;
import Model.Fields;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class Insert implements Connection {
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static String query;


    @Override
    public void connect(String table, List<String> columns) {  //Values és numèric perquè es refereix a la QUANTITAT de valors que volem insertar
        
        String columnNames = "";
        String questionMark = "";
        
        try {
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            
            if (columns == null || columns.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insert field");
                alert.setHeaderText("Can't add field");
                alert.setContentText("All columns must be filled");
        
                
        Optional<ButtonType> result = alert.showAndWait();
            }
            
            for(int i = 0; i < columns.size(); i++){
                if( i > 0){
                   columnNames += ", ";
                   questionMark += ", ";
                }
                columnNames += columns.get(i);
                questionMark += "?";
            }
            query = "INSERT INTO " + table + " (" + columnNames + ") VALUES (" + questionMark + ")"; 
            
            System.out.println("QUERY GENERADA: " + query);
            pst = conn.prepareStatement(query);
            
        } catch (Exception ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }

    @Override
    public void connect(String table) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void connect(String table, String row, String value, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void connect(String table, int values) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }   
}

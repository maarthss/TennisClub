package Controller.CRUD;

import Controller.Connection;
import Model.Fields;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;


public class Insert implements Connection {
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static String query;

    @Override
    public void connect(String table, int values) {  //Values és numèric perquè es refereix a la QUANTITAT de valors que volem insertar
        
        try {
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            
            query = "INSERT INTO " + table + " VALUES(";
            for(int i = 1; i <= values; i++){
                if( i > 1){
                    query += ", ";
                }
                query += "?";
            }
            query += ")";
            
            
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
    public void connect(String table, String row, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

       

    
}

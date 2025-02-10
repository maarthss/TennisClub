package Controller.CRUD;

import Controller.Connection;
import Controller.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Update implements Connection{
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static String query;


    @Override
    public void connect(String table, String row, int id) {
        
        try {
            
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            
            String query = "UPDATE " + table + " SET " + row + " = ? WHERE ID = ?";
            
            pst = conn.prepareStatement(query);
            pst.setString(1, row);
            pst.setInt(2, id);
            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void connect(String table, int values) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void connect(String table) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}

package Controller;

import conexiondb.ConexionMySQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;


public class Connexion {
    
    public static ConexionMySQL conn;
    
    public static void connexion(){

        
        try {            
            conn = new ConexionMySQL("localhost", "tennis_club", "root", "123456");
            conn.ejecutarConsulta("select * from fields");
            
           
            
            /*ResultSet rs = conn.getResultSet();
            int id = rs.getInt("ID");
            String name = rs.getString("name");
            Double price = rs.getDouble("price");
            Boolean status = rs.getBoolean("status");*/
            
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

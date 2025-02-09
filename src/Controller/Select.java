
package Controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Select implements Connection{
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static String query;

    @Override
    public void connect(String table, String column) {
        
        try{
            
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            query = "SELECT " + column + " FROM " + table;
            
            pst = conn.prepareStatement(query);
            
        }catch(Exception e){
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    @Override
    public void connect(String table, int quantityValues) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void connect(String table) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}

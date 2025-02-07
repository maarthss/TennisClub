package Controller;

import conexiondb.ConexionMySQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;


public class Insert implements Connection {
    
    public static Connection conn;

    @Override
    public void connect(String table, int quantityValues) {
        
        try {
            //conn = DriverManager.getConnection("localhost", "tennis_club", "root", "123456");
            String query = "INSERT INTO " + table + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            for(int i = 0; i <= 7; i++){
                if(i <= quantityValues){
                    
                }
            }
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tennis_club", "root", "123456");
            PreparedStatement pst = conn.prepareStatement("Select * from fields");*/
            
        } catch (Exception ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ConexionMySQL conn = new ConexionMySQL("localhost", "tennis_club", "root", "123456");
       
    }
       

    
}

package Controller;

import Model.Fields;
import conexiondb.ConexionMySQL;
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
    public void connect(String table, int quantityValues) {
        
        try {
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            
            query = "INSERT INTO " + table + " VALUES(";
            for(int i = 1; i <= quantityValues; i++){
                if( i > 1){
                    query += ", ";
                }
                query += "?";
            }
            query += ")";
            
            
            pst = conn.prepareStatement(query);
            
            
            /*ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                int id = rs.setInt("ID");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Boolean status = rs.getBoolean("status");
                
                Fields f = new Fields(id, name, price, status);
                obs.add(f);
            }*/

            //String query = "INSERT INTO " + table + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            /*for(int i = 0; i <= 7; i++){
                if(i <= quantityValues){
                    
                }
            }*/
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tennis_club", "root", "123456");
            PreparedStatement pst = conn.prepareStatement("Select * from fields");*/
            
        } catch (Exception ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }

    @Override
    public void connect(String table) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void connect(String table, String column, String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
       

    
}

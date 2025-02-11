
package Controller.CRUD;

import Controller.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class Delete implements Connection{
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static String query;


    @Override
    public void connect(String table, int values) { //Values aquí es refereix a l'id, ja que sempre s'esborra a partir de l'id
                
        try{
            
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            
            String query = "DELETE FROM " + table + " WHERE ID" + " = ?" ;
            
            pst = conn.prepareStatement(query);
            pst.setInt(1, values);
            pst.executeUpdate();
            
            System.out.println("S'ha eliminat la fila");
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void connect(String table, List<String> columns) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}

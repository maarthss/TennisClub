
package Controller;

import java.sql.DriverManager;

public class Delete implements Connection{

    @Override
    public void connect(String table, String column, String id) {
        
        try{
            
            java.sql.Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis_club", "root", "123456");
            
            String query = "DELETE * FROM " + column + " WHERE ";
            
        }catch(Exception e){
            e.printStackTrace();
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

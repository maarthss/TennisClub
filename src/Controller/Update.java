package Controller;

public class Update implements Connection{


    @Override
    public void connect(String table) {
        
        
    }
    
    @Override
    public void connect(String table, int quantityValues) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void connect(String table, String column, String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}

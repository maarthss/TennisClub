
package Controller;

import java.sql.PreparedStatement;


public interface Connection {
    
    public void connect(String table, int quantityValues);
    public void connect(String table, String column, String id);
    public void connect(String table); //Sa columna, es valor que volem canviar i s'id(sa condició que se posarà al where) s'agafen de sa taula, i es nou valor s'agafa des fxml especific per update
    
    
}

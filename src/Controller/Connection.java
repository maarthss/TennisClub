
package Controller;

import java.sql.PreparedStatement;
import java.util.List;


public interface Connection {
    
    public void connect(String table, int values);
    public void connect(String table, List<String> columns);
    public void connect(String table, String row, String value, int id);
    public void connect(String table); //Sa columna, es valor que volem canviar i s'id(sa condició que se posarà al where) s'agafen de sa taula, i es nou valor s'agafa des fxml especific per update
    
    
}

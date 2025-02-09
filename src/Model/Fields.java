package Model;

import Controller.Insert;
import Controller.Select;
import conexiondb.ConexionMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fields {
    
    private int id;
    private String name;
    private double price;
    private String status;

    public Fields(int id, String name, double price, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Fields() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fields{" + "id=" + id + ", name=" + name + ", price=" + price + ", status=" + status + '}';
    }
    
    public void insertField(String name, Double price, String status){
        try {
            Insert i = new Insert();
            i.connect("fields", 3);
            
            System.out.println(i.query);
            
            i.pst.setString(1, name);
            i.pst.setDouble(2, price);
            i.pst.setString(3, status);
            
            
            //Si s'activa sense un event des botó dona error, ja que intentam ficar valors null
            int rowsAffected = i.pst.executeUpdate();

            // Verifica si la inserción fue exitosa
            if (rowsAffected > 0) {
                System.out.println("Fila insertada correctamente.");
            } else {
                System.out.println("No se insertaron filas.");
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(Fields.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Object> getFields(String column){
        ObservableList<Object> obs = FXCollections.observableArrayList();
        
        Select s = new Select();
        
        try{
            s.connect("fields", column);
            ResultSet rs = s.pst.executeQuery();
            
            while(rs.next()){
                Object response = rs.getObject(column);
                obs.add(response);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        //Primer provar amb una connexió a fields desde la classe Fields per comprovar que vagi be. Si es així, passar sa connexio a sa classe connexion i fer alla
        //els mètodes crud generals i despres ajustar-los a cada classe.
        
        return obs;
    } 
    
}

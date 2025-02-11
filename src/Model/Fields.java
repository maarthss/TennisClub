package Model;

import Controller.CRUD.Delete;
import Controller.CRUD.Insert;
import Controller.CRUD.Select;
import Controller.CRUD.Update;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;


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
            List<String> columns = Arrays.asList("name", "price", "status");
            i.connect("fields", columns);
            
            System.out.println(i.query);
            
            
            i.pst.setString(1, name);
            i.pst.setDouble(2, price);
            i.pst.setString(3, status);
            
            i.pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Fields.class.getName()).log(Level.SEVERE, null, ex);
  
        } 
    }
    
    public ObservableList<Fields> getFields(){
        ObservableList<Fields> obs = FXCollections.observableArrayList();
        
        Select s = new Select();
        
        try{
            s.connect("fields");
            ResultSet rs = s.pst.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                String status = rs.getString("status");
                
                Fields f = new Fields(id, name, price, status);
                obs.add(f);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return obs;
    } 
    
    public void deleteFields(int id){
        
        Delete d = new Delete();
        
        try{
            d.connect("fields", id);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void updateFields(String row, String value, int id){
        Update u = new Update();
        
        try{
            u.connect("fields", row, value, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}

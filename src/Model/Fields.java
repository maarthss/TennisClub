package Model;

import Controller.Insert;
import conexiondb.ConexionMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;






public class Fields {
    
    private int id;
    private String name;
    private double price;
    private boolean status;

    public Fields(int id, String name, double price, boolean status) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fields{" + "id=" + id + ", name=" + name + ", price=" + price + ", status=" + status + '}';
    }
    
    
    public ObservableList<Fields> getFields(){
        ObservableList<Fields> obs = FXCollections.observableArrayList();
        try{

            
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tennis_club?serverTimezone=UTC", "root", "123456");
            PreparedStatement pst = conn.prepareStatement("Select * from fields");
            
            ResultSet rs = pst.getResultSet();
            
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Boolean status = rs.getBoolean("status");
                
                Fields f = new Fields(id, name, price, status);
                obs.add(f);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //Primer provar amb una connexió a fields desde la classe Fields per comprovar que vagi be. Si es així, passar sa connexio a sa classe connexion i fer alla
        //els mètodes crud generals i despres ajustar-los a cada classe.
        
        return obs;
    } 
    
}

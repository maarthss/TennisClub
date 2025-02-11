
package Model;

import Controller.CRUD.Delete;
import Controller.CRUD.Insert;
import Controller.CRUD.Select;
import Controller.CRUD.Update;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Matches {
    
    private int id;
    private LocalDate date;
    private String result;
    private int id_member1;
    private int id_member2;
    private int id_field;

    public Matches(int id, LocalDate date, String result, int id_member1, int id_member2, int id_field) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.id_member1 = id_member1;
        this.id_member2 = id_member2;
        this.id_field = id_field;
    }

    public Matches() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId_member1() {
        return id_member1;
    }

    public void setId_member1(int id_member1) {
        this.id_member1 = id_member1;
    }

    public int getId_member2() {
        return id_member2;
    }

    public void setId_member2(int id_member2) {
        this.id_member2 = id_member2;
    }

    public int getId_field() {
        return id_field;
    }

    public void setId_field(int id_field) {
        this.id_field = id_field;
    }

    @Override
    public String toString() {
        return "Matches{" + "id=" + id + ", date=" + date + ", result=" + result + ", id_member1=" + id_member1 + ", id_member2=" + id_member2 + ", id_field=" + id_field + '}';
    }
    
    public void insertMatch(LocalDate matchDate, String result, int member1, int member2, int field){
        
        try {
            Insert i = new Insert();
            List<String> columns = Arrays.asList("date", "result", "id_member1", "id_member2", "id_field");
            i.connect("matches", columns);
            
            System.out.println(i.query);
            
            i.pst.setDate(1, java.sql.Date.valueOf(matchDate));
            i.pst.setString(2, result);
            i.pst.setInt(3, member1);
            i.pst.setInt(4, member2);
            i.pst.setInt(5, field);
            
            
            i.pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Fields.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public ObservableList<Matches> selectMatches(){
        ObservableList<Matches> obs = FXCollections.observableArrayList();

        Select s = new Select();
        
        try{
            s.connect("matches");
            ResultSet rs = s.pst.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("ID");
                
                Date matchDate = rs.getDate("date");
                LocalDate matchDateToLocalDate = matchDate.toLocalDate();
                
                String matchResult = rs.getString("result");
                int member1 = rs.getInt("id_member1");
                int member2 = rs.getInt("id_member2");
                int field = rs.getInt("id_field");

                Matches m = new Matches(id, matchDateToLocalDate, matchResult, member1, member2, field);
                obs.add(m);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return obs;
    }
    
    
    public void deleteMatch(int id){
        Delete d = new Delete();
        
        try{
            d.connect("matches", id);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void updateMatch(String row, String value, int id){
        Update u = new Update();
        
        try{
            u.connect("matches", row, value, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}

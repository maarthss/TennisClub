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

public class Members {
    
    private int id;
    private String dni;
    private String name;
    private String surname;
    private LocalDate membership_start;
    private LocalDate date_of_birth;
    private String phone_number;

    public Members(int id, String dni, String name, String surname, LocalDate membership_start, LocalDate date_of_birth, String phone_number) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.membership_start = membership_start;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
    }

    public Members() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getMembership_start() {
        return membership_start;
    }

    public void setMembership_start(LocalDate membership_start) {
        this.membership_start = membership_start;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Members{" + "id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + ", membership_start=" + membership_start + ", date_of_birth=" + date_of_birth + ", phone_number=" + phone_number + '}';
    }
    
    
    public void insertMember(String dni, String name, String surname, LocalDate membership_start, LocalDate date_of_birth, String phone_number){
        
        try {
            Insert i = new Insert();
            List<String> columns = Arrays.asList("dni", "name", "surname", "membership_start", "date_of_birth", "phone_number");
            i.connect("members", columns);
            
            System.out.println(i.query);
            
            
            i.pst.setString(1, dni);
            i.pst.setString(2, name);
            i.pst.setString(3, surname);
            i.pst.setDate(4, java.sql.Date.valueOf(membership_start));
            i.pst.setDate(5, java.sql.Date.valueOf(date_of_birth));
            i.pst.setString(6, phone_number);
            
            i.pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Fields.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ObservableList<Members> selectMembers(){
        ObservableList<Members> obs = FXCollections.observableArrayList();

        Select s = new Select();
        
        try{
            s.connect("members");
            ResultSet rs = s.pst.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("ID");
                String dni = rs.getString("dni");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                
                Date membership_start = rs.getDate("membership_start");
                LocalDate membershipToLocalDate = membership_start.toLocalDate();
                Date date_of_birth = rs.getDate("date_of_birth");
                LocalDate birthToLocalDate = date_of_birth.toLocalDate();
                String phone_number = rs.getString("phone_number");
                
                
                
                Members m = new Members(id, dni, name, surname, membershipToLocalDate, birthToLocalDate, phone_number);
                obs.add(m);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return obs;
    }
    
    public void deleteMember(int id){
        Delete d = new Delete();
        
        try{
            d.connect("members", id);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateMember(String row, String value, int id){
         Update u = new Update();
        
        try{
            u.connect("members", row, value, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}

package Model;

import java.time.LocalDate;

public class Members {
    
    private int id;
    private String dni;
    private int name;
    private int surname;
    private LocalDate membership_start;
    private LocalDate date_of_birth;
    private String phone_number;

    public Members(int id, String dni, int name, int surname, LocalDate membership_start, LocalDate date_of_birth, String phone_number) {
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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getSurname() {
        return surname;
    }

    public void setSurname(int surname) {
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
    
    
    
}

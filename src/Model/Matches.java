
package Model;

import java.time.LocalDate;

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
}

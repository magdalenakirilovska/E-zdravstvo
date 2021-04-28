package mk.ukim.finki.wp.eshop.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class Termin {
    private Long id;
    private Calendar date;
    private User zakazhanOd;

    public Termin(Calendar date) {
        this.id = (long) (Math.random() * 1000);
        this.date = date;
        zakazhanOd = new User();
    }

    public String getUser(){
        if(zakazhanOd.getName() == null || zakazhanOd.getName().isEmpty())
            return "";
        return zakazhanOd.getName() + " " + zakazhanOd.getSurname();
    }

    @Override
    public String toString() {
        return date.getTime().toString();
    }
}

package mk.ukim.finki.wp.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Calendar date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User zakazhanOd;

    public Termin(Calendar date) {
        this.date = date;
    }

    public String getUser(){
        if(zakazhanOd == null || zakazhanOd.getName() == null || zakazhanOd.getName().isEmpty())
            return "";
        return zakazhanOd.getName() + " " + zakazhanOd.getSurname();
    }

    @Override
    public String toString() {
        return date.getTime().toString();
    }
}
